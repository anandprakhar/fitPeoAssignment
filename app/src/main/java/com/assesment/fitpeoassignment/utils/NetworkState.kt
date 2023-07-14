package com.assesment.fitpeoassignment.utils

class NetworkState(val status: NetworkStatus, val msg: String) {
    companion object {
        val LOADED: NetworkState = NetworkState(NetworkStatus.SUCCESS,"Success")
        val LOADING: NetworkState = NetworkState(NetworkStatus.RUNNING,"Running")
        val ERROR: NetworkState = NetworkState(NetworkStatus.FAILED,"Failed")
    }
}