package com.example.trialapplication

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView


class FontAwesome : TextView {


    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {

        //Font name should not contain "/".
        val tf = Typeface.createFromAsset(
            context.assets,
            "fonts/fa-brands-400.ttf"
        )
        typeface = tf
    }

//    private var context: Context? = null
//
//    constructor(context: Context) : super(context) {
//        this.context = context
//        createView()
//    }
//
//    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        this.context = context
//        createView()
//    }
//
//    private fun createView() {
//        gravity = Gravity.CENTER
//        typeface = FontTypeface.get("FontAwesome.otf", context)
//    }
}

