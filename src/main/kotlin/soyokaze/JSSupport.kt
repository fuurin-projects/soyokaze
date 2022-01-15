package soyokaze

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
