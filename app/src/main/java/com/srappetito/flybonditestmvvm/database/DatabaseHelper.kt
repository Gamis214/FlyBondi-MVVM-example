package com.srappetito.flybonditestmvvm.database

import com.srappetito.flybonditestmvvm.database.tables.Flights

interface DatabaseHelper {

    suspend fun insertAllFlights(lstFlights: List<Flights>)

    suspend fun getAllFlights() : List<Flights>

}