package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class ListActivity : AppCompatActivity() {
    companion object {
        private const val url = "https://www.cbr-xml-daily.ru/daily_json.js"
        private var TitleTime = "Последний синхронизаций: "
        private var currentTime = "отсутствует"
    }

    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        setSupportActionBar(findViewById(R.id.tool_bar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setIcon(R.drawable.picture1)


        listView = findViewById(R.id.list)

        findViewById<Button>(R.id.cancel).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        DataManager.initValues(this)


        if (DataManager.isEmpty()) {
            request()
        }
        else {
            val list = Array(DataManager.name.size)
            {"[${DataManager.charCode[it]}]  ${DataManager.name[it]}"}

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
            android.R.id.text1, list)

            listView?.adapter = adapter
        }
        currentTime = DataManager.getTime(this)

        listView?.setOnItemClickListener { _, _, _, id ->
            MainActivity.id = id.toInt()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("ROTATE", true)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sync) {
            request()
            currentTime = DataManager.getTime(this)
            Toast.makeText(this, "Обновлено", Toast.LENGTH_SHORT).show()
        }
        else if(item.itemId == R.id.date) {
            Toast.makeText(this, TitleTime + currentTime, Toast.LENGTH_LONG).
            show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun request() {
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    DataManager.clearValues()
                    val json = response.getJSONObject("Valute")

                    for (i in json.keys()) {
                        val tempJSON = json.getJSONObject(i)
                        DataManager.id.add(tempJSON.getString("ID"))
                        DataManager.numCode.add(tempJSON.getString("NumCode"))
                        DataManager.charCode.add(tempJSON.getString("CharCode"))
                        DataManager.nominal.add(tempJSON.getInt("Nominal"))
                        DataManager.name.add(tempJSON.getString("Name"))
                        DataManager.value.add(tempJSON.getDouble("Value") /
                                DataManager.nominal.last())
                        DataManager.previous.add(tempJSON.getDouble("Previous") /
                                DataManager.nominal.last())
                    }
                    listView?.adapter = ArrayAdapter(this,
                        android.R.layout.simple_expandable_list_item_1,
                        android.R.id.text1,
                        DataManager.name)

                    DataManager.save(this)
                } catch (ex: JSONException) {
                    Toast.makeText(this, "Неправильный запрос", Toast.LENGTH_LONG).show()
                }
            },
            { Toast.makeText(this, "Нет связи", Toast.LENGTH_LONG).show() })
        queue.add(jsonObjectRequest)
    }
}