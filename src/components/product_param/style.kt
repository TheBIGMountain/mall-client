package components.product_param

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.borderTop
import kotlinx.css.properties.boxShadow
import kotlinx.html.DIV
import react.RBuilder
import style.*
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.navBar(fixed: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    if (fixed) {
      position = Position.fixed
      top = 0.px
      width = 100.pct
      boxShadow(colorE, 0.px, 5.px, 5.px)
    }
    height = 70.px
    lineHeight = LineHeight(height.value)
    borderTop(1.px, BorderStyle.solid, colorH)
    backgroundColor = colorG
    zIndex = 30
  }
  func()
}

fun RBuilder.proTitle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.inlineBlock
    fontSize = fontH
    color = colorB
    fontWeight = FontWeight.bold
  }
  func()
}

fun RBuilder.proParam(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    float = Float.right
    fontSize = fontJ
    color = colorC
    child(":nth-child(5)") {
      marginRight = 10.px
    }
    child("span") {
      margin(0.px, 10.px)
    }
    child("a") {
      color = colorC
      lastChild { color = colorG }
    }
  }
  func()
}