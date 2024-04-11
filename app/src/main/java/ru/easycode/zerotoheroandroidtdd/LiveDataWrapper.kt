package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {
    fun liveData(): LiveData<UiState>
    fun update(value: UiState)
    class Base(private val liveData: MutableLiveData<UiState> = MutableLiveData()) :
        LiveDataWrapper {
        override fun liveData(): LiveData<UiState> = liveData

        override fun update(value: UiState) {
            liveData.value = value
        }

    }
}