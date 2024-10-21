package com.dezis.geeks_dezis.presentation.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.databinding.ItemServiceBinding

class ServiceAdapter(private val navController: NavController) : ListAdapter<ServiceModel, ServiceAdapter.ServiceViewHolder>(DiffCallback()) {

    inner class ServiceViewHolder(private val binding: ItemServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ServiceModel) = with(binding) {
            tvServiceInfo.text = model.info
            tvService.text = model.service
            btnService.text = model.btn

            btnService.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_serviceScreenFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding =
            ItemServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<ServiceModel>() {
        override fun areItemsTheSame(oldItem: ServiceModel, newItem: ServiceModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ServiceModel, newItem: ServiceModel): Boolean {
            return oldItem == newItem
        }
    }

}