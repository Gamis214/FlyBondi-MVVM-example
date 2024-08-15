package com.srappetito.flybonditestmvvm.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?){
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS,data,null)
        }

        fun <T> error(msg: String?): Resource<T> {
            return Resource(Status.ERROR,null,msg)
        }

        /*fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING,null,null)
        }*/
    }
}

data class ResourceLoading<out T>(val status: StatusLoading){
    companion object {
        fun <T> loading(): ResourceLoading<T> {
            return ResourceLoading(StatusLoading.LOADING)
        }
        fun <T> dismissLoading(): ResourceLoading<T> {
            return ResourceLoading(StatusLoading.DISSMISSLOADING)
        }
    }
}
