package view

import io.reactivex.Maybe
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.image.ImageView
import javafx.stage.Stage
import tornadofx.*

class NewCustomerDialog : Dialog<Maybe<String>>() {
    private val root = Form()

    init {
        title = "Create New Customer"

        with(root) {
            fieldset("Customer Name") {
                textfield {
                    setResultConverter {
                        if (it == ButtonType.OK)
                            Maybe.just(text)
                        else
                            Maybe.just(text)
                    }
                }
            }
        }

        dialogPane.content = root
        dialogPane.buttonTypes.addAll(ButtonType.OK, ButtonType.CANCEL)
        graphic = ImageView(FX.primaryStage.icons[0])
        (dialogPane.scene.window as Stage).icons += FX.primaryStage.icons[0]
    }
}