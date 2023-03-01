package com.test_app_21.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.test_app_21.databinding.ActivityMainBinding
import com.test_app_21.ui.home.HomeActivity
import com.test_app_21.utils.ktx.translucentScreen

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding

    @SuppressLint("PackageManagerGetSignatures")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        translucentScreen()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(
                    this,
                    HomeActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }, 3000)
        setContentView(viewBinding.root)

    }
}