package com.hilmyhaidar.github_user.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hilmyhaidar.github_user.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            binding.buttonLogin.id -> {
                val listUserActivityIntent = Intent(this@MainActivity, ListUserActivity::class.java)
                startActivity(listUserActivityIntent)
                finish()
            }
        }
    }
}