package model

abstract class Log(open val timestamp: String, open val priority: Char) {

    abstract fun message(): String

    val messageField by lazy { message() }

    companion object {
        operator fun invoke(raw: String): Log {

            val split = raw.split(" ").filter { it.isNotEmpty() }


            println("Split=$split")
            return if (split.size < 5) {
                BasicLog("--", '!', raw)
            } else {
                val date = split[0]
                val time = split[1]
                val priority = split[4]
                val message = raw.substring(32, raw.lastIndex)
                BasicLog(time, priority.firstOrNull() ?: '?', message).apply {
                    println("log=$this")
                }
            }
        }
    }
}

data class BasicLog(override val timestamp: String, override val priority: Char, val message: String) : Log(timestamp, priority) {
    override fun message(): String {
        return message
    }
}


data class ClassLog(override val timestamp: String, override val priority: Char, val rawMessage: String) : Log(timestamp, priority) {

    override fun message(): String {
        return mutableListOf<String>().apply {
            add("One")
            add("Two")
            add("Three")
        }.joinToString("\n")
    }

}

//  Log(timestamp=22:33:56.842, priority=E, message= AudioRecord-JNI: Error creating AudioRecord instance: initialization check failed with status -12)