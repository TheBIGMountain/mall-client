@file:JsModule("react-redux")
package modules


import store.Dispatch
import store.State

@JsName("useSelector")
external fun <T> useSelector(func: (State) -> T): T

@JsName("useDispatch")
external fun useDispatch(): Dispatch
