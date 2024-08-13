package com.srappetito.flybonditestmvvm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.srappetito.flybonditestmvvm.database.dao.FlightsDao
import com.srappetito.flybonditestmvvm.database.tables.Flights

@Database(
    entities = [Flights :: class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun flightsDao() : FlightsDao

}