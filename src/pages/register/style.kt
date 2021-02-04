package pages.register

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.transform
import kotlinx.css.properties.translateX
import kotlinx.html.DIV
import react.RBuilder
import style.colorB
import style.colorG
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.registerWrap(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    width = 850.px
    height = 565.px
    margin(0.px, LinearDimension.auto, 80.px)
    backgroundColor = colorG
    children {
      margin(0.px, LinearDimension.auto)
      display = Display.block
      textAlign = TextAlign.center
      firstChild {
        display = Display.flex
        justifyContent = JustifyContent.center
        children {
          transform { translateX(25.pct) }
          hover {
            before { marginLeft = (-110).px }
          }
        }
      }
      lastChild {
        display = Display.flex
        justifyContent = JustifyContent.center
      }
    }
    child("input") {
      textAlign = TextAlign.left
      width = 300.px
      height = 42.px
    }
    child(":nth-child(2)") {
      margin(46.px, LinearDimension.auto)
      fontSize = 30.px
      fontWeight = FontWeight.bold
      color = colorB
      lineHeight = LineHeight(60.px.value)
    }
    child(":nth-child(3)") {
      width = 300.px
      textAlign = TextAlign.left
      color = colorB
      fontWeight = FontWeight.bold
      fontSize = 14.px
    }
    child(":nth-child(5)") {
      width = 300.px
      textAlign = TextAlign.left
      color = colorB
      fontWeight = FontWeight.bold
      fontSize = 14.px
      marginTop = 30.px
    }
  }
  func()
}

fun RBuilder.verificationCode(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    margin(30.px, LinearDimension.auto)
    display = Display.flex
    width = 300.px
    children {
      firstChild {
        width = 150.px
        marginRight = 20.px
      }
    }
  }
  func()
}