package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository
) : ViewModel() {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun liveData() = liveDataWrapper.liveData()
    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch {
            val result = repository.load()
            liveDataWrapper.update(UiState.ShowData(result.text))
        }
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        val state = bundleWrapper.restore()
        liveDataWrapper.update(state)
    }
}