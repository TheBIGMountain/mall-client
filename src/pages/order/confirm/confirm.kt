package pages.order.confirm

import ajax.cart.Cart
import ajax.cart.getCarts
import ajax.order.createOrder
import ajax.shipping.*
import antd.input.input
import antd.input.textArea
import antd.message.message
import antd.select.SelectComponent
import antd.select.option
import antd.select.select
import components.modal.*
import kotlinext.js.jsObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.html.INPUT
import kotlinx.html.js.onClickFunction
import modules.History
import modules.useHistory
import pages.cart.f5f5f5Bg
import react.*
import react.dom.div
import react.dom.key
import react.dom.p
import react.dom.span
import styled.css
import styled.styledDiv


private data class OrderConfirmState(
  val shippings: Array<Shipping> = emptyArray(),
  val cart: Cart? = null,
  val checkItem: Shipping = defaultCheckItem(),
  val isShowDel: Boolean = false,
  val isShowAdd: Boolean = false,
  val checkedIndex: Int = -1
)

private fun defaultCheckItem() = jsObject<Shipping> {
  receiverProvince = "北京"
  receiverCity = "河南"
  receiverDistrict = "昌平区"
}

private fun init(state: OrderConfirmState, setState: (OrderConfirmState) -> Unit)
= MainScope().launch {
  shippings().zip(getCarts()) { ships, c ->
    setState(state.copy(shippings = ships, cart = c))
  }.collect()
}

fun <T> Flow<T>.withMessage(): Flow<T> = onEach { message.success("操作成功") }
private fun <T> Flow<T>.toShippings(): Flow<Array<Shipping>> = map { shippings().single() }
private suspend fun <T> Flow<T>.over(state: OrderConfirmState, setState: (OrderConfirmState) -> Unit)
= toShippings()
  .onEach { setState(state.copy(shippings = it, checkItem = defaultCheckItem(), isShowAdd = false)) }
  .withMessage()
  .collect()

private fun deleteAddress(state: OrderConfirmState, setState: (OrderConfirmState) -> Unit)
= MainScope().launch {
  state.checkItem.id.delShipping()
    .toShippings()
    .onEach { setState(state.copy(checkItem = defaultCheckItem(), shippings = it, isShowDel = false)) }
    .withMessage()
    .collect()
}

private fun addAddress(state: OrderConfirmState, setState: (OrderConfirmState) -> Unit)
= MainScope().launch {
  state.checkItem.addShippings().over(state, setState)
}

private fun updateAddress(state: OrderConfirmState, setState: (OrderConfirmState) -> Unit)
= MainScope().launch {
  state.checkItem.updateShipping().over(state, setState)
}

private fun addOrUpdateAddress(state: OrderConfirmState, setState: (OrderConfirmState) -> Unit, type: Int) {
  if (type == 0)
    addAddress(state, setState)
  else
    updateAddress(state, setState)
}

private fun isOver(history: History, state: OrderConfirmState) = MainScope().launch {
  flow { emit(state.shippings[state.checkedIndex]) }
    .onEach { if (it == undefined) throw RuntimeException() }
    .catch { message.error("请选择地址!") }
    .map { it.id.createOrder().map { it.orderNo }.single() }
    .onEach { history.push("/order/pay/$it") }
    .collect()
}

