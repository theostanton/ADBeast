package util

import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.InputStream
import java.util.function.Consumer


class StreamGobbler(private val inputStream: InputStream, private val consumer: Consumer<String>) : Runnable {

    override fun run() {
        BufferedReader(InputStreamReader(inputStream)).lines()
                .forEach(consumer)
    }
}