package components.nav_header

import ajax.product.Product
import ajax.cart.getCardCount
import ajax.product.getProductList
import ajax.user.logout
import antd.message.message
import kotlinext.js.jsObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.html.js.onClickFunction
import kotlinx.html.style
import modules.cookie
import modules.useDispatch
import modules.useHistory
import modules.useSelector
import react.*
import react.dom.a
import react.dom.div
import react.dom.key
import react.dom.span
import store.Dispatch
import store.KAction
import style.colorB

private fun getPhoneList(setPhoneList: (Array<Product>) -> Unit, setRedMiList: (Array<Product>) -> Unit)
= MainScope().launch {
  launch { setPhoneList(getProductList().single().drop(4).take(6).toTypedArray()) }
  launch { setRedMiList(getProductList(2).single().take(6).toTypedArray()) }
}

private fun exitLogin(dispatch: Dispatch) = MainScope().launch {
  logout().onEach { message.success("退出成功") }
    .onEach { cookie.remove("userId") }
    .onEach { dispatch(KAction.ChangeUsername("")) }
    .zip(flowOf(dispatch(KAction.ChangeCartCount(0)))) { _, _ -> }
    .collect()
}

private fun cartCount(dispatch: Dispatch) = MainScope().launch { getCardCount(dispatch).collect() }

private val tvs = arrayOf<Product>(
  jsObject { id = 26; name = "小米壁画电视 65英寸"; price = 6999; mainImage = "/imgs/nav-img/nav-3-1.jpg" },
  jsObject { id = 27; name = "小米电视4A 32英寸"; price = 1999; mainImage = "/imgs/nav-img/nav-3-2.jpg" },
  jsObject { id = 28; name = "小米电视4A 32英寸"; price = 699; mainImage = "/imgs/nav-img/nav-3-3.png" },
  jsObject { id = 29; name = "小米电视4A 55英寸"; price = 1799; mainImage = "/imgs/nav-img/nav-3-4.jpg" },
  jsObject { id = 30; name = "小米电视4A 65英寸"; price = 2699; mainImage = "/imgs/nav-img/nav-3-5.jpg" },
  jsObject { id = 31; name = "查看全部"; price = 0; mainImage = "/imgs/nav-img/nav-3-6.png" },
)


private fun RBuilder.itemMenu(title: String, array: Array<Product>) {
  itemMenu {
    span { +title }
    children {
      array.forEach {
        product {
          attrs.key = it.id.toString()
          a {
            attrs { href = "/#/product/${it.id}"; target = "_blank" }
            proImg { attrs.src = it.mainImage }
            proName { +it.name }
            proPrice { +if (it.price == 0) "查看全部" else "${it.price}元" }
          }
        }
      }
    }
  }
}

private val navHeader = functionalComponent<RProps> {
  val (phoneList, setPhoneList) = useState<Array<Product>>(emptyArray())
  val (redMiList, setRedMiList) = useState<Array<Product>>(emptyArray())
  val username = useSelector { it.username }
  val cartCount = useSelector { it.cartCount }
  val history = useHistory()
  val dispatch = useDispatch()

  useEffect(emptyList()) {
    getPhoneList(setPhoneList, setRedMiList)
    if (cookie.load("userId") != null) cartCount(dispatch)
  }

  div { attrs.style = jsObject<dynamic> { backgroundColor = colorB }.unsafeCast<String>()
    topBar {
      topBarMenu {
        a { +"小米商城" }
        a { +"MUI" }
        a { +"云服务" }
        a { +"协议规则" }
      }
      topBarUser {
        if (username != "") {
          a { +username }
          a { +"我的订单"; attrs.onClickFunction = { history.push("/order/list") } }
          a { +"退出登录"; attrs.onClickFunction = { exitLogin(dispatch) } }
        }
        else
          a { attrs.href = "/#/login"; +"登录" }
        myCart {
          attrs.href = "/#/cart";
          iconCart { }
          +"购物车($cartCount)"
        }
      }
    }
  }
  bottomBar {
    logo { }
    menu {
      itemMenu("小米手机", phoneList)
      itemMenu("红米手机", redMiList)
      itemMenu("电视", tvs)
    }
    searchWrapper {
      search {  }
      searchIcon { attrs.href = "#" }
    }
  }
}


fun RBuilder.navHeader() = child(navHeader)