package ajax.shipping

import ajax.Method
import ajax.ajax
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.js.json

/**
 * 地址信息
 */
interface Shipping {
  var id: Int
  var receiverName: String
  var receiverPhone: String
  var receiverMobile: String
  var receiverProvince: String
  var receiverCity: String
  var receiverDistrict: String
  var receiverAddress: String
  var receiverZip: String
}

/**
 * 添加地址
 */
suspend fun Shipping.addShippings(): Flow<Int> {
  return Method.POST.ajax<dynamic>("/shippings", json(
    "receiverName" to receiverName,
    "receiverPhone" to receiverMobile,
    "receiverMobile" to receiverMobile,
    "receiverProvince" to receiverProvince,
    "receiverCity" to receiverCity,
    "receiverDistrict" to receiverDistrict,
    "receiverAddress" to receiverAddress,
    "receiverZip" to receiverZip
  )).map { it.shippingId }
}

/**
 * 更新地址
 */
suspend fun Shipping.updateShipping(): Flow<Unit>
        = Method.PUT.ajax("/shippings/$id", json(
  "receiverName" to receiverName,
  "receiverPhone" to receiverMobile,
  "receiverMobile" to receiverMobile,
  "receiverProvince" to receiverProvince,
  "receiverCity" to receiverCity,
  "receiverDistrict" to receiverDistrict,
  "receiverAddress" to receiverAddress,
  "receiverZip" to receiverZip
))

/**
 * 获取地址列表
 */
suspend fun shippings(): Flow<Array<Shipping>>
= Method.GET.ajax<dynamic>("/shippings").map { it.content }

/**
 * 删除地址
 */
suspend fun Int.delShipping(): Flow<Unit>
= Method.DELETE.ajax("/shippings/$this")

/**
 * 获取地址详情
 */
suspend fun Int.shippingInfo(): Flow<Shipping>
= Method.GET.ajax("/shippings/$this")