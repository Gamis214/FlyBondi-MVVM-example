package com.srappetito.flybonditestmvvm.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.srappetito.flybonditestmvvm.database.tables.Flights

@Dao
interface FlightsDao {

    @Insert
    suspend fun insertAllFlights(lstFlights: List<Flights>)

    @Query("SELECT * FROM Flights")
    suspend fun getAllFlights(): List<Flights>

}