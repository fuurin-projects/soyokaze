package soyokaze.platform.browser

import kotlinx.browser.window
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromDynamic
import soyokaze.await
import soyokaze.platform.Fetcher

/**
 * ブラウザ環境でデータを取得するクラス
 */
class FetcherJSFetch : Fetcher {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun fetchJson(url: String): JsonObject {

        return window.fetch(url)
            .then { it.json() }
            .then { res -> Json.decodeFromDynamic<JsonObject>(res) }
            .await()

    }

}