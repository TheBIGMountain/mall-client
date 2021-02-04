package pages.login

import components.modal.btn
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.A
import kotlinx.html.DIV
import react.RBuilder
import style.*
import styled.StyledComponents.css
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.loginHeader(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    height = 112.px
    display = Display.flex
    alignItems = Align.center
    child("a") {
      float = Float.left
    }
  }
  func()
}

fun RBuilder.headerInfo(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginLeft = (-55).px
    display = Display.inlineBlock
    height = 55.px
    transform { translateX(18.px) }
    child(":nth-child(1)") {
      marginTop = 2.px
      color = colorB
      height = 32.px
      fontSize = 33.px
      fontWeight = FontWeight.bold
      lineHeight = LineHeight(height.value)
    }
    child(":nth-child(2)") {
      color = colorC
      height = 10.px
      fontWeight = FontWeight.bold
      lineHeight = LineHeight(height.value)
      marginTop = 8.px
    }
  }
  func()
}

fun RBuilder.loginBody(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    width = 100.pct
    height = 576.px
    backgroundImage = Image("url('/imgs/login-bg.jpg')")
    backgroundSize = "cover"
    backgroundPosition = "55%"
  }
  func()
}

fun RBuilder.loginForm(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    float = Float.right
    backgroundColor = colorG
    width = 410.px
    height = 510.px
    marginTop = 37.px
  }
  func()
}

fun RBuilder.formHead(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginTop = 40.px
    child("span") {
      display = Display.inlineBlock
      verticalAlign = VerticalAlign.middle
    }
    child(":nth-child(1)") {
      marginLeft = 73.px
      marginRight = 35.px
      fontSize = fontE
      color = colorA
    }
    child(":nth-child(2)") {
      marginRight = 34.px
      border = "none"
      height = 25.px
      width = 1.px
      borderLeft(2.px, BorderStyle.solid, colorF)
    }
    child(":nth-child(3)") {
      fontSize = fontE
      color = colorC
    }
  }
  func()
}

fun RBuilder.loginInfo(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    height = 120.px
    width = 348.px
    margin(49.px, LinearDimension.auto, 30.px)
    child("input") {
      display = Display.block
      border = "none"
      border(1.px, BorderStyle.solid, colorH)
      paddingLeft = 18.px
      width = 100.pct
      height = 50.px
      marginBottom = 20.px
      fontSize = 14.px
      color = colorD
      placeholder {
        color = colorD
      }
    }
    sibling("a") {
      color = colorG
    }
  }
  func()
}

fun RBuilder.loginBtn(func: StyledDOMBuilder<A>.() -> Unit) = btn {
  css {
    display = Display.block
    width = 348.px
    height = 50.px
    lineHeight = LineHeight(height.value)
    fontSize = 16.px
    margin(0.px, LinearDimension.auto, 14.px)
  }
  func()
}

fun RBuilder.formBottom(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    width = 348.px
    margin(0.px, LinearDimension.auto)
    child("span") {
      display = Display.inlineBlock
      height = 14.px
      lineHeight = LineHeight(14.px.value)
    }
    child(":nth-child(1)") {
      fontSize = 14.px
      fontWeight = FontWeight.bold
      color = colorA
      cursor = Cursor.pointer
    }
    child(":nth-child(2)") { common() }
    child(":nth-child(3)") {
      common()
      ::after {
        content = QuotedString("")
        display = Display.inlineBlock
        height = 12.px
        border = "none"
        borderLeft(1.px, BorderStyle.solid, colorD)
        margin(0.px, 7.px)
        transform { translateY(1.px) }
      }
    }
  }
  func()
}

fun RBuilder.loginBottom(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginTop = 252.px
    children {
      fontSize = 14.px
      color = colorB
      fontWeight = FontWeight("400")
    }
    child("div") {
      width = 100.pct
      textAlign = TextAlign.center
    }
    child(":nth-child(1)") {
      marginBottom = 20.px
      child("span") {
        ::after {
          content = QuotedString("")
          display = Display.inlineBlock
          height = 14.px
          border = "none"
          borderLeft(1.px, BorderStyle.solid, colorD)
          margin(0.px, 10.px)
          transform { translateY(2.px) }
        }
        lastChild { after { display = Display.none } }
      }
    }
    child(":nth-child(2)") {
      paddingBottom = 93.px
    }
  }
  func()
}

private fun CSSBuilder.common() {
  float = Float.right
  color = colorD
  fontSize = 14.px
  fontWeight = FontWeight.bold
}