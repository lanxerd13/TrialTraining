package com.example.trialapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.trialapplication._sliders.SliderIndicator
import kotlinx.android.synthetic.main.slider_own_item.view.*
import java.util.*


class SliderOtherAgain : AppCompatActivity() {

    internal var viewPager: ViewPager? = null
    internal var sliderDotspanel: LinearLayout? = null
    private var dotscount: Int = 0
    private var dots: Array<ImageView?>? = null
    private var mIndicator: SliderIndicator? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider_other_again)

        viewPager = findViewById<View>(R.id.viewPagerOther) as ViewPager
        sliderDotspanel = findViewById<View>(R.id.SliderDots) as LinearLayout
        var skipText = findViewById<TextView>(R.id.textSkip)
        skipText.setTextColor(ContextCompat.getColor(this, R.color.white))
        val viewPagerAdapter = ViewPagerAdapterForSliderOtherAgain(this)

        viewPager?.adapter = viewPagerAdapter
        Log.i("test","view page size $viewPagerAdapter")
        Log.i("test","view page size ${viewPager!!.getCurrentItem()}")

        dotscount = viewPagerAdapter.count
        dots = arrayOfNulls<ImageView>(dotscount)

//        mIndicator = SliderIndicator(this, sliderDotspanel!!, viewPager!!, R.drawable.indicator_circle )

        mIndicator =  object : SliderIndicator(this, sliderDotspanel!!, viewPager!!, R.drawable.indicator_circle ){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val index = position % dotscount
                Log.i("test","masuk override")
                mIndicator!!.setIndicatorAsSelected(index)
                if(index+1 == dotscount)
                    skipText.text = "Continue > "
                else
                    skipText.text = "Skip > "
            }
        }

//        mIndicator = MyPageIndicator(this, mLinearLayout, sliderView!!, R.drawable.indicator_circle)
        mIndicator!!.setPageCount(dotscount)
        mIndicator!!.show()
//
//        for (i in 0 until dotscount) {
//
////            dots!![i].let { ImageView(this) }
//            dots!![i] = ImageView(this)
//            dots!![i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.non_active_dot))
//
//            val params = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//            )
//
//            params.setMargins(8, 0, 8, 0)
//
//            sliderDotspanel?.addView(dots!![i], params)
//
//        }
//
//        dots!![0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
//
//        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//
//            }
//
//            override fun onPageSelected(position: Int) {
//
//                for (i in 0 until dotscount) {
//                    dots!![i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.non_active_dot))
//                }
//
//                dots!![position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
//
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//
//            }
//        })

    }

    fun skipToSighUp (view: View){
        startActivity(Intent(this, SignUp::class.java))
//        finish()
    }


}