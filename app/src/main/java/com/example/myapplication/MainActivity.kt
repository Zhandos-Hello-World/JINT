package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_VALUE = "VALUTE"
        private const val currentData = "CURRENT"
    }

    private var btnGetValute:Button? = null


    var valuteText = "Выбирать"
    private var currentValute: CurrentData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentValute = savedInstanceState?.getSerializable(currentData) as CurrentData?
        Log.d("NullableCustom", (currentValute == null).toString())


        if (intent.hasExtra(EXTRA_VALUE)) {
            val id = intent.getLongExtra(EXTRA_VALUE, -1).toInt()
            if (id != -1) {
                valuteText = List.charCode[id]

                currentValute = CurrentData(List.id[id], List.numCode[id],
                    List.charCode[id], List.nominal[id], List.name[id],
                    List.value[id], List.previous[id])

            }
        }


        btnGetValute = findViewById(R.id.get_valute)
        btnGetValute?.text = currentValute?.charCode ?: valuteText
        btnGetValute?.setOnClickListener {
            val intent = Intent(this, List::class.java)
            startActivity(intent)
        }



    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(currentData, currentValute)
        super.onSaveInstanceState(outState)
    }


    class CurrentData(var id: String, var numCode: String, var charCode: String,
                        var nominal: Int, var name: String, var value: Double,
                        var previous: Double) : Serializable
}