package modules

import kotlin.js.Promise

@JsModule("qrcode")
@JsNonModule
external object QRCode {
  fun toDataURL(url: String): Promise<String>
}

