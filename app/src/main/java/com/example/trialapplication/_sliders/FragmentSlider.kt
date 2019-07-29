package com.example.trialapplication._sliders

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.example.trialapplication.R


/**
 * Created by bagicode on 12/04/17.
 */

class FragmentSlider : Fragment() {

    private var imageUrls: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imageUrls = arguments!!.getString(ARG_PARAM1)
        val view = inflater.inflate(R.layout.slider_own_item, container, false)
        val img = view.findViewById(R.id.img) as ImageView
//        val mDrawableName = imageUrls
//        val resID = resources.getIdentifier(mDrawableName, "drawable", getPackageName())
//
//        val name = "your_drawable"
//        val field = R.drawable.getField(name)
//        val id = field.getInt(null)
//        val drawable = resources.getDrawable(id)

        Glide.with(activity!!)
            .load(imageUrls)
            .placeholder(R.drawable.test2)
//            .placeholder(resID)
            .into(img)
        return view
    }

    companion object {

        private val ARG_PARAM1 = "params"

        fun newInstance(params: String): FragmentSlider {
            val fragment = FragmentSlider()
            val args = Bundle()
            args.putString(ARG_PARAM1, params)
            fragment.arguments = args
            return fragment
        }
    }
}