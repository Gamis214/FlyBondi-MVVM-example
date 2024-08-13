package com.srappetito.flybonditestmvvm.viewModels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.srappetito.flybonditestmvvm.database.DatabaseBuilder
import com.srappetito.flybonditestmvvm.database.DatabaseHelperImpl
import com.srappetito.flybonditestmvvm.database.tables.Flights
import com.srappetito.flybonditestmvvm.models.FlyResponse
import com.srappetito.flybonditestmvvm.repository.HomeFlightsRepository
import com.srappetito.flybonditestmvvm.utils.CoroutineHelper
import com.srappetito.flybonditestmvvm.utils.Resource
import com.srappetito.flybonditestmvvm.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
class HomeFlightsViewModel(application: Application) : AndroidViewModel(application) {

    private val status = MutableLiveData<Resource<Boolean>>()
    private val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(application.applicationContext))

    fun saveAllFlightsInDB(listFlightsEntity : List<Flights>) : MutableLiveData<Resource<Boolean>>{
        viewModelScope.launch {
            status.postValue(Resource.loading())
            val lstFlights = dbHelper.getAllFlights()
            if(lstFlights.isEmpty()){
                dbHelper.insertAllFlights(listFlightsEntity)
                status.postValue(Resource.success(true))
            } else {
                status.postValue(Resource.success(false))
            }
        }
        return status
    }

    fun getFlightsFromServices() : MutableLiveData<FlyResponse?>{
        val flyResponse = MutableLiveData<FlyResponse?>()
        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO){
                    HomeFlightsRepository().getAllFlights()
                }
            }.onSuccess {
                flyResponse.postValue(it)
            }.onFailure {
                it.printStackTrace()
                flyResponse.postValue(null)
            }
        }
        return flyResponse
    }
}