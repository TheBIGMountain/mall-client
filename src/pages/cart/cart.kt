package pages.cart

import ajax.cart.*
import antd.message.message
import components.modal.btnLarge
import components.nav_footer.navFooter
import components.order_header.orderHead
import components.service_bar.serviceBar
import kotlinext.js.jsObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.html.js.onClickFunction
import modules.History
import modules.useHistory
import react.*
import react.dom.*

private fun getCardList(setCart: (Cart) -> Unit, setCheckedNum: (Int) -> Unit)
= MainScope().launch {
  getCarts().onEach { setCart(it) }.withSetCheckedNum(setCheckedNum).collect()
}

private fun Flow<Cart>.withSetCheckedNum(setCheckedNum: (Int) -> Unit): Flow<Cart> {
  return onEach { setCheckedNum(it.cartProductVoList.filter(ProductInCart::productSelected).count()) }
}

private fun toggleAll(selectALl: Boolean, setCart: (Cart) -> Unit, setCheckedNum: (Int) -> Unit)
= MainScope().launch {
  if (selectALl)
    selectAll().onEach { setCart(it) }.withSetCheckedNum(setCheckedNum).collect()
  else
    unSelectAll().onEach { setCart(it) }.withSetCheckedNum(setCheckedNum).collect()
}

private fun updateCart(product: ProductInCart, setCart: (Cart) -> Unit, type: ChangeCartType,
                       setCheckedNum: (Int) -> Unit)
= MainScope().launch {
  when(type) {
    ChangeCartType.ADD -> {
      if (product.quantity > product.productStock) message.info("待购买商品不能大于库存")
      else product.productId
        .updateProduct(product.quantity + 1, product.productSelected)
        .onEach { setCart(it) }.withSetCheckedNum(setCheckedNum).collect()
    }
    ChangeCartType.SUB -> {
      if (product.quantity == 1) message.info("商品数量必须大于一件")
      else product.productId
        .updateProduct(product.quantity - 1, product.productSelected)
        .onEach { setCart(it) }.withSetCheckedNum(setCheckedNum).collect()
    }
    ChangeCartType.SELECTED -> {
      product.productId
        .updateProduct(product.quantity, !product.productSelected)
        .onEach { setCart(it) }.withSetCheckedNum(setCheckedNum).collect()
    }
    ChangeCartType.DELETE -> {
      product.productId.deleteProduct()
        .withSetCheckedNum(setCheckedNum)
        .onEach { setCart(it) }.collect()
    }
  }
}

private enum class ChangeCartType {
  ADD, SUB, SELECTED, DELETE
}

private fun order(cart: Cart, history: History) = MainScope().launch {
  cart.cartProductVoList.asFlow()
    .filter { it.productSelected }
    .onEmpty { message.info("请至少选择一件商品") }
    .onEach { history.push("/order/confirm") }
    .collect()
}

private val cart = functionalComponent<RProps> {
  val (cart, setCart) = useState<Cart?>(null)
  val (checkedNum, setCheckedNum) = useState(0)
  val history = useHistory()
  useEffect(emptyList()) { getCardList(setCart, setCheckedNum) }

  orderHead("我的购物车") {
    span { +"温馨提示: 产品是否购买成功, 以最终下单为准哦, 请尽快结算" }
  }
  f5f5f5Bg {
    cartWrapper {
      cartContent {
        cartContentTitle {
          checkBox(cart?.selectAll ?: false) {
            attrs.onClickFunction = {
              if (cart != null) toggleAll(!cart.selectAll, setCart, setCheckedNum)
            }
          }
          span { +"全选"; }
          span { +"商品名称" }
          span { +"单价" }
          span { +"数量" }
          span { +"小计" }
          span { +"操作" }
        }
        cart?.cartProductVoList?.forEach { product ->
          cartProduct { attrs.key = product.productId.toString()
            checkBox(product.productSelected) { attrs.onClickFunction = { updateCart(product, setCart, ChangeCartType.SELECTED, setCheckedNum) } }
            img { attrs.src = product.productMainImage }
            span { +product.productName }
            span { +"${product.productPrice}元" }
            productSum {
              a { +"-"; attrs.onClickFunction = { updateCart(product, setCart, ChangeCartType.SUB, setCheckedNum) } }
              span { +product.quantity.toString() }
              a { +"+"; attrs.onClickFunction = { updateCart(product, setCart, ChangeCartType.ADD, setCheckedNum) } }
            }
            productPrice {  +"${product.productTotalPrice}元" }
            cancelProduct { attrs.onClickFunction = { updateCart(product, setCart, ChangeCartType.DELETE, setCheckedNum)  } }
          }
        }
      }
      cartBottom {
        div {
          span { +"继续购物" }
          span { +"|" }
          span { +"共"; span { +cart?.cartProductVoList?.size.toString() }; +"件商品, 已选择"; span { +checkedNum.toString() }; +"件" }
        }
        div {
          span { +"合计: "; span { +if (cart != null) "${cart.cartTotalPrice}" else "0" }; +"元" }
          btnLarge { +"去结算"; attrs.onClickFunction = { order(cart ?: jsObject {  }, history) } }
        }
      }
    }
  }
  serviceBar()
  navFooter()
}

fun RBuilder.cart() = child(cart)