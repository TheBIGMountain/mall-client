package components.wechat_code

import components.modal.mask
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RProps
import react.child
import react.dom.div
import react.dom.em
import react.dom.img
import react.dom.span
import react.functionalComponent

private interface WechatCodeProps: RProps {
  var isShow: Boolean
  var qrcode: String
  var cancel: () -> Unit
}

private val wechatCode = functionalComponent<WechatCodeProps> { props ->
  if (props.isShow) {
    mask {  }
    scanImg()
    wxCode {
      div { +"微信支付"; em { attrs.onClickFunction = { props.cancel() } } }
      println(props.qrcode)
      img { attrs.src = props.qrcode }
      div { +"请使用"; span { +"微信" }; +"扫一扫" }
      div { +"二维码完成支付" }
    }
  }
}

fun RBuilder.wechatCode(isShow: Boolean, qrcode: String, cancel: () -> Unit)
= child(wechatCode) {
  attrs.isShow = isShow
  attrs.qrcode = qrcode
  attrs.cancel = cancel
}