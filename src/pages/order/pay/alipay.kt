package pages.order.pay

import ajax.pay.PayType
import ajax.pay.pay
import components.loading.loading
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import modules.useParams
import org.w3c.dom.get
import react.*
import react.dom.div


private fun paySubmit(orderNo: String, setContent: (String) -> Unit) = MainScope().launch {
  orderNo.pay(PayType.ALIPAY_PC)
    .onEach { setContent(it) }
    .onEach { delay(100) }
    .onEach { document.forms[0].asDynamic().submit().unsafeCast<Any>() }
    .collect()
}

private val alipay = functionalComponent<RProps> {
  val orderNo = useParams().orderNo.unsafeCast<String>()
  val (content, setContent) = useState("")

  useEffect(emptyList()) { paySubmit(orderNo, setContent) }
  loading(true)
  div {
    attrs {
      set("dangerouslySetInnerHTML", jsObject<dynamic> {
        __html = content
      }.unsafeCast<Any>())
    }
  }
}

fun RBuilder.alipay() = child(alipay)