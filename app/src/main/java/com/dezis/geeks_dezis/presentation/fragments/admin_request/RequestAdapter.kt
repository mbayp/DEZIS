package com.dezis.geeks_dezis.presentation.fragments.admin_request

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.data.remote.model.inactiveUser.InactiveUserModelItem
import com.dezis.geeks_dezis.databinding.ItemRequestBinding

class RequestAdapter(
    private val listener: OnItemActionListener
) : ListAdapter<InactiveUserModelItem, RequestAdapter.RequestViewHolder>(DiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val binding = ItemRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RequestViewHolder(private val binding: ItemRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: InactiveUserModelItem) = with(binding) {
            textDate.text = model.createdAt
            textName.text = model.username
            textPhone.text = model.number
            textAddress.text = model.address
            textApartmentNumber.text = model.address
            textEmail.text = model.email

            // Устанавливаем обработчики кликов для кнопок подтверждения и удаления
            buttonConfirm.setOnClickListener {
                listener.onConfirmClicked(model.id)
            }

            buttonCancel.setOnClickListener {
                listener.onDeleteClicked(model.id)
            }
        }
    }

    interface OnItemActionListener {
        fun onConfirmClicked(userId: Int)
        fun onDeleteClicked(userId: Int)
    }

    class DiffCallback : DiffUtil.ItemCallback<InactiveUserModelItem>() {
        override fun areItemsTheSame(
            oldItem: InactiveUserModelItem,
            newItem: InactiveUserModelItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: InactiveUserModelItem,
            newItem: InactiveUserModelItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
