package com.srappetito.flybonditestmvvm.database

import com.srappetito.flybonditestmvvm.database.tables.Flights
import kotlinx.coroutines.flow.Flow

interface DatabaseHelper {

    suspend fun insertAllFlights(lstFlights: List<Flights>)

    suspend fun getAllFlights() : List<Flights>

    suspend fun deleteAllFlights()

    fun getAllFlightsFlow(): Flow<List<Flights>>

}