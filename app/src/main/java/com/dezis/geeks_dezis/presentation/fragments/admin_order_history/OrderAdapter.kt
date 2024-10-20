package com.dezis.geeks_dezis.presentation.fragments.admin_order_history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.R
import javax.inject.Inject

class OrderAdapter @Inject constructor() : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private var ordersList = listOf<Order>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = ordersList[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int = ordersList.size

    fun setOrders(orders: List<Order>) {
        ordersList = orders
        notifyDataSetChanged()
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val clientName: TextView = itemView.findViewById(R.id.clientName)
        private val serviceType: TextView = itemView.findViewById(R.id.serviceType)
        private val address: TextView = itemView.findViewById(R.id.address)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val completeOrderButton: Button = itemView.findViewById(R.id.completeOrderButton)

        fun bind(order: Order) {
            clientName.text = order.clientName
            serviceType.text = order.service
            address.text = order.address
            date.text = order.date
            completeOrderButton.visibility = if (order.isCompleted) View.GONE else View.VISIBLE
        }
    }
}