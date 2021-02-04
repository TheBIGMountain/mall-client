package pages.order.pay

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.DIV
import react.RBuilder
import style.*
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.payDetailWrapper(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    backgroundColor = colorG
    marginBottom = 30.px
    paddingTop = 62.px
    children {
      firstChild {
        display = Display.flex
        alignItems = Align.center
        paddingBottom = 45.px
        children {
          firstChild {
            display = Display.inlineBlock
            width = 90.px
            height = 90.px
            background = "url('/imgs/icon-gou.png') #80c58a no-repeat center"
            borderRadius = 50.pct
            backgroundSize = "50px, 50px"
            marginLeft = 53.px
          }
        }
      }
    }
  }
  func()
}

fun RBuilder.orderSubmitInfo(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginLeft = 40.px
    children {
      fontSize = 14.px
      fontWeight = FontWeight.bold
      color = colorC
      lineHeight = LineHeight(24.px.value)
      child("span") { color = colorA }
      firstChild {
        fontSize = 24.px
        lineHeight = LineHeight(60.px.value)
        color = colorB
      }
      lastChild {
        child("span") {
          color = colorC
          marginLeft = 30.px
        }
      }
    }
  }
  func()
}


fun RBuilder.detail(isShow: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = if (isShow) Display.block else Display.none
    borderTop(1.px, BorderStyle.solid, colorF)
    padding(53.px, 0.px)
    paddingLeft = 130.px
    margin(0.px, 53.px)
    fontSize = 14.px
    fontWeight = FontWeight.bold
    color = colorB
    children { lineHeight = LineHeight(30.px.value) }
    children {
      children {
        firstChild {
          display = Display.inlineBlock
          width = 100.px
        }
      }
      firstChild {
        children {
          lastChild { color = colorA }
        }
      }
    }
  }
  func()
}

fun RBuilder.productName(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    child("img") {
      width = 20.px
      height = 20.px
      marginRight = 10.px
    }
  }
  func()
}

fun RBuilder.payPriceWrapper(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginLeft = LinearDimension.auto
    marginRight = 51.px
    textAlign = TextAlign.right
    fontSize = 14.px
    color = colorC
    lineHeight = LineHeight(24.px.value)
  }
  func()
}

fun RBuilder.payPrice(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginBottom = 20.px
    fontWeight = FontWeight.bold
    children {
      color = colorA
      firstChild {
        fontSize = 25.px
        fontWeight = FontWeight.bold
        marginLeft = 20.px
      }
      lastChild { fontSize = 18.px }
    }
  }
  func()
}

fun RBuilder.orderDet(isShow: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    children {
      display = Display.inlineBlock
      width = 14.px
      height = 10.px
      background = "url('/imgs/icon-down.png') no-repeat center"
      backgroundSize = "contain"
      marginLeft = 9.px
      cursor = Cursor.pointer
      if (isShow) {
        transform { rotate(Angle("180deg")) }
        transition("all", 0.5.s)
      }
      transition("all", 0.5.s)
    }
  }
  func()
}

fun RBuilder.payMethodWrapper(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    backgroundColor = colorG
    height = 270.px
    padding(0.px, 53.px)
    fontWeight = FontWeight.bold
    color = colorB
    children {
      firstChild {
        lineHeight = LineHeight(60.px.value)
        fontSize = 20.px
      }
    }
  }
  func()
}

fun RBuilder.payPlatform(checked: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    borderTop(1.px, BorderStyle.solid, colorF)
    children {
      display = Display.inlineBlock
      width = 188.px
      height = 64.px
      firstChild {
        display = Display.block
        fontSize = 18.px
        lineHeight = LineHeight(60.px.value)
      }
      lastChild {
        platform(!checked, "wechat")
      }
    }
    child(":nth-child(2)") {
      margin(0.px, 20.px)
      platform(checked, "ali")
    }
  }
  func()
}

private fun CSSBuilder.platform(checked: Boolean, url: String) {
  if (checked)
    border(1.px, BorderStyle.solid, colorA)
  else
    border(1.px, BorderStyle.solid, colorF)
  background = "url('/imgs/pay/icon-$url.png') center no-repeat"
  backgroundSize = "100px 35px"
  cursor = Cursor.pointer
}