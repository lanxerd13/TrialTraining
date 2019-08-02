package com.example.trialapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.content.SharedPreferences
import android.R.id.edit





class MainActivity : AppCompatActivity() {
    companion object{
        var PACKAGE_NAME: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun splash(view: View) {
        startActivity(Intent(this, SplashScreenActivity::class.java))
    }

    fun splashText(view: View) {
        startActivity(Intent(this, SplashScreenTextActivity::class.java))
    }

    fun slider(view: View) {
        startActivity(Intent(this, Slider::class.java))
    }

    fun slider_other(view: View) {
        startActivity(Intent(this, Slider_other::class.java))
    }

    fun sliderOtherAgain(view: View) {
        startActivity(Intent(this, SliderOtherAgain::class.java))
    }

    fun infiSlider(view: View) {
        startActivity(Intent(this, InfiSlider::class.java))
    }

    fun infiSliderOther(view: View) {
        startActivity(Intent(this, SliderOwn::class.java))
    }
}
