package style

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import styled.StyleSheet
import styled.StyledComponents
import styled.injectGlobal

fun globalStyle() = StyledComponents.injectGlobal {
  html { htmlAndBodyDefaultStyle() }
  body { htmlAndBodyDefaultStyle() }
  ul { listStyleType = ListStyleType.none }
  a { textDecoration = TextDecoration.none; ::focus { outline = Outline.none } }
  input { fontStyle = FontStyle.normal; ::focus { outline = Outline.none } }
}

private fun CSSBuilder.htmlAndBodyDefaultStyle() {
  color = colorB
  minWidth = defaultWidth
  backgroundColor = colorG
  fontSize = fontK
}

object MallStyle: StyleSheet("mall", isStatic = true) {
  val container by css {
    width = defaultWidth
    margin(0.px, LinearDimension.auto)
  }
}
