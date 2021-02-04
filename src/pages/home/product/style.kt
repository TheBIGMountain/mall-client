package pages.home.product

import components.modal.position
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.DIV
import kotlinx.html.VIDEO
import react.RBuilder
import style.*
import styled.*

fun RBuilder.videoImg(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    width = 100.pct
    height = 1044.px
    textAlign = TextAlign.center
    backgroundColor = Color("#070708")
    marginBottom = 30.px
    child("img") {
      +MallStyle.container
      height = 540.px
      cursor = Cursor.pointer
    }
    child(":nth-child(1)") {
      paddingTop = 82.px
      fontSize = 60.px
      fontWeight = FontWeight.bold
      color = colorG
    }
    child(":nth-child(2)") {
      fontSize = 60.px
      fontWeight = FontWeight.bold
      color = colorG
    }
    child(":nth-child(3)") {
      paddingTop = 47.px
      fontSize = fontE
      fontWeight = FontWeight.bold
      color = colorG
    }
    child(":nth-child(4)") {
      fontSize = fontE
      fontWeight = FontWeight.bold
      color = colorG
      marginBottom = 58.px
    }
  }
  func()
}

fun RBuilder.videoBox(show: Boolean, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    if (!show) {
      visibility = Visibility.hidden
      children {
        lastChild {
          top = (-100).pct
          transition("all", 0.5.s)
        }
      }
    }
    else {
      visibility = Visibility.visible
      children {
        lastChild {
          top = 40.pct
          transition("all", 0.5.s)
        }
      }
    }
    transition("all", 0.5.s)
    position = Position.fixed
    zIndex = 20
  }
  func()
}

fun RBuilder.overlay(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    position(Position.fixed)
    backgroundColor = colorB
    opacity = 0.4
  }
  func()
}

fun RBuilder.videoWrapper(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    position(Position.fixed)
    display = Display.inlineBlock
    left = 50.pct
    transform { translate((-50).pct, (-50).pct) }
    width = 1000.px
    height = 536.px
    child("span") {
      display = Display.inlineBlock
      width = 35.px
      height = 35.px
      borderRadius = 50.pct
      backgroundColor = Color.red
      backgroundImage = Image("url('/imgs/icon-close.png')")
      backgroundSize = "25px 25px"
      backgroundRepeat = BackgroundRepeat.noRepeat
      backgroundPosition = "center"
      position = Position.absolute
      top = 20.px
      right = 20.px
      cursor = Cursor.pointer
      zIndex = 13
    }
  }
  func()
}

fun RBuilder.videoSty(func: StyledDOMBuilder<VIDEO>.() -> Unit) = styledVideo {
  css {
    width = 100.pct
    height = 100.pct
    objectFit = ObjectFit.cover
    outline = Outline.none
  }
  func()
}

fun RBuilder.bigImage1(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    width = 100.pct
    height = 718.px
    backgroundImage = Image("url('/imgs/product/product-bg-1.png')")
    backgroundSize = "cover"
    backgroundPosition = "center"
    textAlign = TextAlign.center
    child(":nth-child(1)") {
      opacity = 0
      height = 20.px
    }
    child(":nth-child(2)") {
      fontSize = fontA
      fontWeight = FontWeight.bold
      color = colorB
      marginBottom = 13.px
    }
    child(":nth-child(3)") {
      fontSize = fontE
      fontWeight = FontWeight.bold
      color = colorB
      marginBottom = 21.px
    }
    child(":nth-child(4)") {
      marginBottom = 40.px
      fontSize = fontI
      color = colorB
      child("span") {
        display = Display.inlineBlock
        marginRight = 15.px
        lastChild { marginRight = 0.px }
      }
    }
    child(":nth-child(5)") {
      fontSize = fontC
      fontWeight = FontWeight.bold
      color = colorB
    }
  }
  func()
}

fun RBuilder.bigImage2() = styledDiv {
  css {
    +MallStyle.container
    height = 397.px
    backgroundImage = Image("url('/imgs/product/product-bg-2.png')")
    backgroundSize = "cover"
    backgroundPosition = "center"
    margin(38.px, LinearDimension.auto, 45.px)
  }
}

fun RBuilder.bigImage3() = styledDiv {
  css {
    width = 100.pct
    height = 638.px
    backgroundImage = Image("url('/imgs/product/product-bg-3.png')")
    backgroundSize = "cover"
    backgroundPosition = "center"
  }
}

fun RBuilder.productSwiper(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginTop = 35.px
    height= LinearDimension.auto
    width = 100.pct
    marginBottom = 52.px
    child("div") {
      marginBottom = 57.px
    }
    child("div div div img") {
      width = 100.pct
    }
    child("div div div") {
      margin(0.px, LinearDimension.auto, 0.px)
    }
  }
  func()
}

fun RBuilder.productSwiperBottom(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    height = 18.px
    textAlign = TextAlign.center
    fontSize = fontH
    fontWeight = FontWeight.bold
    color = colorB
  }
  func()
}