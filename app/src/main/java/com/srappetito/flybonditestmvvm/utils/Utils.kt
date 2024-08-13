package com.srappetito.flybonditestmvvm.utils

import android.app.Activity
import android.util.Log
import com.srappetito.flybonditestmvvm.database.tables.Flights
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler

object Utils {

    fun getFlightsToSaveDB(activity: Activity) : List<Flights>{
        val listFlightsEntity: MutableList<Flights> = emptyList<Flights>().toMutableList()
        val jsonString = getFlightsFromAssets(activity).replace("\r\n","")
        val listFlightsAssets = convertFligthsAssetsToObject(jsonString)
        listFlightsAssets!!.listFlights.forEach { flight ->
            val flightEntity = Flights(
                null,
                flight.data,
                flight.origin,
                flight.destination,
                flight.price,
                flight.availability
            )
            listFlightsEntity.add(flightEntity)
        }
        return listFlightsEntity
    }

    fun getFlightsFromAssets(activity: Activity) : String{
        val archive = activity.assets.open("flights.json")
        val size = archive.available()
        val buffer = ByteArray(size)
        archive.read(buffer)
        archive.close()
        return String(buffer,Charsets.UTF_8)
    }

    fun convertFligthsAssetsToObject(jsonString : String) : com.srappetito.flybonditestmvvm.models.Flights? {
        return Gson().fromJson(jsonString, com.srappetito.flybonditestmvvm.models.Flights::class.java)
    }
}

object CoroutineHelper {
    val handler = CoroutineExceptionHandler {_ , exception ->
        Log.e("Coroutine Handler Error -->","${exception.message}")
    }
}