package com.example.trialapplication

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*
import java.util.concurrent.TimeUnit


class VerificationPage : AppCompatActivity(), View.OnFocusChangeListener, View.OnKeyListener, TextWatcher
{
    private var isCancelled = false

        private var mPinFirstDigitEditText: EditText? = null
        private var mPinSecondDigitEditText: EditText? = null
        private var mPinThirdDigitEditText: EditText? = null
        private var mPinForthDigitEditText: EditText? = null
        private var mPinFifthDigitEditText: EditText? = null
        private var mPinHiddenEditText: EditText? = null
        private var countdownTimerText: TextView? = null
        private var resendButton: TextView? = null

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

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
//            setContentView(MainLayout(this, null))

            init()
            setPINListeners()
            countdownTimer()
//            inner class MainLayout(context: Context, ""et) {
//            resendButton!!.setOnClickListener{
//                // Start the timer
//                isCancelled = true
////                button_start.isEnabled = true
//            }
        }

        private fun countdownTimer(){
            val minute:Long = 1000 * 60 // 1000 milliseconds = 1 second
            val millisInFuture:Long = (minute /12 + 1)
            val countDownInterval:Long = 1000

            timer(millisInFuture,countDownInterval).start()
            isCancelled = false
            resendButton!!.isEnabled = false

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
                    val time = timeString(millisUntilFinished)
                    if (isCancelled){
                        cancel()
                        isCancelled = false
//                        countdownTimerText!!.text = ""
                        countdownTimer()
                    }else{
                        countdownTimerText!!.text = time
                    }
                }

                override fun onFinish() {
                    countdownTimerText!!.text = String.format(Locale.getDefault(), "%02d: %02d",0,0)
                    resendButton!!.isEnabled = true
//                    button_stop.isEnabled = false
                }
            }
        }

        private fun timeString(millisUntilFinished:Long):String{
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

        fun resetTimer(view : View){
            if(countdownTimerText!!.text == String.format(Locale.getDefault(), "%02d: %02d",0,0)){
                isCancelled = false
                countdownTimer()
            }else {
                isCancelled = true
            }
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

    fun verification(view: View) {
        Log.i("test","mPinHiddenEditText text ${mPinHiddenEditText?.text}")

        if(mPinHiddenEditText?.text.toString() != "12345"){
            var verificationErrorMessage = findViewById<TextView>(R.id.verificationErrorMessage)
            verificationErrorMessage.text = "Verifikasi yang dimasukan salah"
        }else {
            // menarik data cache
            val mPrefs = getSharedPreferences("label", 0)
            Log.i("test","mprefs $mPrefs")
            val mInt = mPrefs.getInt("opened", 0)
            Log.i("test","mprefs $mInt")

            // update no hp yg sedang login, status login, dan jumlah applikasi pernah login
            val mEditor = mPrefs.edit()
            mEditor.putString("currentLoginPhoneNo", mPrefs.getString("currentSignUpPhoneNo", "")).commit()
            mEditor.putBoolean("loginStatus", true).commit()
            mEditor.putInt("opened", mInt+1).commit()

            startActivity(Intent(this, HomePage::class.java))
        }

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
//
//
//        Log.i("test","message ${message.intOrString()}")
//
//        startActivity(Intent(this, MainActivity::class.java))
    }


        override fun onFocusChange(v: View, hasFocus: Boolean) {
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
            if (event.getAction() === KeyEvent.ACTION_DOWN) {
                val id = v.getId()
                when (id) {
                    R.id.pin_hidden_edittext -> if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (mPinHiddenEditText!!.text.length == 5) {
                            mPinFifthDigitEditText!!.setText("")
                        }else if (mPinHiddenEditText!!.text.length == 4) {
                            mPinForthDigitEditText!!.setText("")
                        }else if (mPinHiddenEditText!!.text.length == 3) {
                            mPinThirdDigitEditText!!.setText("")
                        }else if (mPinHiddenEditText!!.text.length == 2) {
                            mPinSecondDigitEditText!!.setText("")
                        }else if (mPinHiddenEditText!!.text.length == 1) {
                            mPinFirstDigitEditText!!.setText("")
                        }

                        if (mPinHiddenEditText!!.length() > 0) {
                            mPinHiddenEditText!!.setText(
                                mPinHiddenEditText!!.text.subSequence(
                                    0,
                                    mPinHiddenEditText!!.length() - 1
                                )
                            )

                            //solving bug supaya bila angka yg diinput maka angka tidak berubah bila dihapus
                            mPinHiddenEditText!!.post(Runnable { mPinHiddenEditText!!.setSelection(mPinHiddenEditText!!.text.toString().length) })
                        }

                        return true
                    }
                    else -> return false
                }
            }
            return false
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

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
            editText!!.background = resources.getDrawable(R.drawable.textfield_focused_holo)
//            setViewBackground(editText, resources.getDrawable(R.drawable.textfield_focused_holo_light))
        }

        /**
         * Sets listeners for EditText fields.
         */

        private fun setPINListeners() {
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
                    if(mPinFirstDigitEditText!!.text.toString() == ""){
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
            editText.setInputType(InputType.TYPE_CLASS_NUMBER)
            val imm = getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.showSoftInput(editText, 0)
            imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
        }

        /**
         * Custom LinearLayout with overridden onMeasure() method
         * for handling software keyboard show and hide events.
         */
//        inner class MainLayout(context: Context, attributeSet : AttributeSet? ) : RelativeLayout(context,attributeSet) {
//
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