private var submitType = -1
private val orderConfirm = functionalComponent<RProps> {
  val (state, setState) = useState(OrderConfirmState())
  val history = useHistory()

  useEffect(emptyList()) { init(state, setState) }

  f5f5f5Bg { css { paddingTop = 32.px; paddingBottom = 204.px }
    shippingWrapper {
      div { +"收货地址" }
      div {
        state.shippings.forEachIndexed { index, it ->
          addressInfo(index == state.checkedIndex) { attrs.key = it.id.toString()
            div { +it.receiverName }
            phone { +it.receiverMobile }
            street { +"${it.receiverProvince} ${it.receiverCity} ${it.receiverDistrict} ${it.receiverAddress}" }
            action {
              addressDel { attrs.onClickFunction = { _ -> setState(state.copy(checkItem = it, isShowDel = true)) } }
              addressEdit { attrs.onClickFunction = { _ -> submitType = 1; setState(state.copy(checkItem = it, isShowAdd = true))  } }
            }
            span { attrs.onClickFunction = { setState(state.copy(checkedIndex = index)) } }
          }
        }
        addAddress { div { attrs.onClickFunction = { submitType = 0; setState(state.copy(checkItem = defaultCheckItem(), isShowAdd = true)) } } }
      }
      styledDiv { +"商品"; css { marginTop = 30.px; fontSize = 20.px; fontWeight = FontWeight.bold } }
      state.cart?.let { cart ->
        val selectedProducts = cart.cartProductVoList.filter { it.productSelected }
        selectedProducts.forEach {
          productPeek { attrs.key = it.productId.toString()
            prodImg(it.productMainImage)
            div { +it.productName }
            div { +"${it.productPrice}元x${it.quantity}" }
            div { +"${it.productTotalPrice}元" }
          }
        }
        addressContent {
          deliverMethod {
            span { +"配送方式" }
            span { +"包邮" }
          }
          invoice {
            span { +"发票" }
            span { +"电子发票" }
            span { +"个人" }
            span { +"商品明细" }
          }
          styledDiv { css { float = Float.right; marginTop = 50.px; marginRight = 24.px }
            productInfo {
              div { span { +"商品件数:" }; span { +"${selectedProducts.fold(0) { a, b -> a + b.quantity }}件" } }
              div { span { +"商品总价:" }; span { +"${cart.cartTotalPrice}元" } }
              div { span { +"优惠活动:" }; span { +"0元" } }
              div { span { +"运费:" }; span { +"0元" } }
            }
            totalPrice {
              span { +"应付金额:" }
              span { +"元" }
              span { +"${cart.cartTotalPrice}" }
            }
          }
        }
      }
      btnGroup {
        btnLarge { +"去结算"; attrs.onClickFunction = { isOver(history, state) } }
        btnDefault { +"返回购物车"; attrs.onClickFunction = { history.push("/cart") } }
      }
    }
  }
  modal(jsObject {
    title = "删除确认"
    bthType = arrayOf(BthType.CONFIRM)
    sureText = "确认"
    showModal = state.isShowDel
    handleCancel = { setState(state.copy(isShowDel = false)) }
    handleConfirm = { deleteAddress(state, setState) }
  }) {
    p { +"您确认要删除此地址吗" }
  }
  modal(jsObject {
    title = "收货地址信息"
    bthType = arrayOf(BthType.CONFIRM)
    sureText = "确认"
    showModal = state.isShowAdd
    handleCancel = { setState(state.copy(isShowAdd = false)) }
    handleConfirm = { addOrUpdateAddress(state, setState, submitType) }
  }) {
    editWrap {
      editItem {
        input { attrs { placeholder = "姓名"; value = state.checkItem.receiverName }
          attrs.onChangeCapture = { setState(state.apply { checkItem.receiverName = it.target.unsafeCast<INPUT>().value }.copy()) }
        }
        input { attrs { placeholder = "手机号"; value = state.checkItem.receiverMobile }
          attrs.onChangeCapture = { setState(state.apply { checkItem.receiverMobile = it.target.unsafeCast<INPUT>().value }.copy()) }
        }
      }
      editItem {
        select<String, SelectComponent<String>> {
          attrs.defaultValue = state.checkItem.receiverProvince
          attrs.onChange = { value, _ -> setState(state.apply { checkItem.receiverProvince = value }.copy()) }
          option { attrs.value = "北京"; +"北京" }
          option { attrs.value = "天津"; +"天津" }
          option { attrs.value = "石家庄"; +"石家庄" }
        }
        select<String, SelectComponent<String>> { attrs.value = state.checkItem.receiverCity
          attrs.onChange = { value, _ -> setState(state.apply { checkItem.receiverCity = value }.copy()) }
          option { attrs.value = "河南"; +"河南" }
          option { attrs.value = "河北"; +"河北" }
        }
        select<String, SelectComponent<String>> { attrs.value = state.checkItem.receiverDistrict
          attrs.onChange = { value, _ -> setState(state.apply { checkItem.receiverDistrict = value }.copy()) }
          option { attrs.value = "昌平区"; +"昌平区" }
          option { attrs.value = "海淀区"; +"海淀区" }
          option { attrs.value = "东城区"; +"东城区" }
          option { attrs.value = "西城区"; +"西城区" }
          option { attrs.value = "顺义区"; +"顺义区" }
          option { attrs.value = "房山区"; +"房山区" }
        }
      }
      editItem {
        textArea { attrs { value = state.checkItem.receiverAddress; placeholder = "请输入详细地址" }
          attrs.onChangeCapture = { setState(state.apply { checkItem.receiverAddress = it.target.asDynamic().value.unsafeCast<String>() }.copy()) }
        }
      }
      editItem {
        input { attrs { placeholder = "邮编"; value = state.checkItem.receiverZip }
          attrs.onChangeCapture = { setState(state.apply { checkItem.receiverZip = it.target.unsafeCast<INPUT>().value }.copy()) }
        }
      }
    }
  }
}

fun RBuilder.confirm() = child(orderConfirm)

