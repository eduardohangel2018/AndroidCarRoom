package com.android.carroom

import androidx.room.Database
import androidx.room.RoomDatabase

// Abstração Room do Database
@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // Retorna o DAO das Entidades
    abstract fun getCarDAO() : CarDAO
}