package com.example.trialapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class SplashScreenActivity: AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long=3000

    override fun onCreate(savedInstanceState: Bundle?) {
        //set global variable
        val mPrefs = getSharedPreferences("label", 0)
        val mInt = mPrefs.getInt("opened", 0)
        Log.i("test","mInt $mInt")
//        val mEditor = mPrefs.edit()
//        mEditor.putInt("opened", mInt+1).commit()

        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if(mInt === 0) {
                startActivity(Intent(this, SliderOwn::class.java))
            }
            else{
                startActivity(Intent(this, MainActivity::class.java))
            }

            finish()
        },SPLASH_TIME_OUT)
    }
}