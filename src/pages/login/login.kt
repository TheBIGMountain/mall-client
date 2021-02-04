package pages.login


import ajax.user.login
import antd.message.message
import components.nav_header.logo
import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.html.INPUT
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import modules.cookie
import modules.useDispatch
import modules.useHistory
import react.*
import react.dom.div
import react.dom.input
import react.dom.span
import store.Dispatch
import store.KAction
import style.MallStyle
import styled.css
import styled.styledDiv

private fun login(state: LoginState, dispatch: Dispatch) = MainScope().launch {
  state.username.login(state.password)
    .onEach { dispatch(KAction.ChangeUsername(it.username)) }
    .onEach { cookie.save("userId", it.id, jsObject { maxAge = 1800 }) }
    .onEach { window.location.href = "/#/index" }
    .onEach { message.success("登录成功") }
    .collect()
}

private data class LoginState(
  val username: String = "",
  val password: String = ""
)

private val login = functionalComponent<RProps> {
  val (state, setState) = useState(LoginState())
  val dispatch = useDispatch()
  val history = useHistory()

  loginHeader {
    logo {  }
    headerInfo {
      div { +"小米商城" }
      div { +"让每个人都能享受科技的乐趣" }
    }
  }
  loginBody {
    styledDiv { css { +MallStyle.container }
      loginForm {
        formHead {
          span { +"账号登录" }
          span {  }
          span { +"扫码登录" }
        }
        loginInfo {
          input { attrs { type = InputType.text; placeholder = "邮箱/手机号码" }
            attrs.onChangeFunction = { setState(state.copy(username = it.target.unsafeCast<INPUT>().value)) }
            attrs.value = state.username
          }
          input { attrs { type = InputType.password; placeholder = "密码" }
            attrs.onChangeFunction = { setState(state.copy(password = it.target.unsafeCast<INPUT>().value)) }
            attrs.value = state.password
          }
        }
        loginBtn { +"登录"; attrs.onClickFunction = { login(state, dispatch) } }
        formBottom {
          span { +"手机短信登录/注册"; attrs.onClickFunction = { history.push("/register") } }
          span { +"忘记密码?" }
          span { +"立即注册" }
        }
      }
    }
  }
  loginBottom {
    div {
      span { +"简体" }
      span { +"繁体" }
      span { +"English" }
      span { +"常见问题" }
      span { +"隐私政策" }
    }
    div {
      +"Copyright ©2019 TheBIGMountain All Rights Reserved."
    }
  }
}

fun RBuilder.login() = child(login)