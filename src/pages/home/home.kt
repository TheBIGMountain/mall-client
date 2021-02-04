package pages.home

import components.nav_footer.navFooter
import components.nav_header.navHeader
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent

private interface HomeProps: RProps {
  var content: RBuilder.() -> Unit
}

private val home = functionalComponent<HomeProps> { props ->
  navHeader()
  props.content(this)
  navFooter()
}

fun RBuilder.home(content: RBuilder.() -> Unit) = child(home) { attrs.content = content }