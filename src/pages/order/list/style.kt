package pages.order.list

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.html.DIV
import react.RBuilder
import style.*
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

fun RBuilder.orderData(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.flex
    height = 74.px
    alignItems = Align.center
    backgroundColor = Color("#FFFAF7")
    children {
      color = colorC
      fontWeight = FontWeight.bold
      fontSize = 16.px
      lastChild {
        marginLeft = LinearDimension.auto
        height = 40.px
        lineHeight = LineHeight(height.value)
        child(":nth-child(2)") {
          display = Display.inlineBlock
          marginBottom = 0.px
          fontSize = 26.px
          color = colorB
          marginLeft = 20.px
          marginRight = 5.px
        }
      }
      firstChild {
        arrayOf(2, 4, 6).forEach {
          child(":nth-child($it)") {
            margin(0.px, 9.px)
          }
        }
      }
    }
  }
  func()
}

fun RBuilder.orderItem(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    +MallStyle.container
    backgroundColor = colorG
    marginBottom = 31.px
    children { padding(0.px, 44.px) }
  }
  func()
}

fun RBuilder.productData(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    display = Display.flex
    alignItems = Align.center
    height = 145.px
    child("img") {
      width = 69.px
      marginRight = 45.px
    }
    child(":nth-child(2)") {
      fontSize = 20.px
      fontWeight = FontWeight.bold
      color = colorB
      lineHeight = LineHeight(40.px.value)
      children {
        child("span") { margin(0.px, 5.px) }
      }
    }
  }
  func()
}

fun RBuilder.atOnce(func: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
  css {
    marginLeft = LinearDimension.auto
    color = colorA
    fontWeight = FontWeight.bold
    fontSize = 20.px
    lineHeight = LineHeight(60.px.value)
    child("img") {
      width = 11.px
      height = 15.px
      marginLeft = 9.px
    }
  }
  func()
}