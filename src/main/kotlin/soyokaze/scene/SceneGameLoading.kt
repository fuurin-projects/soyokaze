package soyokaze.scene

import org.w3c.dom.CanvasRenderingContext2D
import soyokaze.Soyokaze

/**
 * ゲーム起動時のデータのローディング画面
 */
class SceneGameLoading : Scene {

    override fun draw(soyokaze: Soyokaze, context: CanvasRenderingContext2D) {
        super.draw(soyokaze, context)
        
        val gameLoading = soyokaze.gameLoading

        val subW: Int = if (gameLoading.loadingSubMaxCount != 0) {
            gameLoading.loadingSubCount / gameLoading.loadingSubMaxCount
        } else {
            0
        }


        //サブ
        context.save()
        context.beginPath();
        context.rect(200.0, 580.0, 880.0, 30.0)
        context.strokeStyle = "blue";
        context.lineWidth = 1.0;
        context.stroke()
        context.restore()

        context.save()
        context.beginPath();
        context.rect(202.0, 582.0, 876.0 * subW, 26.0);
        context.fillStyle = "rgba(0,200,0,0.8)";
        context.fill()
        context.restore()

        //メイン
        context.save()
        context.beginPath();
        context.rect(200.0, 520.0, 880.0, 30.0)
        context.strokeStyle = "blue";
        context.lineWidth = 1.0;
        context.stroke()
        context.restore()

    }

}