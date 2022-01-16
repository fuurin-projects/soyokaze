package soyokaze.renderer.map

import org.w3c.dom.CanvasRenderingContext2D

fun CanvasRenderingContext2D.drawMapTip(icon: Icon, dx: Double, dy: Double, dw: Double, dh: Double) {
    drawImage(
        icon.sprite,
        icon.sx.toDouble(),
        icon.sy.toDouble(),
        icon.sw.toDouble(),
        icon.sh.toDouble(),
        dx,
        dy,
        dw,
        dh
    )
}