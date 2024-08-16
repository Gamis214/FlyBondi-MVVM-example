package com.srappetito.flybonditestmvvm.database.repository

import com.srappetito.flybonditestmvvm.database.DatabaseHelper
import com.srappetito.flybonditestmvvm.database.dao.FlightsDao
import com.srappetito.flybonditestmvvm.database.tables.Flights
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class RepositoryRoom @Inject constructor(private val flightsDao: FlightsDao): DatabaseHelper {

    override suspend fun insertAllFlights(lstFlights: List<Flights>) = flightsDao.insertAllFlights(lstFlights)

    override suspend fun getAllFlights() : List<Flights> = flightsDao.getAllFlights()

    override suspend fun deleteAllFlights() = flightsDao.deleteAllFlights()

    override fun getAllFlightsFlow(): Flow<List<Flights>> = flightsDao.getAllFlightsFlow()

}