@file:JsModule("swiper/react")
package modules

import react.RClass
import react.RProps

@JsName("Swiper")
external val swiper: RClass<SwiperProps>

@JsName("SwiperSlide")
external val swiperSlide: RClass<SwiperSlideProps>

external interface SwiperProps: RProps {
  var onSwiper: (dynamic) -> dynamic
  var e1: dynamic
  var loop: dynamic
  var autoplay: dynamic
  var navigation: dynamic
  var pagination: dynamic
  var effect: dynamic
  var coverflowEffect: dynamic
  var slidesPerView: dynamic
  var scrollbar: dynamic
  var spaceBetween: dynamic
  var freeMode: dynamic
}

external interface SwiperSlideProps: RProps