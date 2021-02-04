package components.nav_footer

import react.RBuilder
import react.RProps
import react.child
import react.dom.a
import react.dom.img
import react.dom.p
import react.dom.span
import react.functionalComponent

private val navFooter = functionalComponent<RProps> {
  footerWrapper {
    footerLogo {
      img { attrs.src = "/imgs/logo-footer.png" }
      p {  }

    }
    footerLink {
      a { attrs.href = "#";  +"咸鱼一条" };
      a { attrs.href = "#";  +"风华浪子" };
      a { attrs.href = "#";  +"百度FE" }
      a { attrs.href = "#";  +"创业者" }
    }
    copyright { +"CopyRight ©2020 "; span { +"TheBIGMountain" }; +" All Rights Reserved." }
  }
}

fun RBuilder.navFooter() = child(navFooter)