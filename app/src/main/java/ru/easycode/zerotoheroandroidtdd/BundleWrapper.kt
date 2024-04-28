package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle

interface BundleWrapper {
    interface Mutable : Save, Restore
    interface Save {
        fun save(uiState: UiState)
    }

    interface Restore {
        fun restore(): UiState
    }

    class Base(private val bundle: Bundle) : Mutable {
        override fun save(uiState: UiState) {
            bundle.putSerializable(KEY, uiState)
        }

        override fun restore(): UiState {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(KEY, UiState::class.java) as UiState
            } else {
                bundle.getSerializable(KEY) as UiState
            }
        }

        companion object {
            private val KEY = "TDD"
        }

    }

}