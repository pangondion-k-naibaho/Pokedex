package com.pokedex.client.view.activity.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pokedex.client.R
import com.pokedex.client.databinding.ActivityHomeBinding

class HomeActivity : Activity() {
    private lateinit var binding: ActivityHomeBinding
    private val TAG = HomeActivity::class.java.simpleName

    companion object{
        fun newIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar?.hide()
    }
}