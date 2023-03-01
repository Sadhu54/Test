package com.test_app_21.utils.ktx

import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

// function for showing translucent screen
fun AppCompatActivity.translucentScreen() {
    val w = this.window
    w.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}
