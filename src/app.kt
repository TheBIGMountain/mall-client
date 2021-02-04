import ajax.user.getUserInfo
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import modules.cookie
import modules.useDispatch
import react.*
import router.router
import store.Dispatch

private fun init(dispatch: Dispatch) = MainScope().launch { getUserInfo(dispatch).collect() }

private val app = functionalComponent<RProps> {
  if (cookie.load("userId") != null) {
    val dispatch = useDispatch()
    useEffect(emptyList()) { init(dispatch) }
  }
  router()
}

fun RBuilder.app() = child(app)

