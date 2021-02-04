package pages.home.index

import antd.form.form
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*
import react.RBuilder
import style.*
import styled.*



fun RBuilder.swiperBox(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    child(".swiper-container") {
      height = 451.px
      child("div div a img") {
        width = 100.pct
        height = 100.pct
      }
      child(".swiper-button-prev") {
        left = 274.px
      }
    }
  }
  func()
}

fun RBuilder.navMenu(func: StyledDOMBuilder<UL>.() -> Unit) = styledUl {
  css {
    position = Position.absolute
    width = 264.px
    height = 451.px
    zIndex = 9
    backgroundColor = Color("#55585a7a")
    paddingLeft = 0.px
  }
  func()
}

fun RBuilder.menuItem(func: StyledDOMBuilder<LI>.() -> Unit) = styledLi {
  css {
    height = 50.px
    paddingTop = 26.px
    lineHeight = LineHeight(50.px.value)
    child("a") {
      fontSize = 16.px
      color = colorG
      paddingLeft = 30.px
      display = Display.block
      position = Position.relative
      sibling("ul") {
        visibility = Visibility.hidden
        opacity = 0
        width = 0.px
      }
      hover {
        backgroundColor = colorA
        sibling("ul") {
          visibility = Visibility.visible
          opacity = 1
          width = 962.px
          transition("all", 0.5.s)
        }
      }
      ::after {
        position = Position.absolute
        right = 30.px
        top = 17.5.px
        display = Display.inlineBlock
        content = QuotedString("")
        backgroundImage = Image("url('/imgs/icon-arrow.png')")
        width = 10.px
        height = 15.px
        backgroundSize = "contain"
        backgroundRepeat = BackgroundRepeat.noRepeat
      }
    }
  }
  func()
}

fun RBuilder.menuChildren(func: StyledDOMBuilder<UL>.() -> Unit) = styledUl {
  css {
    hover {
      visibility = Visibility.visible
      opacity = 1
      width = 962.px
    }
    height = 451.px
    backgroundColor = colorG
    position = Position.absolute
    top = 0.px
    left = 264.px
    border(1.px, BorderStyle.solid, colorH)
    paddingLeft = 0.px
    child("li") {
      height = 75.px
      lineHeight = LineHeight(75.px.value)
      display = Display.flex
      paddingLeft = 23.px
      child("a") {
        flex(1.0)
        width = 200.px
        color = colorB
        fontSize = fontJ
        child("img") {
          width = 42.px
          height = 35.px
          verticalAlign = VerticalAlign.middle
          marginRight = 15.px
        }
      }
    }
  }
  func()
}

fun RBuilder.advWrap(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    marginTop = 14.px
    display = Display.flex
  }
  func()
}

fun RBuilder.advBox(img: String, func: StyledDOMBuilder<A>.() -> Unit) = styledA {
  css {
    display = Display.inlineBlock
    flex(1.0)
    height = 167.px
    backgroundImage = Image("url('$img')")
    backgroundSize = "cover"
    backgroundRepeat = BackgroundRepeat.noRepeat
    marginRight = 14.px
    lastChild { marginRight = 0.px }
  }
  func()
}

fun RBuilder.banner(func: StyledDOMBuilder<A>.() -> Unit) = styledA {
  css {
    +MallStyle.container
    display = Display.block
    marginBottom = 50.px
    marginTop = 31.px
    height = 130.px
    backgroundImage = Image("url('/imgs/banner-1.png')")
    backgroundRepeat = BackgroundRepeat.noRepeat
    backgroundSize = "contain"
  }
  func()
}

fun RBuilder.productBox(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    padding(30.px, 0.px, 50.px)
    child("h2") {
      fontSize = fontF
      height = 21.px
      lineHeight = LineHeight(21.px.value)
      color = colorB
      marginBottom = 20.px
    }
  }
  func()
}

fun RBuilder.bannerLeft(func: StyledDOMBuilder<A>.() -> Unit) = styledA {
  css {
    display = Display.inlineBlock
    float = Float.left
    backgroundImage = Image("url('/imgs/mix-alpha.jpg')")
    width = 224.px
    height = 619.px
    marginRight = 16.px
    backgroundSize = "contain"
  }
  func()
}

fun RBuilder.listBox(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.inlineBlock
    height = 619.px
  }
  func()
}

fun RBuilder.boxWrap(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginBottom = 14.px
  }
  func()
}

fun RBuilder.boxItem(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.inlineBlock
    width = 236.px
    height = 302.px
    marginRight = 14.px
    backgroundColor = colorG
    textAlign = TextAlign.center
    lastChild { marginRight = 0.px }
    child("span") {
      display = Display.inlineBlock
      marginTop = 5.px
      width = 67.px
      height = 24.px
      fontSize = 14.px
      lineHeight = LineHeight(24.px.value)
      color = colorG
    }
    child("span.new-pro") { backgroundColor = Color("#7ECF68") }
    child("span.kill-pro") { backgroundColor = Color("#E82626") }
  }
  func()
}

fun RBuilder.itemImg(func: StyledDOMBuilder<IMG>.() -> Unit) = styledImg {
  css {
    width = 100.pct
    height = 194.px
  }
  func()
}

fun RBuilder.itemInfo(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    child("h3") {
      fontSize = fontJ
      color = colorB
      lineHeight = LineHeight(fontJ.value)
      fontWeight = FontWeight.bold
    }
    child("p") {
      color = colorD
      lineHeight = LineHeight(13.px.value)
    }
  }
  func()
}

fun RBuilder.price(func: StyledDOMBuilder<SPAN>.() -> Unit) = styledSpan {
  css {
    color = Color("#F20A0A")
    fontSize = fontJ
    fontWeight = FontWeight.bold
    cursor = Cursor.pointer
    ::after {
      display = Display.inlineBlock
      content = QuotedString("")
      width = 22.px
      height = 22.px
      backgroundImage = Image("url('/imgs/icon-cart-hover.png')")
      backgroundRepeat = BackgroundRepeat.noRepeat
      backgroundSize = "contain"
      marginLeft = 5.px
      verticalAlign = VerticalAlign.middle
    }
  }
  func()
}