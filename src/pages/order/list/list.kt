package pages.order.list

import ajax.order.Order
import ajax.order.orders
import components.loading.loading
import components.no_data.noData
import kotlinext.js.js
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import kotlinx.html.style
import modules.History
import modules.infiniteScroll
import modules.useHistory
import pages.cart.f5f5f5Bg
import react.*
import react.dom.div
import react.dom.img
import react.dom.key
import react.dom.span
import styled.css
import styled.styledImg
import utils.formatDate
import kotlin.js.Date

private data class OrderListState(
  var pageNum: Int = 1,
  var hasMore: Boolean = true,
  var isShowLoading: Boolean = true,
  var orders: Array<Order> = emptyArray()
)

private fun scrollMore(state: OrderListState, setState: (OrderListState) -> Unit)
= MainScope().launch {
  orders(pageNum = state.pageNum, pageSize = 3)
    .onEach { state.pageNum = state.pageNum + 1 }
    .onEach { state.orders = state.orders.toMutableList().apply { addAll(it) }.toTypedArray() }
    .onEach { state.isShowLoading = false }
    .onEach { state.hasMore = it.isNotEmpty() }
    .onEach { delay(600) }
    .onEach { setState(state.copy()) }
    .collect()
}

private fun RBuilder.toOrderItem(orders: Array<Order>, history: History): Array<ReactElement> {
  return orders.withIndex().map {
    val order = it.value
    orderItem { attrs.key = it.index.toString()
      orderData {
        div {
          span { +Date(order.createTime).formatDate() }
          span { +"|" }
          span { +order.shippingVo.receiverName }
          span { +"|" }
          span { +"订单号: ${order.orderNo}" }
          span { +"|" }
          span { +"在线支付" }
        }
        div {
          span { +"应付金额: " }
          span { +"${order.payment}" }
          span { +"元" }
        }
      }
      order.orderItemVoList.forEach { item ->
        productData { attrs.key = item.productId.toString()
          img { attrs.src = item.productImage; }
          div {
            div { +item.productName }
            div { +"${item.currentUnitPrice}元"; span { +"X" }; +"${item.quantity}" }
          }
          atOnce {
            if (order.status == 10) {
              span { +"立即付款"; attrs.style = js { cursor = "pointer" }.unsafeCast<String>()
                attrs.onClickFunction = { history.push("/order/pay/${order.orderNo}") }
              }
              img { attrs.src = "/imgs/>.png" }
            }
            else span { +"已支付" }
          }
        }
      }
    }
  }.toTypedArray()
}

private val list = functionalComponent<RProps> {
  val (state, setState) = useState(OrderListState())
  val history = useHistory()

  f5f5f5Bg { css { padding(33.px, 0.px, 110.px) }
    loading(state.isShowLoading)
    infiniteScroll {
      attrs {
        hasMore = state.hasMore
        loadMore = { if (state.hasMore) { state.hasMore = false; scrollMore(state, setState) } }
        loader = buildElement {
          styledImg {
            css {
              height = 80.px
              display = Display.block
              margin(0.px, LinearDimension.auto)
            }
            attrs.key = "**"
            attrs.src = "/imgs/loading-svg/loading-bars.svg"
          }
        }
        children = toOrderItem(state.orders, history)
      }
    }
    if (!state.isShowLoading && state.orders.isEmpty()) noData()
  }
}

fun RBuilder.list() = child(list)