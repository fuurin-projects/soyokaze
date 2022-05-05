package soyokaze.platform

import kotlinx.serialization.json.JsonObject

/**
 * 外部からデータを取得するクラス
 */
interface Fetcher {

    suspend fun fetchJson(url: String): JsonObject


}