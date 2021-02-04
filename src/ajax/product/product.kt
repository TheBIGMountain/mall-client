package ajax.product

import ajax.Method
import ajax.ajax
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


interface Product {
  var id: Int
  var name: String
  var mainImage: String
  var subtitle: String
  var price: Int
}

/**
 * 获取商品信息
 */
suspend fun Int.getProductInfo(): Flow<Product> {
  return Method.GET.ajax("/products/detail/$this")
}

/**
 * 获取商品列表
 */
suspend fun getProductList(pageNum: Int = 1): Flow<Array<Product>> {
  return Method.GET.ajax<dynamic>("/products?categoryId=100012&pageNum=$pageNum").map { it.content }
}