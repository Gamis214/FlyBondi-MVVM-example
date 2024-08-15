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
import com.srappetito.flybonditestmvvm.utils.Resource
import com.srappetito.flybonditestmvvm.utils.ResourceLoading
import com.srappetito.flybonditestmvvm.utils.StatusLoading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
class HomeFlightsViewModel(application: Application) : AndroidViewModel(application) {

    private val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(application.applicationContext))
    val statusLoading = MutableLiveData<ResourceLoading<StatusLoading>>()

    fun saveAllFlightsInDB(listFlightsEntity : List<Flights>) : MutableLiveData<Resource<Boolean>>{
        val status = MutableLiveData<Resource<Boolean>>()
        viewModelScope.launch {
            statusLoading.postValue(ResourceLoading.loading())
            val lstFlights = dbHelper.getAllFlights()
            if(lstFlights.isEmpty()){
                dbHelper.insertAllFlights(listFlightsEntity)
                statusLoading.postValue(ResourceLoading.dismissLoading())
                status.postValue(Resource.success(true))
            } else {
                statusLoading.postValue(ResourceLoading.dismissLoading())
                status.postValue(Resource.success(false))
            }
        }
        return status
    }

    fun getAllFlightsInDB(): MutableLiveData<Resource<List<Flights>>>{
        val status = MutableLiveData<Resource<List<Flights>>>()
        viewModelScope.launch {
            statusLoading.postValue(ResourceLoading.loading())
            val lstFlights = dbHelper.getAllFlights()
            if(lstFlights.isNotEmpty()){
                statusLoading.postValue(ResourceLoading.dismissLoading())
                status.postValue(Resource.success(lstFlights))
            } else {
                statusLoading.postValue(ResourceLoading.dismissLoading())
                status.postValue(Resource.error("Empty List"))
            }
        }
        return status
    }

    fun getFlightsFromServices() : MutableLiveData<Resource<FlyResponse?>>{
        val status = MutableLiveData<Resource<FlyResponse?>>()
        viewModelScope.launch {
            kotlin.runCatching {
                statusLoading.postValue(ResourceLoading.loading())
                withContext(Dispatchers.IO){
                    HomeFlightsRepository().getAllFlights()
                }
            }.onSuccess {
                statusLoading.postValue(ResourceLoading.dismissLoading())
                status.postValue(Resource.success(it))
            }.onFailure {
                statusLoading.postValue(ResourceLoading.dismissLoading())
                status.postValue(Resource.error(it.message))
            }
        }
        return status
    }
}