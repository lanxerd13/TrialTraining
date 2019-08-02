package com.example.trialapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class SplashScreenActivity: AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long=5000

    override fun onCreate(savedInstanceState: Bundle?) {
        //set global variable
        val mPrefs = getSharedPreferences("label", 0)
        Log.i("test","mPrefs $mPrefs")
        val mInt = mPrefs.getInt("opened", 0)
        val loginStatus = mPrefs.getBoolean("loginStatus", false)
        Log.i("test","mInt $mInt")
        Log.i("test","loginStatus $loginStatus")
//        val mEditor = mPrefs.edit()
//        mEditor.putInt("opened", mInt+1).commit()

        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if(mInt === 0) {
                startActivity(Intent(this, SliderOtherAgain::class.java))
            }
            else{
                if(loginStatus)
//                    startActivity(Intent(this, MainActivity::class.java))
                    startActivity(Intent(this, HomePage::class.java).putExtra("prev","0"))
                else
                    startActivity(Intent(this, SignUp::class.java))
            }

            finish()
        },SPLASH_TIME_OUT)
    }
}