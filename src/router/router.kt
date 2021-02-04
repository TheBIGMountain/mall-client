package router

import pages.cart.cart
import pages.home.detail.productDetail
import pages.home.home
import pages.home.index.index
import pages.home.product.product
import pages.login.login
import pages.order.confirm.confirm
import pages.order.list.list
import pages.order.order
import pages.order.pay.alipay
import pages.order.pay.pay
import pages.register.register
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import react.router.dom.hashRouter
import react.router.dom.redirect
import react.router.dom.route
import react.router.dom.switch

private val router = functionalComponent<RProps> {
  hashRouter {
    switch {
      route("/login", render = { login() })
      route("/register", render = { register() })
      route("/cart",  render = { cart() })
      route("/order", render = {
        order {
          switch {
            route("/order/list", render = { list() })
            route("/order/pay/:orderNo", render = { pay() })
            route("/order/alipay/:orderNo", render = { alipay() })
            route("/order/confirm", render = { confirm() })
          }
        }
      })
      route("/", render = {
        home {
          switch {
            route("/index", render = { index() })
            route("/product/:id", render = { product() })
            route("/detail/:id", render = { productDetail() })
            redirect("/", "/index", exact = true)
          }
        }
      })
    }
  }
}

fun RBuilder.router() = child(router)
