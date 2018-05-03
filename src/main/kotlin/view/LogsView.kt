package view

import events.Event
import events.EventSource
import tornadofx.View
import tornadofx.readonlyColumn
import tornadofx.tableview

class LogsView : View() {

    val eventSource by inject<EventSource>()

    override val root = tableview<Event> {
        readonlyColumn("Time", Event::timestamp)
        readonlyColumn("Priority", Event::priority)
        readonlyColumn("Message", Event::message)

        eventSource.observable
                .subscribe {
                    items.add(0, it)
                    println("items=$items")
                }

    }
}