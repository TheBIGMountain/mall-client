package components.loading

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import react.RBuilder
import react.RProps
import react.child
import react.dom.img
import react.functionalComponent
import styled.css
import styled.styledDiv

private interface LoadingProps: RProps {
  var isShow: Boolean
}

private val loading = functionalComponent<LoadingProps> { props ->
  if (props.isShow) {
    styledDiv {
      css {
        height = 80.px
        lineHeight = LineHeight(height.value)
        textAlign = TextAlign.center
        margin(30.px, 0.px)
        child("img") {
          height = 100.pct
        }
      }
      img { attrs { src = "/imgs/loading-svg/loading-bars.svg" } }
    }
  }
}

fun RBuilder.loading(isShow: Boolean) = child(loading) { attrs.isShow = isShow }

