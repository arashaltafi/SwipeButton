package com.arash.altafi.swipebutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.arash.altafi.swipe.OnActiveListener
import com.arash.altafi.swipebutton.databinding.ActivityMainBinding
import com.arash.altafi.swipebutton.progressLayout.ProgressLayout
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {
        //region logout
        prLayoutLogout.setProgressLayoutListener(object : ProgressLayout.ProgressLayoutListener {
            override fun onProgressCompleted() {
                swipeBtnLogout.toShow()
                rlProgressLogout.toGone()
            }

            override fun onProgressChanged(var1: Int) {
                StringBuilder((var1 / 60).toString())
                    .append(":")
                    .append((var1 % 60).toString())
                    .toString()
            }
        })

        ivProgressLogout.setOnClickListener {
            if (prLayoutLogout.isPlaying) {
                prLayoutLogout.stop()
                prLayoutLogout.cancel()
                swipeBtnLogout.toShow()
                rlProgressLogout.toGone()
            }
        }

        swipeBtnLogout.setOnActiveListener(object : OnActiveListener {
            override fun onActive() {
                toast("Active Logout")

                rlProgressLogout.toShow()
                swipeBtnLogout.toGone()

                val params = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                val emptyColor = ContextCompat.getColor(this@MainActivity, R.color.color_empty)
                val greenColor = ContextCompat.getColor(this@MainActivity, R.color.color_red)
                prLayoutLogout.init(this@MainActivity, null, greenColor, emptyColor)
                prLayoutLogout.setMaxProgress(5) // 5 second
                prLayoutLogout.start()
                params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE)
                params.setMargins(20, 0, 0, 0)
                ivProgressLogout.layoutParams = params
            }
        })
        //endregion

        //region login
        prLayoutLogin.setProgressLayoutListener(object : ProgressLayout.ProgressLayoutListener {
            override fun onProgressCompleted() {
                swipeBtnLogin.toShow()
                rlProgressLogin.toGone()
            }

            override fun onProgressChanged(var1: Int) {
                StringBuilder((var1 / 60).toString())
                    .append(":")
                    .append((var1 % 60).toString())
                    .toString()
            }
        })

        ivProgressLogin.setOnClickListener {
            if (prLayoutLogin.isPlaying) {
                prLayoutLogin.stop()
                prLayoutLogin.cancel()
                swipeBtnLogin.toShow()
                rlProgressLogin.toGone()
            }
        }

        swipeBtnLogin.setOnActiveListener(object : OnActiveListener {
            override fun onActive() {
                toast("Active Login")

                rlProgressLogin.toShow()
                swipeBtnLogin.toGone()

                val params = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                val emptyColor = ContextCompat.getColor(this@MainActivity, R.color.color_empty)
                val greenColor = ContextCompat.getColor(this@MainActivity, R.color.color_red)
                prLayoutLogin.init(this@MainActivity, null, greenColor, emptyColor)
                prLayoutLogin.setMaxProgress(5) // 5 second
                prLayoutLogin.start()
                params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                params.setMargins(0, 0, 20, 0)
                ivProgressLogin.layoutParams = params
            }
        })
        //endregion
    }
}