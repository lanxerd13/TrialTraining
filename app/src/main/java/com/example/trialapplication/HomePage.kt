package com.example.trialapplication

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.trialapplication._sliders.SliderIndicator


class HomePage : AppCompatActivity() {


//    string prev is used for indicator if home page is acessed from verification page/splash or not
//    prev = 0 -> home page accessed from  verification page/splash
//    prev = 1 -> home page accessed from other page

    private var data : String? = null
    private var homeSliderView: ViewPager? = null
    private var mLinearLayout: LinearLayout? = null
    private var mAdapter: SliderOwn.CustomPagerAdapter2? = null
    private var mIndicator: SliderIndicator? = null
    private var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        Log.i("test","masuk onbackpresed")
        Log.i("test","masuk onbackpresed $data")
        if(data == "1"){
            super.onBackPressed()
        }else{
            if (doubleBackToExitPressedOnce) {
                finishAffinity()
//                finish()
            }

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)

        }
        // do something
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle = intent.extras
        data = bundle.getString ("prev")

        Log.i("test","prev = $data")
//        Log.i("test","prev = $prev")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
//        sliderView = findViewById(R.id.sliderView) as SliderView
        homeSliderView = findViewById(R.id.homeSliderView) as ViewPager
        mLinearLayout = findViewById(R.id.homePageContainer) as LinearLayout
        setupSlider()

//        var font = createFromAsset( getAssets(), "font/fa-brands-400.ttf" ) as Typeface
//        Log.i("test","font $font")
////        var fontAwesomeFont = createFromAsset(getAssets(), "fa-brands-400.ttf") as Typeface
//
        var button = findViewById( R.id.menu1 ) as TextView
//        Log.i("test","button $button")
////        button.setTypeface(font)
////        button.setTypeface(FontTypeface.get("FontAwesome.otf", context))
//        button.typeface = font
//
//        button.typeface = FontManager.getTypeface(FontManager.font))

        val iconFont = FontManager.getTypeface(applicationContext, FontManager.FONTAWESOME)
//        FontManager.markAsIconContainer(findViewById(R.id.font_awesome_cubes_icon), iconFont)
        button.typeface = iconFont
//        this.SignUp.finish()

    }

    private fun setupSlider() {
//        sliderView!!.setDurationScroll(800)
        val fragments = ArrayList<Fragment>()
        fragments.add(ContentFragment.newInstance("test2"))
        fragments.add(ContentFragment.newInstance("image3"))
        fragments.add(ContentFragment.newInstance("i4808001"))
        fragments.add(ContentFragment.newInstance("i5409601"))

        mAdapter = SliderOwn.CustomPagerAdapter2(supportFragmentManager, fragments)
        homeSliderView!!.setAdapter(mAdapter)
        homeSliderView!!.setCurrentItem(fragments.size*100);
        mIndicator = SliderIndicator(this, mLinearLayout!!, homeSliderView!!, R.drawable.indicator_circle)
//        mIndicator = MyPageIndicator(this, mLinearLayout, sliderView!!, R.drawable.indicator_circle)
        mIndicator!!.setPageCount(fragments.size)
        mIndicator!!.show()
    }

}