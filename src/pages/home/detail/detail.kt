package pages.home.detail

import ajax.cart.addToCart
import ajax.product.Product
import ajax.product.getProductInfo
import components.modal.btn
import components.modal.btnDefault
import components.product_param.productParam
import kotlinext.js.jsObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.html.js.onClickFunction
import modules.*
import react.*
import react.dom.a
import react.dom.div
import react.dom.img
import react.dom.span
import store.Dispatch
import store.KAction
import style.MallStyle
import styled.css
import styled.styledDiv

private fun Int.productInfo(setProduct: (Product) -> Unit) = MainScope().launch {
  getProductInfo().onEach { setProduct(it) }.collect()
}

private fun Int.addCart(history: History, dispatch: Dispatch) = MainScope().launch {
  addToCart().onEach { history.push("/cart") }
    .onEach { dispatch(KAction.ChangeCartCount(it.cartTotalQuantity)) }.collect()
}

private val productDetail = functionalComponent<RProps> {
  val (product, setProduct) = useState<Product?>(null)
  val (version, setVersion) = useState(1)
  val productId = useParams().id.unsafeCast<Int>()
  val history = useHistory()
  val dispatch = useDispatch()

  useEffect(emptyList()) { productId.productInfo(setProduct) }

  productParam("小米8") { a {  } }
  detailWrapper {
    div {
      if (product != null) {
        productInfo(version) {
          div { +"小米8" }
          div { +"相机全新升级 / 960帧超慢动作 / 手持超级夜景 / 全球首款双频GPS / 骁龙845处理器 / 红 外人脸解锁 / AI变焦双摄 / 三星 AMOLED 屏" }
          div { +"小米自营" }
          div { +"${product.price}元" }
          div {
            div { +"北京 北京市 朝阳区 安定门街道" }
            a { +"修改" }
            div { +"有现货" }
          }
          div { +"选择版本" }
          div {
            a { attrs.onClickFunction = { setVersion(1) }; span { +"6GB+64GB 全网通" }; span { +"1099元" } }
            a { attrs.onClickFunction = { setVersion(0) }; span { +"4GB+64GB 移动4G" }; span { +"1049元" } }
          }
          div { +"选择颜色" }
          div {
            a { span {  }; +" 深空灰" }
          }
          div {
            div {
              span { +"小米8 ${if (version == 1) "6GB+64GB 全网通" else "4GB+64GB 移动4G"} 深灰色" }
              span { +"${product.price}元" }
            }
            div { +"总计: ${product.price}元" }
          }
          div {
            btn { attrs.onClickFunction = { productId.addCart(history, dispatch) }; +"加入购物车" }
            btnDefault { +"喜欢" }
          }
        }
      }
      swiper {
        attrs {
          navigation = true
          loop = true
          pagination = jsObject { e1 = ".swiper-pagination"; clickable = true }
        }
        repeat(4) {
          swiperSlide {
            img { attrs.src = "/imgs/detail/phone-${it + 1}.jpg" }
          }
        }
      }
    }
  }
  priceDescription {
    styledDiv { css { +MallStyle.container }
      div { +"价格说明" }
      img { attrs.src = "/imgs/detail/item-price.jpeg" }
    }
  }
}

fun RBuilder.productDetail() = child(productDetail)