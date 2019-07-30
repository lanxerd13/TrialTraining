package com.example.trialapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.opengl.ETC1.getHeight
import android.view.View.MeasureSpec
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.content.Context.INPUT_METHOD_SERVICE
import android.widget.EditText
import android.os.Build
import android.graphics.drawable.Drawable
import android.view.KeyEvent.KEYCODE_DEL
import android.text.Editable
import android.text.TextWatcher
import android.view.View.OnFocusChangeListener
import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import java.util.*
import java.util.concurrent.TimeUnit


class VerificationPage : AppCompatActivity(), View.OnFocusChangeListener, View.OnKeyListener, TextWatcher
{
    private var isCancelled = false
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.verification_page)
//    }

//    inner class MainActivity : Activity(), View.OnFocusChangeListener, View.OnKeyListener, TextWatcher {
        private var mPinFirstDigitEditText: EditText? = null
        private var mPinSecondDigitEditText: EditText? = null
        private var mPinThirdDigitEditText: EditText? = null
        private var mPinForthDigitEditText: EditText? = null
        private var mPinFifthDigitEditText: EditText? = null
        private var mPinHiddenEditText: EditText? = null
        private var countdownTimerText: TextView? = null
        private var resendButton: TextView? = null

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        /**
         * Hides soft keyboard.
         *
         * @param editText EditText which has focus
         */
        fun hideSoftKeyboard(editText: EditText?) {
            if (editText == null)
                return

            val imm = getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0)
        }

        public override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.verification_page)

            init()
            setPINListeners()
            countdownTimer()

            resendButton!!.setOnClickListener{
                // Start the timer
                isCancelled = true
//                it.isEnabled = false
//                button_start.isEnabled = true
            }
        }

        private fun countdownTimer(){
            val minute:Long = 1000 * 60 // 1000 milliseconds = 1 second
            val millisInFuture:Long = (minute * 5)
            val countDownInterval:Long = 1000

            timer(millisInFuture,countDownInterval).start()
            isCancelled = false

//            button_start.setOnClickListener{
//                // Start the timer
//                timer(millisInFuture,countDownInterval).start()
//                it.isEnabled = false
//                button_stop.isEnabled = true
//
//                isCancelled = false
//            }
        }

        private fun timer(millisInFuture:Long,countDownInterval:Long): CountDownTimer {
            return object: CountDownTimer(millisInFuture,countDownInterval){
                override fun onTick(millisUntilFinished: Long){
                    val timeRemaining = timeString(millisUntilFinished)
                    if (isCancelled){
                        cancel()
                        isCancelled = false
//                        countdownTimerText!!.text = ""
                        countdownTimer()
                    }else{
                        countdownTimerText!!.text = timeRemaining
                    }
                }

                override fun onFinish() {
                    countdownTimerText!!.text = "Done"

//                    button_start.isEnabled = true
//                    button_stop.isEnabled = false
                }
            }
        }

        private fun timeString(millisUntilFinished:Long):String{
            Log.i("test","masuk timer string")
            var millisUntilFinished:Long = millisUntilFinished
            val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
            millisUntilFinished -= TimeUnit.DAYS.toMillis(days)

            val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
            millisUntilFinished -= TimeUnit.HOURS.toMillis(hours)

            val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
            millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes)

            val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)

            // Format the string
            return String.format(
                Locale.getDefault(),
//                "%02d day: %02d hour: %02d min: %02d sec",
                "%02d: %02d",
                minutes,seconds
            )
        }

        private fun resetTImer(view : View){
            Log.i("test","masuk reset timer")
            countdownTimer()
        }
        /**
         * Initialize EditText fields.
         */
        private fun init() {
            mPinFirstDigitEditText = findViewById<EditText>(R.id.pin_first_edittext)
            mPinSecondDigitEditText = findViewById<EditText>(R.id.pin_second_edittext)
            mPinThirdDigitEditText = findViewById<EditText>(R.id.pin_third_edittext)
            mPinForthDigitEditText = findViewById<EditText>(R.id.pin_forth_edittext)
            mPinFifthDigitEditText = findViewById<EditText>(R.id.pin_fifth_edittext)
            mPinHiddenEditText = findViewById<EditText>(R.id.pin_hidden_edittext)

            countdownTimerText = findViewById<TextView>(R.id.countdownTimerText)
            resendButton = findViewById<TextView>(R.id.resend)
        }

