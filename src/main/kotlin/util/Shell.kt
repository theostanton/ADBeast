package util

import io.reactivex.Observable
import io.reactivex.Single
import tornadofx.isInt
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.Executors
import java.util.function.Consumer


object Shell {
    fun monitor(packageName: String): Observable<String> {
        return getPid(packageName)
                .flatMapObservable { pid ->
                    Observable.create<String> { emitter ->
                        val process = Runtime.getRuntime().exec("adb logcat --pid=$pid")
                        val streamGobbler = StreamGobbler(process.inputStream, Consumer {
                            println("Log:$it")
                            emitter.onNext(it)
                        })
                        Executors.newSingleThreadExecutor().submit(streamGobbler)
                        emitter.setCancellable { process.destroy() }
                    }
                }
    }
}

fun getPid(packageName: String): Single<Int> {
    return Single.create { emitter ->
        val process = Runtime.getRuntime().exec(" adb shell ps | grep $packageName | awk '{print \$2}'")
        val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
        val lines = bufferedReader.lines().filter { it.isInt() }
        val line = lines.findFirst().orElse("-1")
        bufferedReader.close()
        emitter.onSuccess(line.toInt())
    }
}