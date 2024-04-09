package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var uiState: UiState
    private val count = Count.Base(2, 4, 0)
    private lateinit var textView: TextView
    private lateinit var buttonIncrement: Button
    private lateinit var buttonDecrement: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.countTextView)
        buttonIncrement = findViewById(R.id.incrementButton)
        buttonDecrement = findViewById(R.id.decrementButton)
        buttonIncrement.setOnClickListener {
            uiState = count.increment(textView.text.toString())
            uiState.apply(textView, buttonIncrement, buttonDecrement)
        }
        buttonDecrement.setOnClickListener {
            uiState = count.decrement(textView.text.toString())
            uiState.apply(textView, buttonIncrement, buttonDecrement)
        }
        if (savedInstanceState == null){
            uiState = count.initial(textView.text.toString())
            uiState.apply(textView, buttonIncrement, buttonDecrement)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
        } else {
            savedInstanceState.getSerializable(KEY) as UiState
        }
        uiState.apply(textView, buttonIncrement, buttonDecrement)
    }

    companion object {
        private const val KEY = "uiStateKey"
    }
}