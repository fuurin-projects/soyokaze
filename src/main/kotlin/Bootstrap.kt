import soyokaze.Soyokaze

lateinit var soyokazeInstance: Soyokaze;

/**
 * すべての処理の起点
 */
suspend fun main() {

    soyokazeInstance = Soyokaze()

    soyokazeInstance.init()

}
