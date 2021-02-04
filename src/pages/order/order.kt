package pages.order

import components.nav_footer.navFooter
import components.order_header.orderHead
import components.service_bar.serviceBar
import modules.useLocation
import react.*
import react.dom.span

private interface OrderProps: RProps {
  var content: RBuilder.() -> Unit
}

private fun setOrderHeader(pathname: String, setTitle: (String) -> Unit, setTip: (String) -> Unit)
= when(pathname) {
  "/order/confirm" -> { setTitle("订单确认"); setTip("请认真填写收货地址 ") }
  "/order/list" -> { setTitle("订单列表"); setTip("请谨防钓鱼链接或诈骗电话, 了解更多>") }
  else -> { setTitle("订单支付"); setTip("请谨防钓鱼链接或诈骗电话, 了解更多>") }
}

private val order = functionalComponent<OrderProps> { props ->
  val (title, setTitle) = useState("")
  val (tip, setTip) = useState("")
  val pathname = useLocation().pathname

  useEffect(listOf(pathname)) { setOrderHeader(pathname, setTitle, setTip) }

  orderHead(title) { span { +tip } }
  props.content(this)
  serviceBar()
  navFooter()
}

fun RBuilder.order(content: RBuilder.() -> Unit) = child(order) { attrs.content = content }