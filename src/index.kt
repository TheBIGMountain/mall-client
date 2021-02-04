
import kotlinext.js.require
import kotlinx.browser.document
import modules.*
import react.dom.render
import react.redux.provider
import store.store
import style.globalStyle

fun main() {
  require("antd/dist/antd.css")
  require("swiper/swiper.min.css")
  require("swiper/components/navigation/navigation.min.css")
  require("swiper/components/pagination/pagination.min.css")
  require("swiper/components/effect-cube/effect-cube.min.css")
  require("swiper/components/effect-coverflow/effect-coverflow.min.css")
  require("swiper/components/scrollbar/scrollbar.min.css")
  globalStyle()
  SwiperCore.use(arrayOf(vavigation, autoplay, pagination, effectCube, effectCoverflow, scrollbar))

  render(document.getElementById("root")) {
    provider(store) {
      app()
    }
  }
}
