package com.example.postslite.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.postslite.databinding.ItemPostBinding
import com.example.postslite.domain.model.Post

class PostsAdapter(
    private val onClick: (Post) -> Unit,
    private val onSaveClick: (Post) -> Unit
) : ListAdapter<Post, PostsAdapter.VH>(Diff) {

    object Diff : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
    }

    class VH(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(b)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val post = getItem(position)
        holder.binding.title.text = post.title
        holder.binding.body.text = post.body
        holder.binding.btnSave.text = if (post.isSaved) "Unsave" else "Save"

        holder.binding.root.setOnClickListener { onClick(post) }
        holder.binding.btnSave.setOnClickListener { onSaveClick(post) }
    }
}
