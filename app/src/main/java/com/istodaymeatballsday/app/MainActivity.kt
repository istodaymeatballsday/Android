package com.istodaymeatballsday.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.istodaymeatballsday.app.services.ApiHandler
import com.istodaymeatballsday.app.services.ApiResponseHandler
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        ApiHandler.getStatus(object : ApiResponseHandler{
            override fun onResponse(value: String) {
                println(value)
                this@MainActivity.runOnUiThread { responseTV.text = value }
            }
        })
    }

}
