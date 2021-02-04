package ajax.cart


import ajax.Method
import ajax.ajax
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import store.Dispatch
import store.KAction
import kotlin.js.json

/**
 * 购物车信息
 */
interface Cart {
  var cartProductVoList: Array<ProductInCart>
  var cartTotalQuantity: Int
  var selectAll: Boolean
  var cartTotalPrice: Long
}

/**
 * 购物车里的商品信息
 */
interface ProductInCart {
  var productId: Int
  var productMainImage: String
  var productName: String
  var productPrice: Int
  var productStatus: Int
  var productStock: Int
  var productSubtitle: String
  var productTotalPrice: Long
  var productSelected: Boolean
  var quantity: Int
}

/**
 * 获取购物车数量
 */
suspend fun getCardCount(dispatch: Dispatch): Flow<Int> {
  return Method.GET.ajax<Int>("/carts/products/sum", showMsg = false)
    .onEach { dispatch(KAction.ChangeCartCount(it)) }
}

/**
 * 更新购物车商品数据
 */
suspend fun Int.updateProduct(quantity: Int, selected: Boolean): Flow<Cart> {
  return Method.PUT.ajax("/carts/$this", json(
    "quantity" to quantity,
    "selected" to selected
  ))
}

/**
 * 删除购物车中商品
 */
suspend fun Int.deleteProduct(): Flow<Cart> {
  return Method.DELETE.ajax("/carts/$this")
}

/**
 * 购物车中商品全选
 */
suspend fun selectAll(): Flow<Cart> {
  return Method.PUT.ajax("/carts/selectAll")
}

/**
 * 购物车中商品非全选
 */
suspend fun unSelectAll(): Flow<Cart> {
  return Method.PUT.ajax("/carts/unSelectAll")
}

/**
 * 获取购物车信息
 */
suspend fun getCarts(): Flow<Cart> {
  return Method.GET.ajax("/carts")
}

/**
 * 添加到购物车
 */
suspend fun Int.addToCart(): Flow<Cart> {
  return Method.POST.ajax("/carts", json(
    "productId" to this
  ))
}