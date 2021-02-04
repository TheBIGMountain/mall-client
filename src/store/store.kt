package store



import redux.RAction
import redux.WrapperAction
import redux.createStore
import redux.rEnhancer

typealias Dispatch = (KAction) -> WrapperAction

data class State(
  val username: String = "",
  val cartCount: Int = 0
)

sealed class KAction: RAction {
  abstract fun execute(state: State): State

  class ChangeUsername(private val username: String): KAction() {
    override fun execute(state: State): State {
      return state.copy(username = username)
    }
  }

  class ChangeCartCount(private val cartCount: Int): KAction() {
    override fun execute(state: State): State {
      return state.copy(cartCount = cartCount)
    }
  }
}


val store = createStore(::reducer, State(), rEnhancer())

private fun reducer(state: State, action: RAction): State = when(action) {
  is KAction -> action.execute(state)
  else -> state
}