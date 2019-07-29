package com.example.trialapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.example.trialapplication._sliders.FragmentSlider
import com.example.trialapplication._sliders.SliderIndicator
import com.example.trialapplication._sliders.SliderPagerAdapter
import com.example.trialapplication._sliders.SliderView

import com.example.trialapplication.MyPageIndicator

class SliderOwn : AppCompatActivity() {

//    private var mAdapter: SliderPagerAdapter? = null
    private var mAdapter: CustomPagerAdapter2? = null
    private var mIndicator: SliderIndicator? = null
//    private var mIndicator: MyPageIndicator? = null

//    private var sliderView: SliderView? = null
    private var sliderView: ViewPager? = null
    private var mLinearLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.slider_own)
//        sliderView = findViewById(R.id.sliderView) as SliderView
        sliderView = findViewById(R.id.sliderView) as ViewPager
        Log.i("test","$sliderView")
        mLinearLayout = findViewById(R.id.pagesContainer) as LinearLayout
        setupSlider()
    }


    private fun setupSlider() {
//        sliderView!!.setDurationScroll(800)
        val fragments = ArrayList<Fragment>()
        fragments.add(ContentFragment.newInstance("test2"))
        fragments.add(ContentFragment.newInstance("image3"))
        fragments.add(ContentFragment.newInstance("i4808001"))
        fragments.add(ContentFragment.newInstance("i5409601"))
        Log.i("test","fragment size ${fragments.size}")
        Log.i("test","fragment size ${fragments.size*100}")
        Log.i("test","fragment size ${fragments.size*100-1}")
        Log.i("test","fragment size ${(fragments.size*100-1)%fragments.size}")
        Log.i("test","fragment size ${(fragments.size*100)%fragments.size}")

//        test2, R.drawable.image3, R.drawable.i4808001, R.drawable.i5409601, R.drawable.i60010241
//        mAdapter = SliderPagerAdapter(supportFragmentManager, fragments)
        mAdapter = CustomPagerAdapter2(supportFragmentManager, fragments)
        sliderView!!.setAdapter(mAdapter)
        sliderView!!.setCurrentItem(fragments.size*100);
        mIndicator = SliderIndicator(this, mLinearLayout!!, sliderView!!, R.drawable.indicator_circle)
//        mIndicator = MyPageIndicator(this, mLinearLayout, sliderView!!, R.drawable.indicator_circle)
        mIndicator!!.setPageCount(fragments.size)
        mIndicator!!.show()
    }

    internal class CustomPagerAdapter2(fm: FragmentManager, frags: List<Fragment>) : FragmentStatePagerAdapter(fm) {

        var mFrags: List<Fragment> = java.util.ArrayList()

        init {
            mFrags = frags
        }

        override fun getItem(position: Int): Fragment {
            val index = position % mFrags.size
            return mFrags[index]
        }

        override fun getCount(): Int {
            return Integer.MAX_VALUE
        }

    }
}