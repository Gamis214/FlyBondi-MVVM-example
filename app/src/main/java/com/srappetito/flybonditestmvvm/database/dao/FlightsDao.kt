package com.srappetito.flybonditestmvvm.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.srappetito.flybonditestmvvm.database.tables.Flights
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightsDao {

    @Insert
    suspend fun insertAllFlights(lstFlights: List<Flights>)

    @Query("SELECT * FROM Flights")
    suspend fun getAllFlights(): List<Flights>

    @Query("DELETE FROM Flights")
    suspend fun deleteAllFlights()

    @Query("SELECT * FROM Flights")
    fun getAllFlightsFlow(): Flow<List<Flights>>

}