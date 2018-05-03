package events

import com.google.gson.GsonBuilder

object Parser {
    val gson = GsonBuilder()
            .registerTypeAdapter(Event::class.java,EventTypeAdapter())
            .create()

}