package view

import com.github.thomasnield.rxkotlinfx.actionEvents
import javafx.geometry.Orientation
import tornadofx.*


class SomeView : View() {

    private val controller: EventController by inject()

    override val root = borderpane {
        left = toolbar {
            orientation = Orientation.VERTICAL

            //save button
            button("Click me") {
                useMaxWidth = true
                actionEvents()
                        .map { Unit }
                        .subscribe(controller.saveAssignments)
            }

        }
    }

    init {

        controller.saveAssignments
                .subscribe {
                    println("Click")
                }
    }
}