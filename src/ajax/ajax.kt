package ajax


import antd.message.message
import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withTimeout
import org.w3c.fetch.INCLUDE
import org.w3c.fetch.RequestCredentials
import kotlin.js.Json
import kotlin.js.json

interface ResponseData<T> {
  var status: Int
  var msg: String
  var data: T
}

enum class Method(val method: String) {
  GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE")
}

suspend fun <T> Method.ajax(url: String, json: Json? = null, showMsg: Boolean = true)
: Flow<T> {
  return this.method.let { met ->
    // 超时设置
    withTimeout(8000) {
      // 发起异步请求
      window.fetch("http://localhost:3000/api$url", jsObject {
        method = met
        headers = json("Content-Type" to "application/json")
        credentials = RequestCredentials.INCLUDE
        if (json != null) body = JSON.stringify(json)
      }).await().json()
        // 获取结果
        .await().unsafeCast<ResponseData<T>>()
        // 转为flow
        .let { flowOf(it) }
        // 未登录非首页则跳回登录页面
        .onEach { if (it.status == 3 && window.location.hash != "#/index") window.location.href = "/#/login" }
        // 数据有误提示信息
        .onEach { if (it.status != 0 && showMsg) message.error(it.msg) }
        // 获取正常数据
        .filter { it.status == 0 }
        // 转换所需数据
        .map { it.data }
    }
  }
}

