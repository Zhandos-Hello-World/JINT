package com.example.myapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0L,

    @SerializedName("ID")
    @Expose
    @ColumnInfo(name = "currency_id")
    var currencyId: String = "",

    @SerializedName("NumCode")
    @Expose
    @ColumnInfo(name = "num_code")
    var numCode: String = "",

    @SerializedName("CharCode")
    @Expose
    @ColumnInfo(name = "char_code")
    var charCode: String = "",

    @SerializedName("Nominal")
    @Expose
    var nominal: Int = 0,
    @SerializedName("Name")
    @Expose
    var name: String = "",
    @SerializedName("Value")
    @Expose
    var value: Double = 0.0,
    @SerializedName("Previous")
    @Expose
    var previous: Double = 0.0
)