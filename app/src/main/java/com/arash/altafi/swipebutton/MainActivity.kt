package com.arash.altafi.swipebutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arash.altafi.swipe.OnActiveListener
import com.arash.altafi.swipebutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.apply {
            swipeBtnLogout.setOnActiveListener(object : OnActiveListener {
                override fun onActive() {
                    Toast.makeText(this@MainActivity, "Active Logout", Toast.LENGTH_SHORT).show()
                }
            })
            swipeBtnLogin.setOnActiveListener(object : OnActiveListener {
                override fun onActive() {
                    Toast.makeText(this@MainActivity, "Active Login", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}