package view

import com.github.thomasnield.rxkotlinfx.actionEvents
import events.EventSource
import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView : View() {
    override val root = BorderPane()

    private val logsView: LogsView by inject()
    private val eventSource: EventSource by inject()

    private val controller: EventController by inject()

    init {
        title = "Client/Salesperson Assignments"

        with(root) {
            setPrefSize(940.0, 610.0)

            top = menubar {
                menu("File") {
                    item("Refresh").apply {
                        actionEvents().map { Unit }.subscribe(controller.refreshCustomers)
                        actionEvents().map { Unit }.subscribe(controller.refreshSalesPeople)
                    }
                }
                menu("Edit") {
                    item("Create Customer").actionEvents().map { Unit }.subscribe(controller.createNewCustomer)
                }
            }

            center = scrollpane(fitToWidth = true, fitToHeight = true) {
                this += logsView
            }

            eventSource.packageNameRelay.accept("com.theostanton.sample")
        }

    }
}