package com.bud.app.com.bud.app.core.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.bud.app.core.data.Result

fun <T : Any, U> LiveData<Result<T>>.mapSuccess(func: (T) -> U): LiveData<U> {
    val mediatorLiveData: MediatorLiveData<U> = MediatorLiveData()
    mediatorLiveData.addSource(this) {
        when (it) {
            is Result.Success -> {
                mediatorLiveData.postValue(func(it.data))
            }
        }
    }
    return mediatorLiveData
}