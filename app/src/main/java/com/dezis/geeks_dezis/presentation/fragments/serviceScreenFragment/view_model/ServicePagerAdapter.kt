package com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment.view_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.R

class ServicePagerAdapter(private val services: List<ServiceModel>) :
    RecyclerView.Adapter<ServicePagerAdapter.ServiceViewHolder>() {

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
        val button: Button = itemView.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_service_card, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = services[position]
        holder.image.setImageResource(service.imageResId)
        holder.title.text = service.title
        holder.description.text = service.description
        holder.button.setOnClickListener {
            // Действие на нажатие кнопки
        }
    }

    override fun getItemCount(): Int = services.size
}