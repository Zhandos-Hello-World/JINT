package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_VALUE = "VALUTE"
        private const val currentData = "CURRENT"
        var id = -1
    }

    private var btnGetValute:Button? = null
    private var values: ListView? = null


    var valuteText = "Выбрать"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetValute = findViewById(R.id.get_valute)
        values = findViewById(R.id.values)

        if (intent.hasExtra(EXTRA_VALUE)) {
            val idValute = intent.getLongExtra(EXTRA_VALUE, -1).toInt()
            if (idValute != -1) {
                id = idValute
                valuteText = List.charCode[id]
                savedInstanceState?.putInt(currentData, id)
            }
            else {
                Toast.makeText(this, "Intent didn't work", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            if (savedInstanceState?.containsKey(currentData) == true) {
                id = savedInstanceState.getInt(currentData)
                valuteText = List.charCode[id]
            }
            else {
                valuteText = if (id != -1) {
                    List.charCode[id]
                } else {
                    "Выбрать"
                }
            }
        }
        initializing()
        btnGetValute?.setOnClickListener {
            val intent = Intent(this, List::class.java)
            startActivity(intent)
        }

    }
    private fun initializing() {
        if (id != -1) {
            btnGetValute?.text = List.charCode[id]

            val list = mutableListOf<String>()
            list.add("ID: ${List.id[id]}")
            list.add("Num code: ${List.numCode[id]}")
            list.add("Char code: ${List.charCode[id]}")
            list.add("Name: ${List.name[id]}")
            list.add("Value: ${List.value[id]}")
            list.add("Previous: ${List.value[id]}")


            values?.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
            android.R.id.text1, list)
        }
    }
}