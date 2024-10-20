package com.dezis.geeks_dezis.presentation.fragments.profile.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.databinding.ItemServiceCardBinding

class OrderHistoryAdapter : ListAdapter<Treatment, OrderHistoryAdapter.OrderHistoryViewHolder>(OrderHistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHistoryViewHolder {
        val binding = ItemServiceCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderHistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class OrderHistoryViewHolder(private val binding: ItemServiceCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(treatment: Treatment) {
            binding.tvService.text = treatment.service
            binding.tvAddress.text = treatment.address
            binding.tvDate.text = treatment.date
            binding.tvTime.text = treatment.time
        }
    }
}

class OrderHistoryDiffCallback : DiffUtil.ItemCallback<Treatment>() {
    override fun areItemsTheSame(oldItem: Treatment, newItem: Treatment): Boolean {
        return oldItem.date == newItem.date && oldItem.time == newItem.time
    }

    override fun areContentsTheSame(oldItem: Treatment, newItem: Treatment): Boolean {
        return oldItem == newItem
    }
}