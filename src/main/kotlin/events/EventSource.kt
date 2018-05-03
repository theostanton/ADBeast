package events

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import tornadofx.Controller
import util.Shell

class EventSource : Controller() {

    val packageNameRelay = BehaviorRelay.create<String>()

    val observable: Observable<Event> = packageNameRelay
            .switchMap { packageName ->
                Shell.monitor(packageName)
                        .map {
                            RawMessage(it)
                        }
                        .filter { it.isEvent }
                        .map {
                            try {
                                val event = Parser.gson.fromJson("${it.message}}", Event::class.java)
                                event.rawMessage = it
                                Attempt.Success(event)
                            } catch (exception: Exception) {
                                println("error=${exception.message}")
                                Attempt.Error
                            }
                        }
                        .filter { it is Attempt.Success }
                        .map { (it as Attempt.Success).event }
                        .doOnNext {
                            println("event->$it")
                        }
            }
            .share()

}

sealed class Attempt {
    data class Success(val event: Event) : Attempt()
    object Error : Attempt()
}