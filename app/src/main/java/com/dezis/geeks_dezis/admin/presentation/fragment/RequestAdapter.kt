package com.dezis.geeks_dezis.admin.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.admin.data.Booking
import com.dezis.geeks_dezis.databinding.ItemRequestBinding

class RequestAdapter(private val bookings: List<Booking>) : RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val binding = ItemRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val booking = bookings[position]
        holder.bind(booking)
    }

    override fun getItemCount(): Int = bookings.size

    inner class RequestViewHolder(private val binding: ItemRequestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(booking: Booking) {
            binding.textDate.text = "Дата: ${booking.date}"
            binding.textName.text = "Услуга: ${booking.service}"
            binding.textTime.text = "Время: ${booking.time}"

            binding.buttonConfirm.setOnClickListener {
                // Обработка подтверждения
            }

            binding.buttonCancel.setOnClickListener {
                // Обработка отказа
            }
        }
    }
}
