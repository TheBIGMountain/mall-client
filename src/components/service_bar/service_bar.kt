package components.service_bar

import react.RBuilder
import react.RProps
import react.child
import react.dom.span
import react.dom.ul
import react.functionalComponent

private val imgs = arrayOf("icon-setting", "icon-7day",  "icon-15day", "icon-post")
private val serviceBar = functionalComponent<RProps> {
  service {
    ul {
      iconSetting(imgs[0]) { span {  }; +"预约维修服务" }
      iconSetting(imgs[1]) { span {  }; +"7天无理由退货" }
      iconSetting(imgs[2]) { span {  }; +"15天免费换货" }
      iconSetting(imgs[3]) { span {  }; +"满150元包邮" }
    }
  }
}

fun RBuilder.serviceBar() = child(serviceBar)