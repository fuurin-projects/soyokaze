package soyokaze

/**
 * ロードしているデータの進捗を管理するクラス
 */
class GameLoading {

    var loadingItem: String = ""

    var loadingMainCount: Int = 0

    var loadingMainMaxCount: Int = 0

    var loadingSubCount: Int = 0

    var loadingSubMaxCount: Int = 0

    fun loadItem(itemName: String, count: Int, maxCount: Int) {

        loadingItem = itemName
        loadingSubCount = count
        loadingSubMaxCount = maxCount

    }

}