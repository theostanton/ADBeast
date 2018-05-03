package events

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class EventTypeAdapter : JsonDeserializer<Event> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Event {
        val asJsonObject = json.asJsonObject
        val key = asJsonObject.getAsJsonPrimitive("key").asString
        val payload = asJsonObject.getAsJsonPrimitive("payload").asString
        return Event.create(key, payload)
    }
}