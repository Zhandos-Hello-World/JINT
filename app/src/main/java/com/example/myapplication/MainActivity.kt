package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {
    companion object {
        var id = -1
    }

    private var btnGetValute:Button? = null
    private var values: ListView? = null
    private var currentValuteEditText:EditText? = null
    private var currentValuteEditRub:EditText? = null

    private var first = true


    private var valuteText = "Выбрать"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetValute = findViewById(R.id.get_valute)
        values = findViewById(R.id.values)
        currentValuteEditText = findViewById(R.id.current_valute_edit)
        currentValuteEditRub = findViewById(R.id.current_valute_edit_rub)

        if (DataManager.haveID(id)) {
            valuteText = DataManager.charCode[id]
        }
        else {
            id = DataManager.getID(this)
            valuteText = if (DataManager.haveID(id)) {
                DataManager.charCode[id]
            } else {
                "Выбрать"
            }
        }
        initializing()
        btnGetValute?.text = valuteText
        btnGetValute?.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

        currentValuteEditText?.setOnFocusChangeListener { _, _ ->  first = true}
        currentValuteEditRub?.setOnFocusChangeListener { _, _ ->  first = false}

        currentValuteEditText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (first && DataManager.haveID(id)) {
                    var str = s.toString()
                    if (str.isNotEmpty()) {
                        if (str[0] == '.') {
                            str = "0$str"
                        }
                        if (str[str.length - 1] == '.') {
                            str += "0"
                        }
                        val value = str.toDouble() * DataManager.value[id]
                        currentValuteEditRub?.setText(String.format("%.3f", value))
                    }
                    else {
                        currentValuteEditRub?.setText("")
                    }
                }
            }
        })
        currentValuteEditRub?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!first && DataManager.haveID(id)) {
                    var str = s.toString()
                    if (str.isNotEmpty()) {
                        if (str[0] == '.') {
                            str = "0$str"
                        }
                        if (str[str.length - 1] == '.') {
                            str += "0"
                        }
                        val value = str.toDouble() / DataManager.value[id]
                        currentValuteEditText?.setText(String.format("%.3f", value))
                    }
                    else {
                        currentValuteEditText?.setText("")
                    }
                }
            }
        })
    }
    private fun initializing() {
        if (DataManager.haveID(id)) {
            val list = mutableListOf<String>()
            list.add("ID: ${DataManager.id[id]}")
            list.add("Num code: ${DataManager.numCode[id]}")
            list.add("Char code: ${DataManager.charCode[id]}")
            list.add("Name: ${DataManager.name[id]}")
            list.add("Value: ${DataManager.value[id]}")
            list.add("Previous: ${DataManager.previous[id]}")

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, list)

            values?.adapter = adapter
        }
    }
}