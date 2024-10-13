package com.dezis.geeks_dezis.admin.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.admin.data.Request
import com.dezis.geeks_dezis.databinding.ItemRequestBinding

class RequestAdapter(private val requests: List<Request>) : RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val binding = ItemRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val request = requests[position]
        holder.bind(request)
    }

    override fun getItemCount(): Int = requests.size

    inner class RequestViewHolder(private val binding: ItemRequestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(request: Request) {
            binding.textDate.text = "Дата: ${request.date}"
            binding.textName.text = "ФИО: ${request.fullName}"
            binding.textEmail.text = "Почта: ${request.email}"
            binding.textPhone.text = "Телефон: ${request.phone}"
            binding.textAddress.text = "Адрес: ${request.address}"
            binding.textApartmentNumber.text = "Номер квартиры/дома: ${request.apartmentNumber}"

            binding.buttonConfirm.setOnClickListener {
                // Обработка подтверждения
            }

            binding.buttonCancel.setOnClickListener {
                // Обработка отказа
            }
        }
    }
}
