package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper.Mutable,
    private val repository: Repository
) : LiveDataWrapper.Observe {
    override fun liveData(): LiveData<UiState> = liveDataWrapper.liveData()
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun save(bundleWrapper : BundleWrapper.Save){
        liveDataWrapper.save(bundleWrapper)
    }
    fun restore(bundleWrapper : BundleWrapper.Restore){
        val state = bundleWrapper.restore()
        liveDataWrapper.update(state)
    }
    fun load(){
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch {
            val result = repository.load()
            result.show(liveDataWrapper)
        }
    }
}