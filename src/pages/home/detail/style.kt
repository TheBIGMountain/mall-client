package pages.home.detail

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

fun RBuilder.detailWrapper(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    width = 100.pct
    overflow = Overflow.hidden
    child("div") { +MallStyle.container }
    child("div .swiper-container") {
      display = Display.inlineBlock
      width = 553.px
      height = 486.px
      marginTop = 50.px
      child("div div img") {
        width = 100.pct
        height = 100.pct
      }
      child(".swiper-button-prev") {
        color = colorD
      }
      child(".swiper-button-prev::after") {
        fontSize = 30.px
      }
      child(".swiper-button-next") {
        color = colorD
      }
      child(".swiper-button-next::after") {
        fontSize = 30.px
      }
    }
  }
  func()
}


fun RBuilder.productInfo(version: Int, func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.inlineBlock
    width = 590.px
    height = LinearDimension.auto
    float = Float.right
    child(":nth-child(1)") {
      marginTop = 30.px
      fontSize = 28.px
      fontWeight = FontWeight.bold
      color = colorB
    }
    child(":nth-child(2)") {
      marginTop = 16.px
      fontSize = 14.px
      fontWeight = FontWeight.bold
      color = colorD
    }
    child(":nth-child(3)") {
      marginTop = 26.px
      fontSize = 16.px
      color = colorA
      fontWeight = FontWeight.bold
    }
    child(":nth-child(4)") {
      marginTop = 14.px
      fontSize = 20.px
      fontWeight = FontWeight.bold
      color = colorA
      ::after {
        content = QuotedString("")
        display = Display.block
        width = 100.pct
        height = 1.px
        border = "none"
        marginTop = 25.px
        borderTop(1.px, BorderStyle.solid, colorH)
      }
    }
    child(":nth-child(5)") {
      marginTop = 28.px
      width = 100.pct
      height = 108.px
      backgroundColor = Color("#FAFAFA")
      child(":nth-child(1)") {
        ::before {
          content = QuotedString("")
          display = Display.inlineBlock
          backgroundImage = Image("url('/imgs/detail/icon-loc.png')")
          width = 20.px
          height = 22.px
          backgroundSize = "cover"
          marginLeft = 34.px
          marginRight = 10.px
          verticalAlign = VerticalAlign.bottom
        }
        marginTop = 31.px
        display = Display.inlineBlock
        marginRight = 20.px
      }
      child(":nth-child(2)") {
        marginTop = 31.px
        display = Display.inlineBlock
        color = colorA
      }
      child(":nth-child(3)") {
        marginTop = 15.px
        marginLeft = 64.px
        color = colorA
      }
    }
    child(":nth-child(6)") {
      marginTop = 30.px
      fontSize = 18.px
      fontWeight = FontWeight.bold
      color = colorB
    }
    fun CSSBuilder.btn() {
      child(":nth-child(1)") {
        display = Display.inlineBlock
        width = 287.px
        lineHeight = LineHeight(50.px.value)
        border(1.px, BorderStyle.solid, colorA)
        textAlign = TextAlign.center
        fontSize = 16.px
        fontWeight = FontWeight.bold
        color = colorA
      }
      child(":nth-child(2)") {
        display = Display.inlineBlock
        width = 287.px
        lineHeight = LineHeight(50.px.value)
        border(1.px, BorderStyle.solid, colorA)
        textAlign = TextAlign.center
        fontSize = 16.px
        fontWeight = FontWeight.bold
        color = colorC
        paddingLeft = 10.px
      }
    }
    child(":nth-child(7)") {
      marginTop = 20.px
      width = 100.pct
      height = 50.px
      btn()
      child(":nth-child(1)") {
        marginRight = 10.px
      }
      if (version == 1) {
        child(":nth-child(2)") {
          border(1.px, BorderStyle.solid, colorD)
        }
      }
      else {
        child(":nth-child(1)") {
          border(1.px, BorderStyle.solid, colorD)
        }
      }
    }
    child(":nth-child(8)") {
      marginTop = 30.px
      fontSize = 18.px
      fontWeight = FontWeight.bold
      color = colorB
    }
    child(":nth-child(9)") {
      marginTop = 20.px
      height = 50.px
      width = 100.pct
      btn()
      child("a span") {
        display = Display.inlineBlock
        width = 12.px
        height = 12.px
        backgroundColor = colorC
      }
    }
    child(":nth-child(10)") {
      marginTop = 50.px
      width = 100.pct
      height = 108.px
      backgroundColor = Color("#FAFAFA")
      child("div") { paddingLeft = 30.px }
      child(":nth-child(1)") {
        paddingTop = 24.px
        height = 14.px
        fontSize = 14.px
        fontWeight = FontWeight.bold
        lineHeight = LineHeight(height.value)
        color = colorB
        child("span") { display = Display.inlineBlock }
        child(":nth-child(1)") { marginRight = 206.px }
      }
      child(":nth-child(2)") {
        marginTop = 18.px
        paddingTop = 18.px
        height = 23.px
        fontSize = 24.px
        fontWeight = FontWeight.bold
        lineHeight = LineHeight(height.value)
        color = colorA
      }
    }
    child(":nth-child(11)") {
      marginTop = 30.px
      marginBottom = 50.px
      width = 100.pct
      height = 54.px
      child("a") {
        height = 100.pct
        lineHeight = LineHeight(54.px.value)
        fontSize = 16.px
        fontWeight = FontWeight.bold
      }
      child(":nth-child(1)") {
        width = 300.px
        marginRight = 21.px
      }
      child(":nth-child(2)") {
        width = 142.px
      }
    }
  }
  func()
}


fun RBuilder.priceDescription(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    width = 100.pct
    backgroundColor = Color("#F3F3F3")
    height = 340.px
    child("div div") {
      marginBottom = 30.px
      paddingTop = 38.px
      fontSize = fontE
      fontWeight = FontWeight.bold
    }
  }
  func()
}