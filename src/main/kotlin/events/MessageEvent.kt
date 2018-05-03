package events

data class MessageEvent(override val message: String) : Event()