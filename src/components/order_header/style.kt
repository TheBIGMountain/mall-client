package components.order_header

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.html.DIV
import react.RBuilder
import style.MallStyle
import style.colorB
import style.colorC
import style.colorD
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.orderHeaderSty(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    padding(30.px, 0.px)
    child("a") { float = Float.left }
  }
  func()
}

fun RBuilder.orderTitle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.inlineBlock
    height = 55.px
    lineHeight = LineHeight(55.px.value)
    fontSize = 28.px
    color = colorB
    child(":nth-child(2)") {
      fontSize = 14.px
      marginLeft = 17.px
      color = colorD
      fontWeight = FontWeight.bold
    }
  }
  func()
}

fun RBuilder.orderUsername(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    float = Float.right
    lineHeight = LineHeight(55.px.value)
    child("a") {
      color = colorC
      fontSize = 16.px
    }
  }
  func()
}
