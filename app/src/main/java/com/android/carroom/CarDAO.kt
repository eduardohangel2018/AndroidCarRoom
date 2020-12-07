package com.android.carroom

import androidx.room.*
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Cria o DAO para a Entidade
@Dao
interface CarDAO {

    @Query("SELECT * FROM cars WHERE id = :id")
    suspend fun get(id : Int) : Car

    @Query("SELECT * FROM cars")
    suspend fun list():List<Car>

    @Query("SELECT * FROM cars")
    fun listLiveData() : Flow<List<Car>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(car: Car)

    @Update
    suspend fun update(car: Car)

    @Delete
    suspend fun delete(car: Car)
}