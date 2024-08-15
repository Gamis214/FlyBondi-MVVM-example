package com.srappetito.flybonditestmvvm.database

import com.srappetito.flybonditestmvvm.database.tables.Flights
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DatabaseHelperImpl(private val appDataBase: AppDataBase) : DatabaseHelper {

    override suspend fun insertAllFlights(lstFlights: List<Flights>) = appDataBase.flightsDao().insertAllFlights(lstFlights)

    override suspend fun getAllFlights() : List<Flights> = appDataBase.flightsDao().getAllFlights()

    override suspend fun deleteAllFlights() = appDataBase.flightsDao().deleteAllFlights()

    override fun getAllFlightsFlow(): Flow<List<Flights>> = appDataBase.flightsDao().getAllFlightsFlow()

}