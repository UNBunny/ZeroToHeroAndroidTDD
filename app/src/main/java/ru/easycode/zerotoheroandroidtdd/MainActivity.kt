package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.children
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.actionButton.setOnClickListener {
            addTextView(binding.inputEditText.text.toString())
            binding.inputEditText.text?.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val textList =
            binding.contentLayout.children.map { (it as TextView).text.toString() }.toList()
        outState.putStringArrayList("list", ArrayList(textList))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val list = savedInstanceState.getStringArrayList("list")
        list?.forEach { i ->
            addTextView(i.toString())
        }
    }

    fun addTextView(inputText: String) {
        val textView = TextView(this).apply {
            text = inputText
        }
        binding.contentLayout.addView(textView)
    }
}