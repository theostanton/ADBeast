package events

import java.time.Instant

abstract class Event(val timestamp: Instant = Instant.now()) {

    lateinit var rawMessage: RawMessage

    val priority: Char get() = rawMessage.priority
    abstract val message: String

    companion object {
        fun create(key: String, payload: String): Event {
            val eventType = EventType.TYPES.first { it.key == key }
            val event = eventType.parse(payload)
            return event
        }
    }


}