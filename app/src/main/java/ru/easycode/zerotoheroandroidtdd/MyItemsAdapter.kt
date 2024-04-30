package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.easycode.zerotoheroandroidtdd.databinding.ItemLayoutBinding

class MyItemsAdapter : RecyclerView.Adapter<MyItemViewHolder>() {
    private val itemsList = ArrayList<String>()
    fun add(source: String) {
        itemsList.add(source)
        notifyItemInserted(itemsList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemViewHolder {
        return MyItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: MyItemViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    fun save(bundle: Bundle) {
        bundle.putStringArrayList(KEY, itemsList)
    }

    fun restore(bundle: Bundle) {
        itemsList.addAll(bundle.getStringArrayList(KEY) ?: ArrayList())
    }

    companion object {
        private const val KEY = "myItemsAdapterKey"
    }
}

class MyItemViewHolder(private val binding: ItemLayoutBinding) : ViewHolder(binding.root) {
    fun bind(source: String) {
        binding.elementTextView.text = source
    }
}
