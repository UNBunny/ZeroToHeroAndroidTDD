package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootLayout)

        binding.actionButton.setOnClickListener {
            binding.titleTextView.text = binding.inputEditText.text.toString()
            binding.inputEditText.text?.clear()
            binding.actionButton.isEnabled = false
        }
        binding.inputEditText.addTextChangedListener {
            binding.actionButton.isEnabled = binding.inputEditText.text.toString().length >= 3
        }

    }
}