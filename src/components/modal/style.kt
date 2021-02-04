package components.modal

import antd.form.form
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.A
import kotlinx.html.DIV
import react.RBuilder
import style.*
import styled.*

fun CSSBuilder.position(pos: Position = Position.absolute,
                                t: LinearDimension = 0.px,
                                l: LinearDimension = 0.px,
                                w: LinearDimension = 100.pct,
                                h: LinearDimension = 100.pct) {
  position = pos
  top = t
  left = l
  width = w
  height = h
}

fun CSSBuilder.positionImg(pos: Position = Position.absolute,
                           t: LinearDimension = 0.px,
                           r: LinearDimension = 0.px,
                           w: LinearDimension = 0.px,
                           h: LinearDimension = 0.px,
                           img: String) {
  position = pos
  top = t
  right = r
  width = w
  height = h
  backgroundImage = Image("url('$img')")
  backgroundRepeat = BackgroundRepeat.noRepeat
  backgroundSize = "contain"
}

fun RBuilder.modalSty(isShow: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    zIndex = 10
    position(Position.fixed)
    if (isShow) {
      visibility = Visibility.visible
      child(":nth-child(2)") {
        top = 30.pct
        transition("all", 0.5.s)
      }
    }
    else {
      visibility = Visibility.hidden
      child(":nth-child(2)") {
        top = (-100).pct
        transition("all", 0.5.s)
      }
    }
    transition("all", 0.5.s)
  }
  func()
}

fun RBuilder.mask(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    position(Position.fixed)
    backgroundColor = colorI
    opacity = 0.5
  }
  func()
}

fun RBuilder.dialog(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    position(t = 40.pct, l = 50.pct, w = 660.px, h = LinearDimension.auto)
    backgroundColor = colorG
    transform { translateX((-50).pct); translateY((-50).pct) }
    top = (-100).pct
  }
  func()
}

fun RBuilder.modalHeader(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    height = 60.px
    backgroundColor = colorJ
    padding(0.px, 25.px)
    lineHeight = LineHeight(60.px.value)
    fontSize = fontI
  }
  func()
}

fun RBuilder.modalBody(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    padding(42.px, 40.px, 54.px)
    fontSize = 14.px
  }
  func()
}

fun RBuilder.modalFooter(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    height = 82.px
    lineHeight = LineHeight(height.value)
    textAlign = TextAlign.center
    backgroundColor = colorJ
  }
  func()
}

fun RBuilder.iconClose(func: StyledDOMBuilder<A>.() -> Unit) = styledA {
  css {
    positionImg(t = 23.px, r = 25.px, w = 14.px, h = 14.px, img = "/imgs/icon-close.png")
    transition("transform", 0.4.s)
    hover {
      transform { scale(1.5) }
      transition("transform", 0.4.s)
    }
  }
  func()
}

fun RBuilder.btn(func: StyledDOMBuilder<A>.() -> Unit) = styledA {
  css {
    display = Display.inlineBlock
    width = 110.px
    height = 30.px
    lineHeight = LineHeight(height.value)
    textAlign = TextAlign.center
    backgroundColor = colorA
    color = colorG
    hover { color = colorG }
  }
  func()
}

fun RBuilder.btnDefault(func: StyledDOMBuilder<A>.() -> Unit) = btn {
  css {
    backgroundColor = Color("#B0B0B0")
    color = colorG
  }
  func()
}


fun RBuilder.btnLarge(func: StyledDOMBuilder<A>.() -> Unit) = btn {
  css {
    width = 202.px
    height = 50.px
    lineHeight = LineHeight(height.value)
    fontSize = fontH
  }
  func()
}

fun RBuilder.btnHuge(func: StyledDOMBuilder<A>.() -> Unit) = btn {
  css {
    width = 300.px
    height = 54.px
    lineHeight = LineHeight(height.value)
    fontSize = fontI
  }
  func()
}

fun RBuilder.btnGroup(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    child("a") {
      marginRight = 20.px
      color = colorG
      lastChild { marginRight = 0.px }
    }
  }
  func()
}

