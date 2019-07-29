package com.example.trialapplication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ContentFragment : Fragment() {

    private var mParam1: String? = null
    private var imageUrls: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imageUrls = arguments!!.getString(ARG_PARAM1)
        val view = inflater.inflate(R.layout.slider_own_item, container, false)
        val text = view.findViewById(R.id.text) as TextView
//        val view = inflater.inflate(R.layout.slider_own_item, container, false)
        val img = view.findViewById(R.id.img) as ImageView

//        val mDrawableName = "myimg"
        val resID = resources.getIdentifier(imageUrls, "drawable",getActivity()!!.getPackageName())
        var asd = getActivity()!!.getPackageName()
        var asd2 = R.drawable.test2
        Log.i("test","$asd")
        Log.i("test","$resID")
        Log.i("test","$asd2")
        text.text = mParam1
        Glide.with(activity)
            .load(imageUrls)
//            .placeholder(R.drawable.test2)
            .placeholder(resID)
            .into(img)
        return view
    }

    fun getmParam1(): String? {
        return mParam1
    }

    companion object {
        private val ARG_PARAM1 = "param1"

        fun newInstance(param1: String): ContentFragment {
            val fragment = ContentFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
