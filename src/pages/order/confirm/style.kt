package pages.order.confirm

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.A
import kotlinx.html.DIV
import react.RBuilder
import style.*
import styled.StyledDOMBuilder
import styled.css
import styled.styledA
import styled.styledDiv

fun RBuilder.shippingWrapper(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    backgroundColor = colorG
    paddingTop = 38.px
    paddingBottom = 90.px
    paddingLeft = 63.px
    child(":nth-child(1)") {
      fontSize = 20.px
      fontWeight = FontWeight("400")
      color = colorB
    }
    child(":nth-child(2)") {
      marginTop = 21.px
    }
    children {
      overflow = Overflow.hidden
      lastChild {
        children { float = Float.right }
        child(":nth-child(1)") {
          marginRight = 0.px
          marginLeft = 20.px
        }
        child(":nth-child(2)") {
          width = 202.px
          height = 50.px
          lineHeight = LineHeight(height.value)
          backgroundColor = colorG
          color = colorD
          fontSize = 18.px
          fontWeight = FontWeight.bold
          border(1.px, BorderStyle.solid, colorF)
        }
      }
    }
  }
  func()
}

private fun CSSBuilder.addIcon() {
  position = Position.absolute
  display = Display.inlineBlock
  top = 50.pct
  left = 50.pct
  transform { translate((-50).pct, (-50).pct) }
  cursor = Cursor.pointer
}

fun RBuilder.productPeek(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginTop = 5.px
    children { display = Display.inlineBlock }
    borderTop(1.px, BorderStyle.solid, colorH)
    marginRight = 24.px
    lineHeight = LineHeight(50.px.value)
    paddingTop = 20.px
    marginBottom = 20.px
    child(":nth-child(2)") {
      width = 60.pct
    }
    child(":nth-child(4)") {
      float = Float.right
      color = colorA
    }
    children {
      fontWeight = FontWeight.bold
      fontSize = 14.px
    }
  }
  func()
}

fun RBuilder.prodImg(src: String) = styledDiv {
  css {
    width = 50.px
    height = 50.px
    marginRight = 20.px
    background = "url('$src') no-repeat center"
    backgroundSize = "contain"
    verticalAlign = VerticalAlign.middle
  }
}

fun RBuilder.addressInfo(checked: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    child(":nth-child(5)") {
      position = Position.absolute
      display = Display.inlineBlock
      width = 30.px
      height = 30.px
      top = 20.px
      right = 25.px
      borderRadius = 50.pct
      backgroundColor = if (checked) colorA else colorF
      cursor = Cursor.pointer
      ::before {
        content = QuotedString("")
        width = 30.px
        height = 30.px
        background = "url('/imgs/icon-gou.png') no-repeat center"
        backgroundSize = "contain"
        position = Position.absolute
        display = Display.inlineBlock
      }
    }
    position = Position.relative
    boxSizing = BoxSizing.borderBox
    float = Float.left
    width = 271.px
    height = 180.px
    if (checked)
      border(1.px, BorderStyle.solid, colorA)
    else
      border(1.px, BorderStyle.solid, colorH)
    marginRight = 15.px
    padding(15.px, 24.px)
    fontSize = 14.px
    color = Color("#757575")
    child(":nth-child(1)") {
      height = 27.px
      fontSize = 18.px
      fontWeight = FontWeight("300")
      color = colorB
      marginBottom = 10.px
    }
  }
  func()
}

fun RBuilder.phone(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  func()
}

fun RBuilder.street(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css { height = 50.px }
  func()
}

fun RBuilder.action(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    height = 20.px
    lineHeight = LineHeight(height.value)
    marginTop = 10.px
    children {
      transition("all", 0.5.s)
      hover {
        transform { scale(1.1) }
        transition("all", 0.5.s)
      }
    }
  }
  func()
}

fun RBuilder.addressDel(func: StyledDOMBuilder<A>.() -> Unit) = styledA {
  css {
    icon()
    background = "url('/imgs/waste.svg') no-repeat center"
    backgroundSize = "cover"
  }
  func()
}

fun RBuilder.addressEdit(func: StyledDOMBuilder<A>.() -> Unit) = styledA {
  css {
    icon()
    background = "url('/imgs/pencil.svg') no-repeat center"
    backgroundSize = "cover"
    float = Float.right
  }
  func()
}

private fun CSSBuilder.icon() {
  display = Display.inlineBlock
  width = 30.px
  height = 30.px
  verticalAlign = VerticalAlign.middle
}

fun RBuilder.addAddress(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    width = 271.px
    height = 180.px
    float = Float.left
    border(1.px, BorderStyle.solid, colorH)
    position = Position.relative
    child("div") {
      addIcon()
      width = 30.px
      height = 30.px
      backgroundColor = Color("#E0E0E0")
      borderRadius = 50.pct
      ::before {
        addIcon()
        width = 14.px
        height = 14.px
        zIndex = 1
        background = "url('/imgs/icon-add.png') no-repeat center"
        backgroundSize = "cover"
      }
    }
  }
  func()
}

fun RBuilder.addressContent(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginRight = 24.px
    marginTop = 20.px
    marginBottom = 37.px
    border = "none"
    borderTop(1.px, BorderStyle.solid, colorH)
    borderBottom(1.px, BorderStyle.solid, colorH)
    child("div span") { display = Display.inlineBlock }
    child("div div span") { display = Display.inlineBlock }
  }
  func()
}

private fun CSSBuilder.blackFont() {
  fontSize = 20.px
  fontWeight = FontWeight("500")
  color = colorB
}

private fun CSSBuilder.themeFont() {
  fontWeight = FontWeight.bold
  color = colorA
  fontSize = 16.px
}

fun RBuilder.deliverMethod(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginTop = 31.px
    child(":nth-child(1)") { blackFont() }
    child(":nth-child(2)") { themeFont(); marginLeft = 71.px }
  }
  func()
}

fun RBuilder.invoice(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginTop = 48.px
    children { themeFont() }
    child(":nth-child(1)") { blackFont() }
    child(":nth-child(2)") { marginLeft = 112.px }
    child(":nth-child(3)") { marginLeft = 23.px }
    child(":nth-child(4)") { marginLeft = 24.px }
  }
  func()
}

fun RBuilder.productInfo(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    children { marginBottom = 5.px }
    child("div") { lastChild { marginBottom = 0.px } }
    child("div :nth-child(1)") {
      width = 200.px
      fontSize = 16.px
      color = colorC
      textAlign = TextAlign.right
      marginRight = 50.px
    }
    child("div :nth-child(2)") {
      fontSize = 16.px
      color = colorA
      float = Float.right
    }
  }
  func()
}

fun RBuilder.totalPrice(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginTop = 21.px
    lineHeight = LineHeight(24.px.value)
    marginBottom = 33.px
    height = 24.px
    child(":nth-child(1)") {
      width = 200.px
      fontSize = 16.px
      color = colorC
      textAlign = TextAlign.right
      marginRight = 50.px
    }
    child(":nth-child(3)") {
      fontSize = 24.px
      color = colorA
      fontWeight = FontWeight.bold
      float = Float.right
      marginRight = 3.px
    }
    child(":nth-child(2)") {
      fontSize = 16.px
      color = colorA
      float = Float.right
    }
  }
  func()
}

fun RBuilder.editWrap(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    fontSize = 14.px
    child(":nth-child(2)") {
      children { marginRight = 20.px }
    }
  }
  func()
}

fun RBuilder.editItem(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginBottom = 15.px
    child("input") {
      display = Display.inlineBlock
      width = 280.px
      sibling("input") { marginLeft = 20.px }
    }
  }
  func()
}


