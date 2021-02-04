package pages.home.product

import ajax.product.Product
import ajax.product.getProductInfo
import components.modal.btn
import components.product_param.productParam
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import modules.swiper
import modules.swiperSlide
import modules.useHistory
import modules.useParams
import react.*
import react.dom.div
import react.dom.img
import react.dom.span

private fun Int.productInfo(setProduct: (Product) -> Unit) = MainScope().launch {
  getProductInfo().onEach { setProduct(it) }.collect()
}


private val product = functionalComponent<RProps> {
  val (show, isShow) = useState(false)
  val (product, setProduct) = useState<Product>(jsObject { })
  val productId = useParams().id.unsafeCast<Int>()
  val history = useHistory()

  useEffect(emptyList()) { productId.productInfo(setProduct) }

  productParam("小米8") { btn { +"立即购买"; attrs.onClickFunction = { history.push("/detail/${product.id}") } } }
  bigImage1 {
    div { +"666" }
    div { +"小米8" }
    div { +product.subtitle }
    div {
      span { +"全球首款双频 GP" }; span { +"|" }
      span { +"骁龙845" }; span { +"|" }
      span { +"AI 变焦双摄" }; span { +"|" }
      span { +"红外人脸识别" }
    }
    div { +"￥${product.price}" }
  }
  bigImage2()
  bigImage3()
  productSwiper {
    swiper {
      attrs {
        autoplay = true
        loop = true
        slidesPerView = 3
        spaceBetween = 30
        freeMode = true
      }
      repeat(5) {
        swiperSlide { attrs.key = it.toString()
          img { attrs.src = "/imgs/product/gallery-${it + 2}.${if (it < 3) "png" else "jpg"}" }
        }
      }
    }
    productSwiperBottom { +"小米8 AI变焦双摄拍摄" }
  }
  videoImg {
    div { +"60帧超慢动作摄影" }
    div { +"慢慢回味每一瞬间的精彩" }
    div { +"后置960帧电影般超慢动作视频，将眨眼间的美妙展现得淋漓尽致！" }
    div { +"更能AI 精准分析视频内容，15个场景智能匹配背景音效。" }
    img { attrs.src = "/imgs/product/gallery-1.png"; attrs.onClickFunction = {
      isShow(true)
      document.getElementById("video").asDynamic().play().unsafeCast<Unit>()
    } }
    span {  }
  }
  videoBox(show) {
    overlay {  }
    videoWrapper {
      span { attrs.onClickFunction = {
        isShow(false)
        document.getElementById("video").asDynamic().pause().unsafeCast<Unit>()
      } }
      videoSty { attrs { id = "video"; src = "/imgs/product/video.mp4"; controls = true; autoPlay = true; set("muted", true) } }
    }
  }
}

fun RBuilder.product() = child(product)