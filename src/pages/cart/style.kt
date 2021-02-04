package pages.cart

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.border
import kotlinx.css.properties.borderTop
import kotlinx.html.DIV
import react.RBuilder
import style.*
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.f5f5f5Bg(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    backgroundColor = Color("#F5F5F5")
  }
  func()
}

fun RBuilder.cartWrapper(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    paddingTop = 30.px
    paddingBottom = 114.px
  }
  func()
}

fun RBuilder.cartContent(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    backgroundColor = colorG
    paddingLeft = 50.px
    child(":nth-child(2)::before") {
      marginLeft = (-15).px
    }
  }
  func()
}

fun RBuilder.checkBox(isChecked: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.inlineBlock
    width = 22.px
    height = 22.px
    border(1.px, BorderStyle.solid, Color("#E5E5E5"))
    verticalAlign = VerticalAlign.top
    cursor = Cursor.pointer
    if (isChecked) {
      background = "url('/imgs/icon-gou.png') #FF6600 no-repeat center"
      backgroundSize = "16px 12px"
      border = "none"
    }
  }
  func()
}

fun RBuilder.cartContentTitle(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    paddingTop = 20.px
    marginBottom = 20.px
    children {
      color = colorD
      fontSize = 17.px
    }
    child(":nth-child(2)") {
      paddingLeft = 10.px
    }
    child(":nth-child(3)") {
      paddingLeft = 120.px
    }
    child(":nth-child(4)") {
      paddingLeft = 400.px
    }
    child(":nth-child(5)") {
      paddingLeft = 120.px
    }
    child(":nth-child(6)") {
      paddingLeft = 120.px
    }
    child(":nth-child(7)") {
      paddingLeft = 100.px
    }
  }
  func()
}

fun RBuilder.cartProduct(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.flex
    alignItems = Align.center
    height = 140.px
    borderTop(1.px, BorderStyle.solid, colorD)
    marginRight = 40.px
    fontWeight = FontWeight.bold
    fontSize = 18.px
    color = colorB
    child("img") {
      width = 90.px
      marginLeft = 7.pct
    }
    child(":nth-child(3)") {
      fontSize = 20.px
      width = 38.pct
    }
    child(":nth-child(4)") {
      marginLeft = LinearDimension.auto
      marginRight = 5.pct
    }
  }
  func()
}

fun RBuilder.productSum(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    border(1.px, BorderStyle.solid, colorD)
    width = 13.pct
    height = 50.px
    lineHeight = LineHeight(height.value)
    marginRight = 4.pct
    display = Display.flex
    textAlign = TextAlign.center
    alignItems = Align.center
    fontSize = 20.px
    child("a") {
      color = colorB
      hover { color = colorA }
    }
    children {
      flex(1.0)
    }
  }
  func()
}

fun RBuilder.productPrice(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    color = colorA
    width = 14.pct
  }
  func()
}

fun RBuilder.cancelProduct(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    background = "url('/imgs/icon-close.png') center no-repeat"
    backgroundSize = "cover"
    width = 20.px
    height = 20.px
    cursor = Cursor.pointer
    marginRight = 1.pct
  }
  func()
}

fun RBuilder.cartBottom(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginTop = 20.px
    overflow = Overflow.hidden
    child(":nth-child(1)") {
      display = Display.inlineBlock
      lineHeight = LineHeight(50.px.value)
      color = colorD
      fontSize = 18.px
      fontWeight = FontWeight.bold
      child(":nth-child(2)") {
        margin(0.px, 10.px)
        fontWeight = FontWeight("100")
      }
      child("span span") { color = colorA }
    }
    child(":nth-child(2)") {
      float = Float.right
      child("a") {
        marginLeft = 30.px
        float = Float.right
      }
      child("span") {
        color = colorA
        fontSize = 18.px
        fontWeight = FontWeight.bold
        child("span") {
          fontSize = 35.px
        }
      }
    }
  }
  func()
}
