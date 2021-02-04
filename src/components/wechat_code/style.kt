package components.wechat_code

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.transform
import kotlinx.css.properties.translate
import kotlinx.html.DIV
import react.RBuilder
import style.colorA
import style.colorB
import style.colorG
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledImg

fun RBuilder.scanImg() = styledImg {
  attrs.src = "/imgs/pay/icon-scan.png"
  css {
    width = 296.px
    height = 485.px
    pot()
    zIndex = 1
  }
}

private fun CSSBuilder.pot() {
  position = Position.fixed
  top = 40.pct
  left = 30.pct
  transform { translate((-50).pct, (-50).pct) }
}

fun RBuilder.wxCode(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    width = 370.px
    height = 440.px
    backgroundColor = colorG
    pot()
    left = 50.5.pct
    children {
      textAlign = TextAlign.center
      fontSize = 14.px
      lineHeight = LineHeight(22.px.value)
      color = colorB
      fontWeight = FontWeight.bold
      firstChild {
        backgroundColor = Color("#F5F5F5")
        lineHeight = LineHeight(60.px.value)
        fontSize = 20.px
        paddingLeft = 18.px
        textAlign = TextAlign.left
        display = Display.flex
      }
      child("span") { color = colorA }
      child("em") {
        marginLeft = LinearDimension.auto
        marginRight = 23.px
        width = 13.px
        height = 13.px
        background = "url('/imgs/icon-close.png') center"
        backgroundSize = "contain"
        cursor = Cursor.pointer
        alignSelf = Align.center
      }
    }
    child("img") {
      display = Display.block
      width = 237.px
      height = 240.px
      margin(44.px, LinearDimension.auto, 26.px)
    }
  }
  func()
}