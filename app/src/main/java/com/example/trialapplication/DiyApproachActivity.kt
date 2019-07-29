package com.example.trialapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout

//import com.alimuzaffar.loopingviewpagerdemo.customindicator.MyPageIndicator

import java.util.ArrayList

class DiyApproachActivity : AppCompatActivity() {
//
//    internal var mPager: ViewPager
//    internal var mLinearLayout: LinearLayout
//    internal var mAdapter: CustomPagerAdapter2
//    internal var mIndicator: MyPageIndicator
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_diy_approach)
//        mPager = findViewById<View>(R.id.pager)
//        mLinearLayout = findViewById<View>(R.id.pagesContainer)
//        val fragments = ArrayList<Fragment>()
//        fragments.add(ContentFragment.newInstance("Hello"))
//        fragments.add(ContentFragment.newInstance("World"))
//        fragments.add(ContentFragment.newInstance("!!!!!"))
//        fragments.add(ContentFragment.newInstance("~~~~"))
//        mAdapter = CustomPagerAdapter2(supportFragmentManager, fragments)
//        mPager.adapter = mAdapter
//        mIndicator = MyPageIndicator(this, mLinearLayout, mPager, R.drawable.indicator_circle)
//        mIndicator.setPageCount(fragments.size)
//        mIndicator.show()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mIndicator.cleanup()
//    }
//
//    internal class CustomPagerAdapter2(fm: FragmentManager, frags: List<Fragment>) : FragmentStatePagerAdapter(fm) {
//
//        var mFrags: List<Fragment> = ArrayList()
//
//        init {
//            mFrags = frags
//        }
//
//        override fun getItem(position: Int): Fragment {
//            val index = position % mFrags.size
//            return mFrags[index]
//        }
//
//        override fun getCount(): Int {
//            return Integer.MAX_VALUE
//        }
//
//    }
}
