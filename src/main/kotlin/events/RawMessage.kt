package events

import java.time.Instant

data class RawMessage(val timestamp: Instant, val priority: Char, val message: String, val isEvent: Boolean) {
    companion object {
        operator fun invoke(line: String): RawMessage {

            val split = line.split(" ").filter { it.isNotEmpty() }
            println("split=$split")
            return if (split.size < 5) {
                RawMessage(Instant.now(), '!', line, false)
            } else {
                val dateString = split[0]
//                val date = Instant.parse(dateString)
                val time = split[1]
                val priority = split[4].firstOrNull() ?: '?'
                val message = line.substring(32, line.lastIndex)
                val isEvent = message.contains("ADBeast")
                val trimmed = message.replace("ADBeast : ", "").trim()
                println("trimmed=*${trimmed}*")
                return RawMessage(Instant.now(), priority, trimmed, isEvent)
            }
        }
    }
}