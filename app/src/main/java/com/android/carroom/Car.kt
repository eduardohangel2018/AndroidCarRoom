package com.android.carroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(

        @PrimaryKey(autoGenerate = true)
        var id: Int,
        @ColumnInfo(name= "name")
        var name: String,
        @ColumnInfo(name="price")
        var price: String,
        @ColumnInfo(name="year")
        var year: String,
        @ColumnInfo(name="desc")
        var desc: String) {
}