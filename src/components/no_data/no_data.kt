package components.no_data

import kotlinx.css.*
import react.RBuilder
import react.RProps
import react.child
import react.dom.img
import react.dom.p
import react.functionalComponent
import style.colorD
import styled.css
import styled.styledDiv

private val noData = functionalComponent<RProps> {
  styledDiv {
    css {
      textAlign = TextAlign.center
      fontSize = 20.px
      color = colorD
      margin(50.px, 0.px)
      child("img") {
        width = 240.px
        height = 180.px
        marginBottom = 30.px
      }
    }
    img { attrs.src = "/imgs/icon-no-data.png" }
    p { +"当前暂无提交的订单记录" }
  }
}

fun RBuilder.noData() = child(noData)