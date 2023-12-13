package com.pokedex.client.view.activity.screenload

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.pokedex.client.R
import com.pokedex.client.databinding.ActivityScreenloadBinding
import com.pokedex.client.view.activity.home.HomeActivity

class ScreenLoadActivity : Activity() {
    private lateinit var binding: ActivityScreenloadBinding
    private val TAG = ScreenLoadActivity::class.java.simpleName
    private val MAX_PROGRESS = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenloadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition{false}
        actionBar?.hide()
        setUpView()
    }

    private fun setUpView(){
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable{
            var currentProgress = 0
            override fun run() {
                currentProgress+=20
                binding.pbStartApp.progress = currentProgress
                if(currentProgress < MAX_PROGRESS){
                    handler.postDelayed(this, 500)
                }else{
                    binding.pbStartApp.visibility = View.GONE
                    binding.btnStartApp.visibility = View.VISIBLE
                }
            }
        },500)

        binding.btnStartApp.apply {
            setOnClickListener {
                startActivity(
                    HomeActivity.newIntent(this@ScreenLoadActivity)
                )
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                finish()
            }
        }
    }
}