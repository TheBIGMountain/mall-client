package ajax.pay

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


enum class PayType {
  ALIPAY_PC, WXPAY_NATIVE
}

suspend fun String.pay(payType: PayType): Flow<String> {
  return flowOf(launchPay("/pay/create?orderId=$this&amount=0.01&payType=$payType"))
}

private suspend fun launchPay(url: String): String {
  return window.fetch("http://localhost:3000$url").await().text().await()
}