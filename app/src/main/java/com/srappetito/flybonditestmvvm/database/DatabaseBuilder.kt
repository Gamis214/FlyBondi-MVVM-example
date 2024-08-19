package com.srappetito.flybonditestmvvm.database

import android.content.Context
import androidx.room.Room
import com.srappetito.flybonditestmvvm.database.dao.FlightsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseBuilder {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "flights.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFlightDao(appDataBase: AppDataBase): FlightsDao {
        return appDataBase.flightsDao()
    }
}