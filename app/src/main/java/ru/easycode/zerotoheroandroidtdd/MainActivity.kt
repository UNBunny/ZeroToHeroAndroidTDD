package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.actionButton)
        textView = findViewById(R.id.titleTextView)
        progressBar = findViewById(R.id.progressBar)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(LiveDataWrapper.Base(), Repository.Base())
        ).get(MainViewModel::class.java)
        button.setOnClickListener {
            viewModel.load()
        }

        viewModel.liveData().observe(this) { uiState ->
            uiState.apply(button, progressBar, textView)
        }
    }
}