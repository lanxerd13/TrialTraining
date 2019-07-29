package com.example.trialapplication

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import id.zelory.compressor.Compressor
import java.io.File


class ViewPagerAdapterForSliderOtherAgain(private val context: Context) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private val images = arrayOf<Int>(R.drawable.test2, R.drawable.image3, R.drawable.i4808001, R.drawable.i5409601, R.drawable.i60010241)

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.activity_slider_other_again_layout, null)
        val imageView = view.findViewById(R.id.imageViewOther) as ImageView
        imageView.setImageResource(images[position])

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)

    }
}