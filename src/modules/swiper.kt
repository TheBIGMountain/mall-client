@file:JsModule("swiper")
package modules

@JsName("default")
external object SwiperCore {
  fun use(components: Array<dynamic>)
}

@JsName("Navigation")
external val vavigation: dynamic

@JsName("Pagination")
external val pagination: dynamic

@JsName("Autoplay")
external val autoplay: dynamic

@JsName("EffectCube")
external val effectCube: dynamic

@JsName("EffectCoverflow")
external val effectCoverflow: dynamic

@JsName("Scrollbar")
external val scrollbar: dynamic

