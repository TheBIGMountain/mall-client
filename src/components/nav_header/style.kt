package components.nav_header

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*
import react.RBuilder
import style.*
import styled.*


fun RBuilder.topBar(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    height = 39.px
    lineHeight = LineHeight(39.px.value)
    child("div a") {
      display = Display.inlineBlock
      color = Color("#B0B0B0")
    }
  }
  func()
}

fun RBuilder.topBarMenu(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.inlineBlock
    child("a") { marginRight = 17.px }
  }
  func()
}

fun RBuilder.topBarUser(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    float = Float.right
    child("a") { marginLeft = 17.px }
  }
  func()
}

fun RBuilder.myCart(func: StyledDOMBuilder<A>.() -> Unit) = styledA {
  css {
    width = 110.px
    backgroundColor = colorA
    textAlign = TextAlign.center
    put("color", "${colorG.value} !important")
  }
  func()
}

fun RBuilder.iconCart(func: StyledDOMBuilder<SPAN>.() -> Unit) = styledSpan {
  css {
    display = Display.inlineBlock
    width = 16.px
    height = 12.px
    backgroundImage = Image("url('/imgs/icon-cart-checked.png')")
    backgroundRepeat = BackgroundRepeat.noRepeat
    backgroundSize = "contain"
    marginRight = 10.px
  }
  func()
}

fun RBuilder.bottomBar(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    position = Position.relative
    height = 112.px
    display = Display.flex
    alignItems = Align.center
  }
  func()
}

fun RBuilder.logo(func: StyledDOMBuilder<A>.() -> Unit) = styledA {
  attrs.href = "/#/index"
  css {
    display = Display.inlineBlock
    width = 110.px
    height = 55.px
    background = "linear-gradient(90deg, ${colorA.value} 0%, ${colorA.value} 50%, ${colorG.value} 51%, ${colorG.value} 100%)"
    ::before {
      image("'/imgs/mi-logo.png'")
      transition("margin", 0.2.s)
    }
    ::after { image("'/imgs/mi-home.png'") }
    hover {
      before {
        marginLeft = (-55).px
        transition("margin", 0.2.s)
      }
    }
  }
  func()
}

fun RBuilder.menu(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.inlineBlock
    paddingLeft = 209.px
    overflow = Overflow.hidden
  }
  func()
}

fun RBuilder.itemMenu(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.inlineBlock
    color = colorB
    fontWeight = FontWeight.bold
    fontSize = 16.px
    lineHeight = LineHeight(112.px.value)
    marginRight = 20.px
    child("ul") {
      opacity = 0
      visibility = Visibility.hidden
      hover {
        opacity = 1
        visibility = Visibility.visible
      }
    }
    child("span") {
      display = Display.inlineBlock
      cursor = Cursor.pointer
      sibling("ul") {
        height = 0.px
        opacity = 0
        visibility = Visibility.hidden
      }
      hover {
        color = colorA
        sibling("ul") {
          visibility = Visibility.visible
          height = 220.px
          opacity = 1
          transition("all", 0.5.s)
        }
        sibling("ul li a") {
          display = Display.inlineBlock
        }
      }
    }
  }
  func()
}

fun RBuilder.children(func: StyledDOMBuilder<UL>.() -> Unit) = styledUl {
  css {
    position = Position.absolute
    top = 112.px
    left = 0.px
    width = 1226.px
    height = 220.px
    borderTop(1.px, BorderStyle.solid, colorH)
    boxShadow(rgba(0, 0, 0, 0.11), 0.px, 7.px, 6.px, 0.px)
    zIndex = 10
    backgroundColor = colorG
    display = Display.flex
    child("li a") { display = Display.inlineBlock }
    hover { height = 220.px }
  }
  func()
}

fun RBuilder.product(func: StyledDOMBuilder<LI>.() -> Unit) = styledLi {
  css {
    flex(1.0)
    fontSize = 12.px
    lineHeight = LineHeight(12.px.value)
    marginLeft = (-40).px
    textAlign = TextAlign.center
    position = Position.relative
    ::after {
      position = Position.absolute
      top = 28.px
      right = 0.px
      borderLeft(1.px, BorderStyle.solid, colorF)
      height = 100.px
      width = 22.px
      content = QuotedString("")
    }
    lastChild { after { display = Display.none } }
  }
  func()
}

fun RBuilder.proImg(func: StyledDOMBuilder<IMG>.() -> Unit) = styledImg {
  css {
    height = 116.px
    width = LinearDimension.auto
    marginTop = 26.px
  }
  func()
}

fun RBuilder.proName(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    fontWeight = FontWeight.bold
    marginTop = 19.px
    marginBottom = 8.px
    color = colorB
  }
  func()
}
fun RBuilder.proPrice(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css { color = colorA }
  func()
}

fun RBuilder.searchWrapper(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    height = 50.px
    display = Display.flex
    alignItems = Align.center
    marginLeft = LinearDimension.auto
    width = 319.px
    border(1.px, BorderStyle.solid, Color("#E0E0E0"))
  }
  func()
}

fun RBuilder.search(func: StyledDOMBuilder<INPUT>.() -> Unit) = styledInput {
  css {
    border = "none"
    borderRight(1.px, BorderStyle.solid, Color("#E0E0E0"))
    borderBottom(1.px, BorderStyle.solid, Color("#E0E0E0"))
    borderTop(1.px, BorderStyle.solid, Color("#E0E0E0"))
    width = 264.px
    height = 50.px
    paddingLeft = 14.px
  }
  func()
}

fun RBuilder.searchIcon(func: StyledDOMBuilder<A>.() -> Unit) = styledA {
  css {
    display = Display.inlineBlock
    width = 18.px
    height = 18.px
    background = "url('/imgs/icon-search.png') no-repeat center"
    backgroundSize = "contain"
    marginLeft = 17.px
  }
  func()
}

private fun CSSBuilder.image(url: String) {
  content = QuotedString("")
  display = Display.inlineBlock
  width = 55.px
  height = 55.px
  backgroundImage = Image("url($url)")
  backgroundRepeat = BackgroundRepeat.noRepeat
  backgroundSize = "contain"
}