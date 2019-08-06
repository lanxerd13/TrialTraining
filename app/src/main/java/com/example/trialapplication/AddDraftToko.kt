package com.example.trialapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.slider_own_item.view.*
import okhttp3.OkHttpClient

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap
import android.R.attr.y
import android.widget.TextView
import android.widget.DatePicker



internal class AddDraftToko : AppCompatActivity(), AdapterView.OnItemSelectedListener, View.OnFocusChangeListener {

    private var namaToko: TextView? = null
    private var namaTokoText: EditText? = null

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        val id = v!!.getId()
        when (id) {
            R.id.addTokoName -> if (hasFocus) {
//                namaToko!!.setTextColor(R.color.headerFooterColor.y)
                namaToko!!.setTextColor(getResources().getColor(R.color.headerFooterColor))
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.i("tag","view $view")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_draft_toko)

        val actionBar = supportActionBar
        actionBar!!.title = "Pengajuan Toko"
        actionBar.setDisplayHomeAsUpEnabled(true)

        var spinner: Spinner = findViewById<Spinner>(R.id.addTipeSparePartSpinner)
        spinner!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, R.layout.country_spinner, arrayOf("Roda 2", "Roda 4"))
        aa.setDropDownViewResource(R.layout.country_spinner)
        with(spinner)
        {
            adapter = aa
//            setSelection(0, false)
//            onItemSelectedListener = this@AddDraftToko
            prompt = "Select your favourite language"
            gravity = Gravity.START
//            android:gravity="end"
        }

        namaToko = findViewById<EditText>(R.id.addTokoNameText)
        namaTokoText = findViewById<EditText>(R.id.addTokoName)
        namaTokoText!!.setOnFocusChangeListener(this)
        Log.i("tag","focus ${getCurrentFocus()}")

