package com.example.trialapplication._sliders

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

import java.util.ArrayList

/**
 * Created by bagicode on 12/04/17.
 */

class SliderPagerAdapter(fm: FragmentManager, frags: List<Fragment>) : FragmentStatePagerAdapter(fm) {

    internal var mFrags: List<Fragment> = ArrayList()

    init {
        mFrags = frags
    }

    override fun getItem(position: Int): Fragment {
        val index = position % mFrags.size
        return FragmentSlider.newInstance(mFrags[index].arguments!!.getString("params"))
    }

    override fun getCount(): Int {
        return Integer.MAX_VALUE
    }

    companion object {

        private val TAG = "SliderPagerAdapter"
    }

}