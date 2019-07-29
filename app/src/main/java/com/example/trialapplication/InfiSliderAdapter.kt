package com.example.trialapplication

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class InfiSliderAdapter(internal var lstImages: Array<Int>, internal var context: Context) : PagerAdapter() {
    internal var layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return lstImages.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(R.layout.infi_slider_layout, container, false)
        val imageView = view.findViewById(R.id.infiImageView) as ImageView
        imageView.setImageResource(lstImages[position])
        container.addView(view)
        return view
    }
}