package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

object DataManager {
    @JvmStatic
    val id = mutableListOf<String>()
    @JvmStatic
    val numCode = mutableListOf<String>()
    @JvmStatic
    val charCode = mutableListOf<String>()
    @JvmStatic
    val nominal = mutableListOf<Int>()
    @JvmStatic
    val name = mutableListOf<String>()
    @JvmStatic
    val value = mutableListOf<Double>()
    @JvmStatic
    val previous = mutableListOf<Double>()

    @JvmStatic
    fun save(context: Context) {
        saveDataToSql(context)
        setTime(context)
    }


    @JvmStatic
    fun saveDataToSql(context: Context) {
        val currentDataHelper = CurrentDataHelper(context)
        try {
            val write = currentDataHelper.writableDatabase
            write.delete("DATA", null, null)

            val contentValue = ContentValues()

            for (i in id.indices) {
                contentValue.put("id", id[i])
                contentValue.put("numCode", numCode[i])
                contentValue.put("charCode", charCode[i])
                contentValue.put("nominal", nominal[i])
                contentValue.put("name", name[i])
                contentValue.put("value", value[i])
                contentValue.put("previous", previous[i])
                write.insert("DATA", null, contentValue)
            }

            write.close()

        } catch (ex: SQLiteException) {
            Toast.makeText(context, "Проблемы с базами данных №1", Toast.LENGTH_SHORT)
                .show()
        }
        finally {
            currentDataHelper.close()
        }
    }
    @JvmStatic
    private fun setTime(context: Context) {
        val currentDataHelper = CurrentDataHelper(context)
        try {
            val write = currentDataHelper.writableDatabase
            write.delete("TIME", null, null)

            val contentValue = ContentValues()
            val currentTime= Calendar.getInstance().time

            contentValue.put("date", currentTime.time.toString())
            write.insert("TIME", null, contentValue)
            write.close()
        } catch (ex: SQLiteException) {
            Toast.makeText(context, "Проблемы с базами данных №3", Toast.LENGTH_SHORT)
                .show()
        }
        finally {
            currentDataHelper.close()
        }
    }
    @JvmStatic
    fun clearValues() {
        id.clear()
        numCode.clear()
        charCode.clear()
        nominal.clear()
        name.clear()
        name.clear()
        value.clear()
        previous.clear()
    }
    fun haveID(id: Int) = id in 0 until (this.id.size)

    fun getID(context: Context): Int {
        val currentDataHelper = CurrentDataHelper(context)
        var returnValue = -1
        try {
            val read = currentDataHelper.readableDatabase
            val cursor = read.query("CHOICE_DATA", arrayOf("_id", "id"), null,
            null, null, null, null)

            if (cursor.moveToFirst()) {
                returnValue = cursor.getInt(1)
            }
            read.close()
            cursor.close()
        } catch (ex: SQLiteException) { }
        finally {
            currentDataHelper.close()
        }
        return returnValue
    }

    fun initValues(context: Context) {
        val currentDataHelper = CurrentDataHelper(context)
        try {
            val read = currentDataHelper.readableDatabase
            val cursor = read.query("DATA", arrayOf("_id", "id", "numCode",
                "charCode", "nominal", "name", "value", "previous"), null,
                null, null, null, null)
            clearValues()
            if (cursor.moveToFirst()) {
                id.add(cursor.getString(1))
                numCode.add(cursor.getString(2))
                charCode.add(cursor.getString(3))
                nominal.add(cursor.getInt(4))
                name.add(cursor.getString(5))
                value.add(cursor.getDouble(6))
                previous.add(cursor.getDouble(7))
            }

            while (cursor.moveToNext()) {
                id.add(cursor.getString(1))
                numCode.add(cursor.getString(2))
                charCode.add(cursor.getString(3))
                nominal.add(cursor.getInt(4))
                name.add(cursor.getString(5))
                value.add(cursor.getDouble(6))
                previous.add(cursor.getDouble(7))
            }
            cursor.close()
            read.close()
        } catch (ex: SQLiteException) { }
        finally {
            currentDataHelper.close()
        }
    }


    fun getTime(context: Context): String {
        val currentDataHelper = CurrentDataHelper(context)
        var currentTime = "отсутствует"
        try {
            val read = currentDataHelper.readableDatabase
            val cursor = read.query("TIME", arrayOf("_id", "date"), null,
                null, null, null, null)

            if (cursor.moveToFirst()) {
                val date = Date(cursor.getString(1).toLong())
                val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
                currentTime = format.format(date)?:"Null"
            }
            read.close()
            cursor.close()
        }
        catch (ex: SQLiteException) {

        }
        finally {
            currentDataHelper.close()
        }
        return currentTime
    }
    fun isEmpty() : Boolean {
        return id.size == 0
    }
}