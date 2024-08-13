package com.srappetito.flybonditestmvvm.database

import com.srappetito.flybonditestmvvm.database.AppDataBase
import com.srappetito.flybonditestmvvm.database.DatabaseHelper
import com.srappetito.flybonditestmvvm.database.tables.Flights

class DatabaseHelperImpl(private val appDataBase: AppDataBase) : DatabaseHelper {

    override suspend fun insertAllFlights(lstFlights: List<Flights>) = appDataBase.flightsDao().insertAllFlights(lstFlights)

    override suspend fun getAllFlights() : List<Flights> = appDataBase.flightsDao().getAllFlights()

}