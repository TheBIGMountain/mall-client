package pages.home.index

import ajax.cart.addToCart
import ajax.product.Product
import ajax.product.getProductList
import components.modal.BthType
import components.modal.ModalType
import components.modal.modal
import components.service_bar.serviceBar
import kotlinext.js.jsObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.css.backgroundColor
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import modules.*
import react.*
import react.dom.*
import store.Dispatch
import store.KAction
import style.colorJ
import styled.css
import styled.styledDiv


private val items = arrayOf(
  "手机 电话卡", "电视 盒子", "笔记本 平板", "家电 插线板",
  "出行 穿戴", "智能 路由器", "电源 配件", "生活 箱包"
)

private val advImgs = arrayOf(
  "/imgs/ads/ads-1.png",
  "/imgs/ads/ads-2.jpg",
  "/imgs/ads/ads-3.png",
  "/imgs/ads/ads-4.jpg"
)

private val slideList: Array<Product> = arrayOf(
  jsObject { id = 26; mainImage = "/imgs/slider/slide-1.jpg" },
  jsObject { id = 27; mainImage = "/imgs/slider/slide-2.jpg" },
  jsObject { id = 28; mainImage = "/imgs/slider/slide-3.jpg" },
  jsObject { id = 29; mainImage = "/imgs/slider/slide-4.jpg" },
  jsObject { id = 30; mainImage = "/imgs/slider/slide-5.jpg" },
)

private val menuList = ArrayList<Array<Product>>().apply {
  add(arrayOf(
    jsObject { id = 30; mainImage = "/imgs/item-box-1.png"; name = "小米CC9" },
    jsObject { id = 31; mainImage = "/imgs/item-box-2.png"; name = "小米8青春版" },
    jsObject { id = 32; mainImage = "/imgs/item-box-3.jpg"; name = "Redmi K20 Pro" },
    jsObject { id = 33; mainImage = "/imgs/item-box-4.jpg"; name = "移动4G专区" },
  ))
  repeat(5) {
    val list = ArrayList<Product>()
    repeat(4) { list.add(jsObject { id = 30; mainImage = "/imgs/item-box-1.png"; name = "小米CC9" }) }
    add(list.toTypedArray())
  }
}.toTypedArray()

private fun init(state: IndexState, setState: (IndexState) -> Unit) = MainScope().launch {
  val arr1 = async { getProductList(2).single().take(4).toTypedArray() }
  val arr2 = async { getProductList(2).single().drop(4).take(4).toTypedArray() }
  setState(state.copy(phoneList = arrayOf(arr1.await(), arr2.await())))
}

private fun Dispatch.addCart(id: Int, state: IndexState, setState: (IndexState) -> Unit) {
  val dispatch = this
  MainScope().launch {
    id.addToCart()
      .onEach { dispatch(KAction.ChangeCartCount(it.cartTotalQuantity)) }
      .onEach { setState(state.copy(showModal = true)) }
      .collect()
  }
}

private data class IndexState(
  val phoneList: Array<Array<Product>> = emptyArray(),
  val showModal: Boolean = false
)

private val index = functionalComponent<RProps> {
  val (state, setState) = useState(IndexState())
  val history = useHistory()
  val dispatch = useDispatch()
  useEffect(emptyList()) { init(state, setState) }

  swiperBox {
    navMenu {
      items.forEachIndexed { index, it ->
        menuItem { attrs.key = index.toString()
          a { attrs.href = "#"; +it }
          menuChildren {
            menuList.forEach {
              li {
                it.forEach {
                  a {
                    attrs { href = "/#/product/${it.id}" }
                    img { attrs.src = it.mainImage }
                    +it.name
                  }
                }
              }
            }
          }
        }
      }
    }
    swiper {
      attrs {
        navigation = true
        loop = true
        pagination = jsObject { e1 = ".swiper-pagination"; clickable = true }
        effect = "cube"
        autoplay = true
      }
      slideList.withIndex().forEach {
        swiperSlide { attrs.key = it.index.toString()
          a { attrs.href = "/#/product/${it.value.id}"; img { attrs.src = it.value.mainImage } }
        }
      }
    }
  }
  advWrap {
    repeat(4) {
      advBox(advImgs[it]) { attrs.href = "/#/product/33" }
    }
  }
  banner { attrs.href = "/#/product/30" }
  styledDiv { css { backgroundColor = colorJ }
    productBox {
      h2 { +"手机" }
      listBox {
        state.phoneList.forEach {
          boxWrap {
            it.forEach {
              boxItem {
                if (it.id == 40)
                  span { attrs.classes = setOf("new-pro"); +"新品" }
                else
                  span { attrs.classes = setOf("kill-pro"); +"秒杀" }
                lazyLoad {
                  itemImg { attrs.src = it.mainImage }
                }
                itemInfo {
                  h3 { +it.name }
                  p { +it.subtitle }
                  price { attrs.onClickFunction = { _ -> dispatch.addCart(it.id, state, setState) }; +"${it.price}元" }
                }
              }
            }
          }
        }
      }
      bannerLeft { attrs.href = "/#/product/30" }
    }
  }
  serviceBar()
  modal(jsObject {
    title = "提示"
    sureText = "查看购物车"
    bthType = arrayOf(BthType.CONFIRM)
    modalType = ModalType.MIDDLE
    showModal = state.showModal
    handleConfirm = { history.push("/cart") }
    handleCancel = { setState(state.copy(showModal = false)) }
  }) { p { +"商品添加成功" } }
}

fun RBuilder.index() = child(index)