package soyokaze.controller

import kotlinx.browser.window
import org.w3c.dom.events.KeyboardEvent

class Keyboard {

    var up = false;
    var down = false;
    var right = false;
    var left = false;


    init {
        window.addEventListener("keydown", { event ->
            val keyboardEvent = event as KeyboardEvent

            if (keyboardEvent.keyCode == KyeCodes.wCode) {
                up = true;
            } else if (keyboardEvent.keyCode == KyeCodes.sCode) {
                down = true;
            } else if (keyboardEvent.keyCode == KyeCodes.aCode) {
                left = true;
            } else if (keyboardEvent.keyCode == KyeCodes.dCode) {
                right = true;
            }

        });

        window.addEventListener("keyup", { event ->
            val keyboardEvent = event as KeyboardEvent

            if (keyboardEvent.keyCode == KyeCodes.wCode) {
                up = false;
            } else if (keyboardEvent.keyCode == KyeCodes.sCode) {
                down = false;
            } else if (keyboardEvent.keyCode == KyeCodes.aCode) {
                left = false;
            } else if (keyboardEvent.keyCode == KyeCodes.dCode) {
                right = false;
            }

        });
    }
}

object KyeCodes {
    val wCode = 87;
    val aCode = 65;
    val sCode = 83;
    val dCode = 68;
}