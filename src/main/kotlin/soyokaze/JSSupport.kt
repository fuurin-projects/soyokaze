package soyokaze

import kotlinx.browser.window
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.js.Promise

/**
 * Promiseをsuspendさせる
 */
suspend fun <T> Promise<T>.await(): T = suspendCoroutine { cont ->
    then({ cont.resume(it) }, { cont.resumeWithException(it) })
}

/**
 * JSネイティブのオブジェクトをforできるように
 */
fun keys(json: dynamic) = js("Object").keys(json).unsafeCast<Array<String>>()

fun sleep(timeOut: Int): Promise<dynamic> {

    return Promise { resolve, _ -> window.setTimeout(resolve, timeOut) }

}
