package pages.register

import ajax.Method
import ajax.ajax
import ajax.user.UserData
import ajax.user.register
import antd.button.button
import antd.input.input
import antd.message.message
import components.modal.btnHuge
import components.nav_header.logo
import kotlinext.js.jsObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.html.INPUT
import kotlinx.html.js.onClickFunction
import modules.useHistory
import pages.login.loginBottom
import react.*
import react.dom.div
import react.dom.span
import styled.css
import styled.styledDiv

private fun startCountDown(username: String, isDisable: (Boolean) -> Unit, setCountDown: (Int) -> Unit)
= MainScope().launch {
  launch { Method.GET.ajax<dynamic>("/verificationCode?phoneNumber=${username}").collect() }
  var init = 59
  isDisable(true)
  while (true) {
    delay(1000)
    setCountDown(init --)
    if (init == 0) {
      isDisable(false)
      setCountDown(60)
      break
    }
  }
}

private val register = functionalComponent<RProps> {
  val (username, setUsername) = useState("")
  val (password, setPassword) = useState("")
  val (code, setCode) = useState("")
  val (countDown, setCountDown) = useState(60)
  val (disable, isDisable) = useState(false)
  val history = useHistory()

  styledDiv { css { backgroundColor = Color("#e6e0e042"); paddingTop = 80.px; height = 100.pct }
    registerWrap {
      div { logo {  } }
      div { +"注册小米账号" }
      div { +"手机号码" }
      input {
        attrs {
          placeholder = "请输入手机号码"
          value = username
          onChangeCapture = { setUsername(it.target.unsafeCast<INPUT>().value) }
        }
      }
      div { +"密码" }
      input { attrs.type = "password"
        attrs {
          placeholder = "请输入密码"
          value = password
          onChangeCapture = { setPassword(it.target.unsafeCast<INPUT>().value) }
        }
      }
      verificationCode {
        input { attrs {
          placeholder = "请输入验证码"
          value = code
          onChangeCapture = { setCode(it.target.unsafeCast<INPUT>().value) }
        } }
        if (disable)
          button { +"倒计时(${countDown})秒"; attrs { disabled = true } }
        else
          button { +"获取验证码"; attrs { onClickCapture = { startCountDown(username, isDisable, setCountDown) } }}
      }
      div {
        btnHuge { +"立即注册"; attrs.onClickFunction = {
          MainScope().launch {
            jsObject<UserData> {
              this.username = username
              this.password = password
              this.code = code
            }.register()
              .onEach { message.success("注册成功") }
              .onEach { history.push("/login") }
              .collect()
          }
        } }
      }
    }
    loginBottom { css { marginTop = 0.px }
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
}

fun RBuilder.register() = child(register)