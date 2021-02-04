package components.modal

import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RProps
import react.child
import react.dom.span
import react.functionalComponent


enum class ModalType {
  BIG, MIDDLE, SMALL
}

enum class BthType {
  CONFIRM, CANCEL
}

interface ModalData {
  var modalType: ModalType
  var title: String
  var bthType: Array<BthType>
  var sureText: String
  var showModal: Boolean
  var cancelText: String
  var handleConfirm: () -> Unit
  var handleCancel: () -> Unit
}

private interface ModalProps: RProps {
  var modalData: ModalData
  var content: RBuilder.() -> Unit
}

private val modal = functionalComponent<ModalProps> { props ->
  modalSty(props.modalData.showModal) {
    mask {  }
    dialog {
      modalHeader {
        span { +props.modalData.title }
        iconClose { attrs.onClickFunction = { props.modalData.handleCancel() } }
      }
      modalBody {
        props.content(this)
      }
      modalFooter {
        btnGroup {
          props.modalData.bthType.forEach { b ->
            if (b == BthType.CONFIRM)
              btn {
                attrs { onClickFunction = { props.modalData.handleConfirm() } }
                +props.modalData.sureText
              }
            if (b == BthType.CANCEL)
              btnDefault {
                attrs { onClickFunction = { props.modalData.handleCancel() } }
                +props.modalData.cancelText
              }
          }
        }
      }
    }
  }
}

fun RBuilder.modal(data: ModalData, func: RBuilder.() -> Unit)
= child(modal) { attrs { modalData = data; content = func } }