package com.example.trialapplication


import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.slider_own_item.view.*
import android.widget.TextView
import android.view.ViewGroup
import android.widget.ArrayAdapter



class SignUp : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var textView_msg:String? = null

    var list_of_items = arrayOf("+62", "+65", "+64")

    override fun onBackPressed() {
        startActivity(Intent(this, SliderOtherAgain::class.java))
        overridePendingTransition( android.R.anim.slide_out_right, android.R.anim.slide_out_right)
        finish()
        // do something
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_page)

        val mPrefs = getSharedPreferences("label", 0)
        var imageView : ImageView = findViewById(R.id.imageViewSignUp) as ImageView
        var activity = getApplicationContext().getPackageName()
//        var textView_msg =findViewById<TextView>(R.id.msg)
        var spinner: Spinner = findViewById<Spinner>(R.id.countrySpinner)
        val phoneNo = findViewById<EditText>(R.id.handphoneNo)
        val currentRegisteredPhoneNo = mPrefs.getString("currentSignUpPhoneNo", "")
        phoneNo.setText(currentRegisteredPhoneNo)

        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
//        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items)
        val aa = ArrayAdapter(this, R.layout.country_spinner, list_of_items)
//        aa.setDropDownViewResource()
        // Set layout to use when the list of choices appear
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_item)
        aa.setDropDownViewResource(R.layout.country_spinner)
        // Set Adapter to Spinner

        with(spinner)
        {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@SignUp
            prompt = "Select your favourite language"
            gravity = Gravity.END

//            android:gravity="end"
        }

        phoneNo.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
//                return@OnKeyListener true
                Log.i("test","klik enter")
                this.signUp(v)
            }
            false
        })

//        Log.i("test","call activity ${getApplicationContext().getPackageName()}")
//        Log.i("test","call activity ${getApplicationContext()}")
//        Log.i("test","call activity ${this}")
//        Log.i("test","call activity $callingActivity")
//        Log.i("test","call activity $imageView")
//        Log.i("test","call activity ${imageView.text}")

//        Glide.with(this)
//            .load(imageView.text)
////            .placeholder(R.drawable.test2)
//            .placeholder(R.drawable.test2)
//            .into(imageView)

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.i("test", "pos $p2")
        textView_msg = list_of_items[p2]
    }


    fun mainActivity(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun signUp(view: View) {
        val phoneNo = findViewById<EditText>(R.id.handphoneNo)
        val countrySpinner = findViewById<TextView>(R.id.errorMessage)
        val errorMessage = findViewById<TextView>(R.id.errorMessage)
        val message = phoneNo.text.toString()
        Log.i("test","message $message")
        Log.i("test","message ${message.length}")
        Log.i("test","message ${message.toLongOrNull()}")
        Log.i("test","message ${message.toLongOrNull(15)}")

        // validasi message harus angka
        if(message.toLongOrNull() === null){
            errorMessage.text = "Nomor handphone tidak valid"
        }else{
            errorMessage.text = ""
            Log.i("test", "pos ${textView_msg+phoneNo.text}")

            // update current phone nomor yg sendang signup
            val mPrefs = getSharedPreferences("label", 0)
            val mEditor = mPrefs.edit()
            mEditor.putString("currentSignUpPhoneNo", message).commit()

            startActivity(Intent(this, VerificationPage::class.java))
            finish()
        }


//        Log.i("test","message ${message.intOrString()}")

//        startActivity(Intent(this, MainActivity::class.java))
    }

//    fun String.intOrString(): Any {
//        val v = toIntOrNull()
//        return when(v) {
//            null -> this
//            else -> v
//        }
//    }


}