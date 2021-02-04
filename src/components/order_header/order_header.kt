package components.order_header

import components.nav_header.logo
import components.order_header.orderHeaderSty
import components.order_header.orderTitle
import components.order_header.orderUsername
import kotlinx.css.BorderStyle
import kotlinx.css.properties.borderBottom
import kotlinx.css.px
import modules.useSelector
import react.RBuilder
import react.RProps
import react.child
import react.dom.a
import react.dom.span
import react.functionalComponent
import style.colorA
import styled.css
import styled.styledDiv

private interface OrderHeaderProps: RProps {
  var title: String
  var content: RBuilder.() -> Unit
}

private val orderHead = functionalComponent<OrderHeaderProps> { props ->
  val username = useSelector { it.username }

  styledDiv { css { borderBottom(2.px, BorderStyle.solid, colorA) }
    orderHeaderSty {
      logo {  }
      orderTitle {
        span { +props.title }
        props.content(this)
      }
      orderUsername {
        a { +username }
      }
    }
  }
}

fun RBuilder.orderHead(title: String, content: RBuilder.() -> Unit)
= child(orderHead) { attrs.title = title; attrs.content = content }