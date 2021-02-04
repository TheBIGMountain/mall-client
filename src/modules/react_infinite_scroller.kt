package modules

import react.RClass
import react.RProps
import react.ReactElement

@JsModule("react-infinite-scroller")
@JsNonModule
external val infiniteScroll: RClass<InfiniteScrollProps>


external interface InfiniteScrollProps: RProps {
  var loadMore: dynamic
  var hasMore: dynamic
  var loader: dynamic
  var children: Array<ReactElement>
}


