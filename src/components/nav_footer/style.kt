package components.nav_footer

import kotlinx.css.*
import kotlinx.css.properties.borderTop
import kotlinx.html.DIV
import react.RBuilder
import style.colorA
import style.colorB
import style.colorD
import style.fontI
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.footerWrapper(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    height = 234.px
    borderTop(4.px, BorderStyle.solid, colorA)
    backgroundColor = colorB
    color = colorD
    fontSize = fontI
    textAlign = TextAlign.center
  }
  func()
}

fun RBuilder.footerLogo(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginTop = 46.px
    marginBottom = 31.px
    child("img") {
      width = 53.px
      height = 36.px
      marginBottom = 13.px
    }
  }
  func()
}

fun RBuilder.footerLink(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    child("a") {
      color = colorD
      display = Display.inlineBlock
      lastChild { after { display = Display.none } }
      ::after {
        content = QuotedString("|")
        border = "none"
        fontSize = 20.px
        margin(0.px, 10.px)
      }
    }
  }
  func()
}

fun RBuilder.copyright(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginTop = 13.px
    child("span") { color = colorA }
  }
  func()
}