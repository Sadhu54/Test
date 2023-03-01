package com.test_app_21.utils.ktx

import android.view.View


/**
 * Function for handling visibility of the view
 */

fun View.visibilityVisible() {
    visibility = View.VISIBLE
}

fun View.visibilityInVisible() {
    visibility = View.INVISIBLE
}

fun View.visibilityGone() {
    visibility = View.GONE
}