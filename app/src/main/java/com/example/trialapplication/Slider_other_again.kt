package com.example.trialapplication

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout


class Slider_other_again : AppCompatActivity() {

    internal var viewPager: ViewPager? = null
    internal var sliderDotspanel: LinearLayout? = null
    private var dotscount: Int = 0
    private var dots: Array<ImageView?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider_other_again)

        viewPager = findViewById<View>(R.id.viewPagerOther) as ViewPager

        sliderDotspanel = findViewById<View>(R.id.SliderDots) as LinearLayout

        val viewPagerAdapter = ViewPagerAdapterForSliderOtherAgain(this)

        viewPager?.adapter = viewPagerAdapter

        dotscount = viewPagerAdapter.count
        dots = arrayOfNulls<ImageView>(dotscount)

        for (i in 0 until dotscount) {

//            dots!![i].let { ImageView(this) }
            dots!![i] = ImageView(this)
            dots!![i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.non_active_dot))

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(8, 0, 8, 0)

            sliderDotspanel?.addView(dots!![i], params)

        }

        dots!![0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))

        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                for (i in 0 until dotscount) {
                    dots!![i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.non_active_dot))
                }

                dots!![position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

    }

}