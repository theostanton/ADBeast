package view

import io.reactivex.subjects.BehaviorSubject
import tornadofx.Controller

class EventController : Controller() {
    val refreshSalesPeople = BehaviorSubject.create<Unit>()
    val refreshCustomers = BehaviorSubject.create<Unit>()
    val createNewCustomer = BehaviorSubject.create<Unit>()
    val saveAssignments = BehaviorSubject.create<Unit>()
}