//    fun signUp(view: View) {
//        private var mPinFirstDigitEditText: EditText? = null
//        private var mPinSecondDigitEditText: EditText? = null
//        private var mPinThirdDigitEditText: EditText? = null
//        private var mPinForthDigitEditText: EditText? = null
//        private var mPinFifthDigitEditText: EditText? = null
//        private var mPinHiddenEditText: EditText? = null
//
//        val phoneNo = findViewById<EditText>(R.id.handphoneNo)
//        val countrySpinner = findViewById<TextView>(R.id.errorMessage)
//        val errorMessage = findViewById<TextView>(R.id.errorMessage)
//        val message = phoneNo.text.toString()
//        Log.i("test","message $message")
//        Log.i("test","message ${message.toIntOrNull()}")
//
//        // validasi message harus angka
//        if(message.toIntOrNull() === null){
//            errorMessage.text = "Nomor handphone tidak valid"
//        }else{
//            errorMessage.text = ""
//            Log.i("test", "pos ${textView_msg+phoneNo.text}")
//            startActivity(Intent(this, VerificationPage::class.java))
//        }


//        Log.i("test","message ${message.intOrString()}")

//        startActivity(Intent(this, MainActivity::class.java))
//    }


        override fun onFocusChange(v: View, hasFocus: Boolean) {
            Log.i("test","masuk on focus change ??")
            val id = v.getId()
            when (id) {
                R.id.pin_first_edittext -> if (hasFocus) {
                    setFocus(mPinHiddenEditText)
                    showSoftKeyboard(mPinHiddenEditText)
                }

                R.id.pin_second_edittext -> if (hasFocus) {
                    setFocus(mPinHiddenEditText)
                    showSoftKeyboard(mPinHiddenEditText)
                }

                R.id.pin_third_edittext -> if (hasFocus) {
                    setFocus(mPinHiddenEditText)
                    showSoftKeyboard(mPinHiddenEditText)
                }

                R.id.pin_forth_edittext -> if (hasFocus) {
                    setFocus(mPinHiddenEditText)
                    showSoftKeyboard(mPinHiddenEditText)
                }

                R.id.pin_fifth_edittext -> if (hasFocus) {
                    setFocus(mPinHiddenEditText)
                    showSoftKeyboard(mPinHiddenEditText)
                }
                else -> {
                }
            }
        }

        override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
            Log.i("test","masuk on key")
            if (event.getAction() === KeyEvent.ACTION_DOWN) {
                val id = v.getId()
                Log.i("test","test $id")
                when (id) {
                    R.id.pin_hidden_edittext -> if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (mPinHiddenEditText!!.text.length == 5)
                            mPinFifthDigitEditText!!.setText("")
                        else if (mPinHiddenEditText!!.text.length == 4)
                            mPinForthDigitEditText!!.setText("")
                        else if (mPinHiddenEditText!!.text.length == 3)
                            mPinThirdDigitEditText!!.setText("")
                        else if (mPinHiddenEditText!!.text.length == 2)
                            mPinSecondDigitEditText!!.setText("")
                        else if (mPinHiddenEditText!!.text.length == 1)
                            mPinFirstDigitEditText!!.setText("")

                        if (mPinHiddenEditText!!.length() > 0)
                            mPinHiddenEditText!!.setText(
                                mPinHiddenEditText!!.text.subSequence(
                                    0,
                                    mPinHiddenEditText!!.length() - 1
                                )
                            )

                        return true
                    }

                    else -> return false
                }
            }

            return false
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            Log.i("test","masuk on text changed")
            setDefaultPinBackground(mPinFirstDigitEditText)
            setDefaultPinBackground(mPinSecondDigitEditText)
            setDefaultPinBackground(mPinThirdDigitEditText)
            setDefaultPinBackground(mPinForthDigitEditText)
            setDefaultPinBackground(mPinFifthDigitEditText)

            if (s.length == 0) {
                setFocusedPinBackground(mPinFirstDigitEditText)
                mPinFirstDigitEditText!!.setText("")
            } else if (s.length == 1) {
                setFocusedPinBackground(mPinSecondDigitEditText)
                mPinFirstDigitEditText!!.setText(s[0] + "")
                mPinSecondDigitEditText!!.setText("")
                mPinThirdDigitEditText!!.setText("")
                mPinForthDigitEditText!!.setText("")
                mPinFifthDigitEditText!!.setText("")
            } else if (s.length == 2) {
                setFocusedPinBackground(mPinThirdDigitEditText)
                mPinSecondDigitEditText!!.setText(s[1] + "")
                mPinThirdDigitEditText!!.setText("")
                mPinForthDigitEditText!!.setText("")
                mPinFifthDigitEditText!!.setText("")
            } else if (s.length == 3) {
                setFocusedPinBackground(mPinForthDigitEditText)
                mPinThirdDigitEditText!!.setText(s[2] + "")
                mPinForthDigitEditText!!.setText("")
                mPinFifthDigitEditText!!.setText("")
            } else if (s.length == 4) {
                setFocusedPinBackground(mPinFifthDigitEditText)
                mPinForthDigitEditText!!.setText(s[3] + "")
                mPinFifthDigitEditText!!.setText("")
            } else if (s.length == 5) {
                setDefaultPinBackground(mPinFifthDigitEditText)
                mPinFifthDigitEditText!!.setText(s[4] + "")

                hideSoftKeyboard(mPinFifthDigitEditText)
            }
        }

        /**
         * Sets default PIN background.
         *
         * @param editText edit text to change
         */
        private fun setDefaultPinBackground(editText: EditText?) {
            editText!!.background = resources.getDrawable(R.drawable.textfield_default_holo_light)
//            setViewBackground(editText, resources.getDrawable(R.drawable.textfield_default_holo_light))
        }

        /**
         * Sets focused PIN field background.
         *
         * @param editText edit text to change
         */
        private fun setFocusedPinBackground(editText: EditText?) {
            Log.i("test","masuk sini?")
            editText!!.background = resources.getDrawable(R.drawable.textfield_focused_holo)
//            setViewBackground(editText, resources.getDrawable(R.drawable.textfield_focused_holo_light))
        }

        /**
         * Sets listeners for EditText fields.
         */
        @SuppressLint("ClickableViewAccessibility")
        private fun setPINListeners() {
            Log.i("test","masuk on pin listener")
            mPinHiddenEditText!!.addTextChangedListener(this)

            mPinFirstDigitEditText!!.setOnFocusChangeListener(this)
            mPinSecondDigitEditText!!.setOnFocusChangeListener(this)
            mPinThirdDigitEditText!!.setOnFocusChangeListener(this)
            mPinForthDigitEditText!!.setOnFocusChangeListener(this)
            mPinFifthDigitEditText!!.setOnFocusChangeListener(this)

            mPinFirstDigitEditText!!.setOnKeyListener(this)
            mPinSecondDigitEditText!!.setOnKeyListener(this)
            mPinThirdDigitEditText!!.setOnKeyListener(this)
            mPinForthDigitEditText!!.setOnKeyListener(this)
            mPinFifthDigitEditText!!.setOnKeyListener(this)
            mPinHiddenEditText!!.setOnKeyListener(this)

            mPinFirstDigitEditText!!.setOnTouchListener{ v, event ->
                if (event.action ==  KeyEvent.ACTION_UP) {
                    Log.i("test"," ${event.action} ")
                    Log.i("test"," ${KeyEvent.ACTION_UP} ")
                    Log.i("test"," ${mPinFirstDigitEditText!!.text} ")
                    Log.i("test"," ${mPinFirstDigitEditText!!.text.toString()}  ")
                    Log.i("test"," ${mPinFirstDigitEditText!!.text.toString() == ""}  ")
                    Log.i("test"," ${mPinFirstDigitEditText!!.text.toString() === ""}  ")
                    Log.i("test"," ${mPinFirstDigitEditText!!.text.toString() === null}  ")
                    if(mPinFirstDigitEditText!!.text.toString() == ""){
                        Log.i("test" , "masik sini")
                        setFocusedPinBackground(mPinFirstDigitEditText)
                    }
                }
                false
            }
        }

        /**
         * Sets background of the view.
         * This method varies in implementation depending on Android SDK version.
         *
         * @param view       View to which set background
         * @param background Background to set to view
         */
        fun setViewBackground(view: View?, background: Drawable?) {
            if (view == null || background == null)
                return

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                view!!.setBackground(background)
            } else {
                view!!.setBackgroundDrawable(background)
            }
        }

        /**
         * Shows soft keyboard.
         *
         * @param editText EditText which has focus
         */
        fun showSoftKeyboard(editText: EditText?) {
            if (editText == null)
                return

            val imm = getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, 0)
        }

        /**
         * Custom LinearLayout with overridden onMeasure() method
         * for handling software keyboard show and hide events.
         */
//        inner class MainLayout(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {
//
//            init {
//                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//                inflater.inflate(R.layout.verification_page, this)
//            }
//
//            override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//                val proposedHeight = MeasureSpec.getSize(heightMeasureSpec)
//                val actualHeight = height
//
//                Log.d("TAG", "proposed: $proposedHeight, actual: $actualHeight")
//
//                if (actualHeight >= proposedHeight) {
//                    // Keyboard is shown
//                    if (mPinHiddenEditText!!.length() == 0)
//                        setFocusedPinBackground(mPinFirstDigitEditText)
//                    else
//                        setDefaultPinBackground(mPinFirstDigitEditText)
//                }
//
//                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//            }
//        }

        companion object {

            /**
             * Sets focus on a specific EditText field.
             *
             * @param editText EditText to set focus on
             */
            fun setFocus(editText: EditText?) {
                if (editText == null)
                    return

                editText.isFocusable = true
                editText.isFocusableInTouchMode = true
                editText.requestFocus()
            }
        }


    }

