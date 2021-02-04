package pages.order.pay

import ajax.order.OrderItem
import ajax.order.orderDetail
import ajax.pay.PayType
import ajax.pay.pay
import components.modal.BthType
import components.modal.modal
import components.wechat_code.wechatCode
import kotlinext.js.jsObject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.css.paddingBottom
import kotlinx.css.paddingTop
import kotlinx.css.px
import kotlinx.html.js.onClickFunction
import modules.History
import modules.QRCode
import modules.useHistory
import modules.useParams
import pages.cart.f5f5f5Bg
import react.*
import react.dom.div
import react.dom.img
import react.dom.p
import react.dom.span
import styled.css


private fun getOrderDetail(orderNo: String, state: OrderPayState, setState: (OrderPayState) -> Unit)
= MainScope().launch {
  orderNo.orderDetail()
    .onEach { state.addressInfo = it.shippingVo.run { "$receiverName $receiverMobile $receiverProvince $receiverCity $receiverDistrict $receiverAddress" } }
    .onEach { state.orderItems = it.orderItemVoList }
    .onEach { state.payment = it.payment }
    .onEach { setState(state.copy()) }
    .collect()
}

private fun wxPay(orderNo: String, state: OrderPayState, setState: (OrderPayState) -> Unit)
= MainScope().launch {
  orderNo.pay(PayType.WXPAY_NATIVE)
    .onEach { state.isChecked = false }
    .onEach { state.isShowWx = true }
    .onEach { state.qrcode = QRCode.toDataURL(it).await() }
    .onEach { setState(state.copy()) }
    .collect()
}

private data class OrderPayState(
  var isChecked: Boolean = true,
  var isDetail: Boolean = false,
  var isShowWx: Boolean = false,
  var isShowPayModal: Boolean = false,
  var payment: Double = 0.0,
  var qrcode: String = "",
  var addressInfo: String = "",
  var orderItems: Array<OrderItem> = emptyArray()
)

private fun loopOrderStatus(orderNo: String, history: History) = MainScope().launch {
  while (true) {
    delay(1000)
    orderNo.orderDetail()
      .filter { it.status == 20 }
      .onEach { history.push("/order/list") }
      .onEach { cancel() }
      .collect()
  }
}

private val pay = functionalComponent<RProps> {
  val (state, setState) = useState(OrderPayState())
  val orderNo = useParams().orderNo.unsafeCast<String>()
  val history = useHistory()

  useEffect(emptyList()) { getOrderDetail(orderNo, state, setState) }

  f5f5f5Bg { css { paddingTop = 30.px; paddingBottom = 261.px }
    payDetailWrapper {
      div {
        div {  }
        orderSubmitInfo {
          div { +"订单提交成功! 去付款咯~" }
          div { +"请在"; span { +"0小时30分" }; +"内完成支付, 超时后取消订单" }
          div { +"收货信息: **0972"; span { +"北京 北京市 朝阳区" } }
        }
        payPriceWrapper {
          payPrice { +"应付金额:"; span { +"${state.payment}" }; span { +"元" } }
          orderDet(state.isDetail) { +"订单详情"; span { attrs.onClickFunction = { setState(state.copy(isDetail = !state.isDetail)) } } }
        }
      }
      detail(state.isDetail) {
        div { span { +"订单号:" }; span { +orderNo } }
        div { span { +"收货信息:" }; span { +state.addressInfo } }
        state.orderItems.forEach {
          productName { span { +"商品名称:" }; img { attrs { src = it.productImage } }; span { + it.productName } }
        }
        div { span { +"发票信息:" }; span { +"电子发票 个人" } }
      }
    }
    payMethodWrapper {
      div { +"选择以下支付方式付款" }
      payPlatform(state.isChecked) {
        div { +"支付平台" }
        div { attrs.onClickFunction = {
          setState(state.copy(isChecked = true))
          history.push("/order/alipay/$orderNo")
        } }
        div { attrs.onClickFunction = {
          wxPay(orderNo, state, setState)
          loopOrderStatus(orderNo, history) }
        }
      }
    }
  }
  wechatCode(state.isShowWx, state.qrcode) {
    setState(state.copy(isShowWx = false, isShowPayModal = true))
  }
  modal(jsObject {
    title = "支付确认"
    bthType = arrayOf(BthType.CONFIRM, BthType.CANCEL)
    showModal = state.isShowPayModal
    sureText = "查看订单"
    cancelText = "未支付"
    handleCancel = { setState(state.copy(isShowPayModal = false)) }
    handleConfirm = { history.push("/order/list") }
  }) {
    p { +"您确认是否完成支付?" }
  }
}

fun RBuilder.pay() = child(pay)