        doAsync {
            Log.d("tag", "=== Masuk ===")
            var result = URL("http://122.248.38.212:8882/api/get-province-list").readText()
            Log.d("tag", "result $result")


//            val map = Gson().fromJson(result, Map::class.java)
//            Log.d("tag", "JSONObject ${JSONObject.toString()}")
//            Log.d("tag", "map ${map.toString()}")
//            Log.d("tag", "map ${map.get("result").toString()}")
//            val map2 = Gson().fromJson(map.get("result").toString(), Map::class.java)
//            Log.d("tag", "map2 ${map2.toString()}")
//            Log.d("tag", "map2 ${map2.get("province_list").toString()}")

            val crunchifyMap = HashMap<String, String>()
            crunchifyMap["Google"] = "San Jose"
            crunchifyMap["Facebook"] = "Mountain View"
            crunchifyMap["Crunchify"] = "NYC"
            crunchifyMap["Twitter"] = "SFO"
            crunchifyMap["Microsoft"] = "Seattle"
            Log.d("tag", "crunchifyMap ${crunchifyMap.toString()}")

            val gsonMapBuilder = GsonBuilder()

            val gsonObject = gsonMapBuilder.create()

            val JSONObject = gsonObject.toJson(crunchifyMap)
            Log.d("tag", "JSONObject ${JSONObject.toString()}")


            val map = Gson().fromJson(result, Map::class.java)
            Log.d("tag", "JSONObject123 ${map.toString()}")
            Log.d("tag", "JSONObject123 ${map.get("result").toString()}")
            val JSONObject123 = gsonObject.toJson(map)
            Log.d("tag", "JSONObject123 ${JSONObject123.toString()}")

            val map1 = Gson().fromJson(JSONObject123, Map::class.java)
            Log.d("tag", "map1 ${map1.toString()}")
            val JSONObject234 = gsonObject.toJson(map1.get("result"))
            Log.d("tag", "JSONObject234 ${JSONObject234.toString()}")

            val map2 = Gson().fromJson(JSONObject234, Map::class.java)
            Log.d("tag", "map2 ${map2.toString()}")
            Log.d("tag", "map2 ${map2.get("province_list").toString()}")
            val JSONObject345 = gsonObject.toJson(map1.get("province_list"))
            Log.d("tag", "JSONObject345 ${JSONObject345.toString()}")

            Log.d("tag", "map1 jsonobject ${JSONObject(map1) is JSONObject}")
            Log.d("tag", "map2 jsonobject ${JSONObject(map2) is JSONObject}")

            handleJSONObject(JSONObject(map1))
            handleJSONObject(JSONObject(map2))

//            if (JSONObject(map2) is JSONObject) {
//                handleJSONObject(JSONObject(map2))
//            }  else {
////            logger.info("Value: {0}", value)
//                Log.d("tag", "value ${map2.toString()}")
//            }

//            val my_json = JSONObject234.get().obj
//            val items = my_json.getJSONArray("items")



//            val prettyGson = GsonBuilder().setPrettyPrinting().create()
//            val prettyJson = prettyGson.toJson(crunchifyMap)
//            Log.d("tag", "prettyGson ${prettyGson.toString()}")
//            Log.d("tag", "prettyJson ${prettyJson.toString()}")

//            val gson = GsonBuilder().setPrettyPrinting().create()
//            var personMap: Map<String, String> = gson.fromJson(result, object : TypeToken<Map<String, String>>() {}.type)
//            Log.d("tag", "personMap ${personMap.toString()}")
//
//            var string2:String = personMap.get("result").toString()
//            var string2:String = personMap.get
//            Log.d("tag", "string2 $string2")
//
//            var personMap2: Map<String, Any> = gson.fromJson(string2, object : TypeToken<Map<String, Any>>() {}.type)
//            Log.d("tag", "personMap2 ${personMap2.toString()}")
//
//            var string3:String = personMap.get("province_list").toString()
//            Log.d("tag", "string3 $string3")
//            var personMap3: Map<String, Any> = gson.fromJson(string3, object : TypeToken<Map<String, Any>>() {}.type)
//            Log.d("tag", "personMap3 $personMap3")
//
//            personMap3.forEach {
//                Log.d("tag", "foreach personmap $it")
//            }
//
//            Log.d("tag", "=== Map to JSON ===")
//            val jsonPersonMap: String = gson.toJson(personMap2)
//            Log.d("tag", "jsonPersonMap $jsonPersonMap")

            uiThread {
                Log.d("Request", result)
                longToast("Request performed")
            }
        }
//
//        with(result.openConnection() as HttpURLConnection) {
//            requestMethod = "GET"  // optional default is GET
//
//            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")
//
////            inputStream.bufferedReader().use {
////                it.lines().forEach { line ->
////                    println(line)
////                }
////            }
//        }
//        Log.d("tag", "result $result")

//        doAsync {
//            val result1 = URL("https://jsonplaceholder.typicode.com").readText()
//            Log.d("tag", "result1 $result1")
//
//            uiThread {
//                Log.d("Request", result1)
//                longToast("Request performed")
//            }
//        }

//         get post data
//        val postServices = DataRepository.create()
//        Log.d("tag", "postService $postServices")
//        postServices.getPosts().enqueue(object : Callback<List<PostModel>> {
//
//            override fun onResponse(call: Call<List<PostModel>>, response: Response<List<PostModel>>) {
//                if (response.isSuccessful) {
//                    val data = response.body()
//                    Log.d("tag", "responsennya ${data?.size}")
//
//                    data?.map {
//                        Log.d("tag", "datanya ${it.body}")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<PostModel>>, error: Throwable) {
//                Log.e("tag", "errornya ${error.message}")
//            }
//        })
    }

    fun clickDataPicker(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val y = c.get(Calendar.YEAR) + 4
        val m = c.get(Calendar.MONTH) - 2
        val d = c.get(Calendar.DAY_OF_MONTH)
        var date :String? = null
        val viewAsd = view
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view , year, monthOfYear, dayOfMonth ->
            // Display Selected date in Toast
            Toast.makeText(this, """$dayOfMonth - ${monthOfYear + 1} - $year""", Toast.LENGTH_LONG).show()
            date = year.toString()
            Log.i("tag","date 123 $date")
            Log.i("tag","date 123 ${viewAsd.id}")
            when(viewAsd.id) {
                R.id.addTahunBerdiri -> {
                    Log.i("tag","date $date")
                    Log.i("tag","tahun berdiri $viewAsd.id")
                    var asd = findViewById<EditText>(viewAsd.id)
                    Log.i("tag","tahun berdiri $asd")
                    asd.setText(date.toString())
                    Log.i("tag","tahun berdiri 4 ${asd.text}")
                }
                R.id.addTahunBerakhir -> {
                    Log.i("tag","tahun berakhir $view.id")
                    var asd = findViewById<EditText>(viewAsd.id)
                    Log.i("tag","tahun berdiri $asd")
                    asd.setText(date.toString())
                }
            }
        }, year, month, day)
//        val dp = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//                var erg = ""
//                erg = dayOfMonth.toString()
//                erg += "." + (monthOfYear + 1).toString()
//                erg += ".$year"
//                Log.i("tag"," erg $erg")
////                (et1 as TextView).text = erg
//            }, y, m, d
//        )


        Log.i("tag","view $view")
        Log.i("tag","view ${view.text}")
        Log.i("tag","view ${view.id}")
        dpd.show()
//        dpd.show()
    }

    fun handleValue(value: Any) {
        (value as? JSONObject)?.let { handleJSONObject(it) }?: (value as? JSONArray)
    }

    fun handleJSONObject( jsonObject:JSONObject) {
        jsonObject.keys().forEach{
            var value = jsonObject.get(it)
            Log.d("tag", "JSONObject345 ${value.toString()}")
            handleValue(value)
        }
    }

//    void handleJSONArray(JSONArray jsonArray) {
//        jsonArray.iterator().forEachRemaining(element -> {
//            handleValue(element)
//        });
//    }

//    fun handleValue(value: Any) {
//        if (value is JSONObject) {
//            handleJSONObject(value)
//        } else if (value is JSONArray) {
////            handleJSONArray(value)
//        } else {
////            logger.info("Value: {0}", value)
//            Log.d("tag", "value ${value.toString()}")
//        }
//    }


//    fun handleJSONArray(jsonArray:JSONArray) {
//        jsonArray..iterator().forEachRemaining(element -> {
//            handleValue(element)
//        });
//    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
