package com.dezis.geeks_dezis.presentation.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.databinding.ItemInformationBinding
import com.dezis.geeks_dezis.databinding.ItemServiceBinding

class InformationAdapter : ListAdapter<InformationModel, InformationAdapter.ServiceViewHolder>(DiffCallback()) {

    inner class ServiceViewHolder(private val binding: ItemInformationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: InformationModel) = with(binding) {
            tvNumber.text = model.id.toString()
            tvDescription.text = model.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding =
            ItemInformationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<InformationModel>() {
        override fun areItemsTheSame(oldItem: InformationModel, newItem: InformationModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: InformationModel, newItem: InformationModel): Boolean {
            return oldItem == newItem
        }
    }
}