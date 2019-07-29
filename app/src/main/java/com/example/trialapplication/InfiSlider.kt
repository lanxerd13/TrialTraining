package com.example.trialapplication


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager

import java.util.ArrayList

class InfiSlider : AppCompatActivity() {

    internal var lstImages: MutableList<Int> = ArrayList()

    private val images = arrayOf<Int>(R.drawable.test2, R.drawable.image3, R.drawable.i4808001, R.drawable.i5409601, R.drawable.i60010241)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.infi_slider)

//        initData()

        val pager = findViewById(R.id.horizontal_cycle) as HorizontalInfiniteCycleViewPager
        val adapter = InfiSliderAdapter(images, baseContext)
        pager.adapter = adapter
    }

//    private fun initData() {
//        lstImages.add(R.drawable.cyclos)
//        lstImages.add(R.drawable.night)
//        lstImages.add(R.drawable.meggan)
//    }
}
