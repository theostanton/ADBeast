package events

sealed class EventType<E : Event>(val key: String, val clazz: Class<E>) {

    companion object {
        val TYPES = listOf(NetworkType, MessageType)
    }

    fun parse(payload: String): E {
        return Parser.gson.fromJson(payload, clazz)
    }

}

object NetworkType : EventType<NetworkEvent>("network", NetworkEvent::class.java)
object MessageType : EventType<MessageEvent>("message", MessageEvent::class.java)