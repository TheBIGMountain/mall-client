@file:JsModule("react-router-dom")
package modules

@JsName("useHistory")
external val useHistory: () -> History

@JsName("useParams")
external val useParams: () -> dynamic

@JsName("useLocation")
external val useLocation: () -> Location

external interface History {
  fun push(url: String)
}
external interface Location {
  var pathname: String
}


