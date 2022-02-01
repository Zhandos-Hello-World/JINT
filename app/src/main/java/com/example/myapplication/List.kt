package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class List : AppCompatActivity() {
    companion object {
        val id = mutableListOf<String>()
        val numCode = mutableListOf<String>()
        val charCode = mutableListOf<String>()
        val nominal = mutableListOf<Int>()
        val name = mutableListOf<String>()
        val value = mutableListOf<Double>()
        val previous = mutableListOf<Double>()
        private const val url = "https://www.cbr-xml-daily.ru/daily_json.js"
        private var requested = false
    }

    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listView = findViewById(R.id.list)
        findViewById<Button>(R.id.cancel).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


        if (requested) {
            listView?.adapter = ArrayAdapter(this,
                android.R.layout.simple_expandable_list_item_1,
                android.R.id.text1,
                name)
        }
        else {
            Toast.makeText(this, "Request", Toast.LENGTH_LONG).show()
            request()
        }

        listView?.setOnItemClickListener { _, _, _, id ->
            val intent = Intent(this, MainActivity::class.java)
            Log.d("idCustom", id.toString())
            intent.putExtra(MainActivity.EXTRA_VALUE, id)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("ROTATE", true)
        super.onSaveInstanceState(outState)
    }


    private fun request() {
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    clearValues()
                    val json = response.getJSONObject("Valute")

                    for (i in json.keys()) {
                        val tempJSON = json.getJSONObject(i)
                        id.add(tempJSON.getString("ID"))
                        numCode.add(tempJSON.getString("NumCode"))
                        charCode.add(tempJSON.getString("CharCode"))
                        nominal.add(tempJSON.getInt("Nominal"))
                        name.add(tempJSON.getString("Name"))
                        value.add(tempJSON.getDouble("Value"))
                        previous.add(tempJSON.getDouble("Previous"))
                    }
                    listView?.adapter = ArrayAdapter(this,
                        android.R.layout.simple_expandable_list_item_1,
                        android.R.id.text1,
                        name)
                } catch (ex: JSONException) {
                    Toast.makeText(this, "Wrong request", Toast.LENGTH_LONG).show()
                }
            },
            { Toast.makeText(this, "Error", Toast.LENGTH_LONG).show() })
        queue.add(jsonObjectRequest)
        requested = true
    }
    private fun clearValues() {
        id.clear()
        numCode.clear()
        charCode.clear()
        nominal.clear()
        name.clear()
        name.clear()
        value.clear()
        previous.clear()
    }

}