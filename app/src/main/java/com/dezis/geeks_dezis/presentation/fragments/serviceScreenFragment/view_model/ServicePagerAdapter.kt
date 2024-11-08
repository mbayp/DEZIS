package com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment.view_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.data.remote.model.service.ServiceModel

class ServicePagerAdapter(
    private val services: List<ServiceModel>,
    private val navController: NavController
) : RecyclerView.Adapter<ServicePagerAdapter.ServiceViewHolder>() {

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.image)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.description)
        private val button: Button = itemView.findViewById(R.id.button)

        fun bind(service: ServiceModel, navController: NavController) {
            image.setImageResource(service.imageResId)
            title.text = service.title
            description.text = service.description
            button.setOnClickListener {
                navController.navigate(R.id.action_serviceScreenFragment_to_calendarFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_service_screen, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(services[position], navController)
    }

    override fun getItemCount(): Int = services.size
}