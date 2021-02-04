package components.service_bar

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.borderRight
import kotlinx.html.DIV
import kotlinx.html.LI
import react.RBuilder
import style.MallStyle
import style.colorC
import style.colorH
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledLi

fun RBuilder.service(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    padding(33.px, 0.px)
    color = colorC
    fontSize = 16.px
    child("ul") {
      display = Display.flex
      marginBottom = 0.px
    }
  }
  func()
}


fun RBuilder.iconSetting(img: String, func: StyledDOMBuilder<LI>.() -> Unit) = styledLi {
  css {
    flex(1.0)
    alignSelf = Align.center
    textAlign = TextAlign.center
    borderRight(1.px, BorderStyle.solid, colorH)
    verticalAlign = VerticalAlign.bottom
    lineHeight = LineHeight(18.px.value)
    child("span") {
      display = Display.inlineBlock
      verticalAlign = VerticalAlign.bottom
      image("/imgs/${img}.png")
    }
    lastChild { borderRight = "none" }
  }
  func()
}

private fun CSSBuilder.image(url: String) {
  width = 20.px
  height = 20.px
  marginRight = 8.px
  backgroundImage = Image("url('$url')")
  backgroundRepeat = BackgroundRepeat.noRepeat
  backgroundSize = "contain"
}