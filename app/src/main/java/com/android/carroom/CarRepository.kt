package com.android.carroom

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class CarRepository (applicationContext: Application) {

    private lateinit var dao : CarDAO

    // Será executado no momento em que o objeto for instanciado
    init {

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,
        "db_cars").build()
        dao = db.getCarDAO()
    }

    fun getById(id: Int) : Car {

        return runBlocking {
            return@runBlocking dao.get(id)
        }
    }

    fun listCarLivedata() : Flow<List<Car>> {

        return runBlocking {
            return@runBlocking dao.listLiveData()
        }
    }

    fun saveCar(car : Car) {

        return runBlocking {
            return@runBlocking dao.insert(car)
        }
    }

    fun deleteCar(car : Car) {

        return runBlocking {
            return@runBlocking dao.delete(car)
        }
    }

}