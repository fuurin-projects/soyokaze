package soyokaze.renderer.map

import org.w3c.dom.CanvasRenderingContext2D

fun CanvasRenderingContext2D.drawMapTip(mapTip: MapTip, dx: Double, dy: Double, dw: Double, dh: Double) {
    drawImage(
        mapTip.sprite,
        mapTip.sx.toDouble(),
        mapTip.sy.toDouble(),
        mapTip.sw.toDouble(),
        mapTip.sh.toDouble(),
        dx,
        dy,
        dw,
        dh
    )
}