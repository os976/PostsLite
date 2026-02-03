package com.example.postslite.presentation.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.postslite.databinding.ItemSavedBinding
import com.example.postslite.domain.model.Post

class SavedAdapter(
    private val onSelectionChanged: (Int) -> Unit,
    private val onPostClick: (Post) -> Unit
) : ListAdapter<Post, SavedAdapter.VH>(DiffCallback) {

    private val selectedIds = linkedSetOf<Int>()

    class VH(val binding: ItemSavedBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemSavedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val post = getItem(position)

        holder.binding.title.text = post.title
        holder.binding.body.text = post.body

        holder.binding.check.setOnCheckedChangeListener(null)
        holder.binding.check.isChecked = selectedIds.contains(post.id)

        holder.binding.check.setOnCheckedChangeListener { _, checked ->
            if (checked) selectedIds.add(post.id)
            else selectedIds.remove(post.id)

            onSelectionChanged(selectedIds.size)
        }

        holder.binding.root.setOnClickListener {
            onPostClick(post)
        }
    }

    fun getSelectedIds(): List<Int> = selectedIds.toList()

    fun clearSelection() {
        selectedIds.clear()
        notifyDataSetChanged()
        onSelectionChanged(0)
    }

    fun selectAll() {
        selectedIds.clear()
        selectedIds.addAll(currentList.map { it.id })
        notifyDataSetChanged()
        onSelectionChanged(selectedIds.size)
    }
}
