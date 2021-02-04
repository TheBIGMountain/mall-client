package ajax.user


import ajax.Method
import ajax.ajax
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import store.Dispatch
import store.KAction
import kotlin.js.json

/**
 * 用户信息
 */
interface UserData {
  var username: String
  var password: String
  var email: String
  var id: String
  var code: String
}

/**
 * 获取用户信息
 */
suspend fun getUserInfo(dispatch: Dispatch): Flow<UserData> {
  return Method.GET.ajax<UserData>("/user", showMsg = false)
    .onEach { dispatch(KAction.ChangeUsername(it.username)) }
}


/**
 * 用户登录
 */
suspend fun String.login(password: String): Flow<UserData> {
  return Method.POST.ajax("/user/login", json(
    "username" to this,
    "password" to password
  ))
}

/**
 * 退出登录
 */
suspend fun logout(): Flow<Unit> {
  return Method.POST.ajax("/user/logout")
}

/**
 * 用户注册
 */
suspend fun UserData.register(): Flow<Unit> {
  return Method.POST.ajax("/user/register", json(
    "username" to username,
    "password" to password,
    "code" to code
  ))
}