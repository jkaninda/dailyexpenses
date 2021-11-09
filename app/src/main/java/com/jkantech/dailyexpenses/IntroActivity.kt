package com.jkantech.dailyexpenses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.jkantech.dailyexpenses.utils.CustomDataStore

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        CustomDataStore.init(this)

        Handler(Looper.getMainLooper()).postDelayed({
            start()

        },1000)
    }
    private fun start(){
        if(CustomDataStore.restoreBoolean("isConnected")){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }


    }
}