package ajax.order

import ajax.Method
import ajax.ajax
import ajax.shipping.Shipping
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.js.json

interface Order {
  var orderNo: String
  var payment: Double
  var paymentType: Int
  var paymentTypeDesc: String
  var postage: Double
  var status: Int
  var statusDesc: String
  var paymentTime: String
  var sendTime: String
  var endTime: String
  var closeTime: String
  var createTime: String
  var orderItemVoList: Array<OrderItem>
  var imageHost: String
  var shippingId: Int
  var shippingVo: Shipping
}

interface OrderItem {
  var orderNo: String
  var productId: Long
  var productName: String
  var productImage: String
  var currentUnitPrice: Double
  var quantity: Int
  var totalPrice: Double
  var createTime: String
}


/**
 * 获取所有订单
 */
suspend fun orders(pageNum: Int, pageSize: Int): Flow<Array<Order>> {
  return Method.GET.ajax<dynamic>("/orders?pageNum=$pageNum&pageSize=$pageSize").map { it.content }
}

/**
 * 根据订单号查询订单
 */
suspend fun String.orderDetail(): Flow<Order> {
  return Method.GET.ajax("/orders/$this")
}

/**
 * 创建订单
 */
suspend fun Int.createOrder(): Flow<Order> {
  return Method.POST.ajax("/orders", json(
    "shippingId" to this
  ))
}