package com.devst.trekclan.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.devst.trekclan.R
import com.devst.trekclan.databinding.ActivityLaunchBinding

class LaunchActivity : AppCompatActivity() {
    lateinit var splashBinding: ActivityLaunchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        supportActionBar?.hide()

        val aplhaAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.launch_anim)

        splashBinding = ActivityLaunchBinding.inflate(layoutInflater)
        val view = splashBinding.root
        setContentView(view)

        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.launch_anim)
        splashBinding.titleSplash.startAnimation(animation)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {

                val intent = Intent(this@LaunchActivity, HomeActivity::class.java)
                startActivity((intent))
                finish()
            }

        }, 5000)

    }
}