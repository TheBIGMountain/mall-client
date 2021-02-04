package components.product_param

import kotlinx.browser.window
import org.w3c.dom.events.Event
import react.*
import react.dom.a
import react.dom.span
import style.MallStyle
import styled.css
import styled.styledDiv


private interface ProductParamProps: RProps {
  var content: RBuilder.() -> Unit
  var productName: String
}

private fun initHeight(isFixed: (Boolean) -> Unit): () -> Unit {
  val initHeight = { _: Event ->
    window.pageYOffset.let {
      if (it > 152) isFixed(true) else isFixed(false)
    }
  }
  window.addEventListener("scroll", initHeight)
  return { window.removeEventListener("scroll", initHeight) }
}

private val productParam = functionalComponent<ProductParamProps> { props ->
  val (fixed, isFixed) = useState(false)

  useEffectWithCleanup(emptyList()) { initHeight(isFixed) }

  navBar(fixed) {
    styledDiv { css { +MallStyle.container }
      proTitle { +props.productName }
      proParam {
        a { +"概述" }; span { +"|" }
        a { +"参数" }; span { +"|" }
        a { +"用户评价" }
        props.content(this)
      }
    }
  }
}

fun RBuilder.productParam(name: String, func: RBuilder.() -> Unit)
= child(productParam) { attrs { productName = name; content = func } }