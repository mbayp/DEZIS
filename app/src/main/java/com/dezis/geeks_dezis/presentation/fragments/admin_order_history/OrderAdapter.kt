package com.dezis.geeks_dezis.presentation.fragments.admin_order_history

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.data.remote.model.Booking
import javax.inject.Inject

class OrderAdapter @Inject constructor() : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private var ordersList = listOf<Booking>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = ordersList[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int = ordersList.size

    fun setOrders(orders: List<Booking>) {
        ordersList = orders
        notifyDataSetChanged()
    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val clientName: TextView = itemView.findViewById(R.id.clientName)
        private val serviceType: TextView = itemView.findViewById(R.id.serviceType)
        private val address: TextView = itemView.findViewById(R.id.address)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val completeOrderButton: Button = itemView.findViewById(R.id.completeOrderButton)

        fun bind(order: Booking) {
            clientName.text = "Client: ${order.user}"
            serviceType.text = order.service
            address.text = "Address: ${order.id}"
            date.text = "${order.date}, ${order.time}"

            // Показать кнопку только для незавершенных заказов
            completeOrderButton.visibility = if (order.time.isNotEmpty()) View.VISIBLE else View.GONE

            completeOrderButton.setOnClickListener {
                showConfirmationDialog(order)
            }
        }

        private fun showConfirmationDialog(order: Booking) {
            val dialogView = LayoutInflater.from(itemView.context).inflate(R.layout.custom_dialog_layout, null)
            val dialogBuilder = AlertDialog.Builder(itemView.context, R.style.CustomAlertDialog)
            val dialog = dialogBuilder.setView(dialogView).create()

            val yesButton = dialogView.findViewById<TextView>(R.id.dialog_yes_button)
            val noButton = dialogView.findViewById<TextView>(R.id.dialog_no_button)

            yesButton.setOnClickListener {
                dialog.dismiss()
                showOrderCompletedDialog() // Открываем второй диалог после нажатия "Да"
            }

            noButton.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }

        private fun showOrderCompletedDialog() {
            val completedDialogView = LayoutInflater.from(itemView.context).inflate(R.layout.order_completed_dialog, null)
            val completedDialogBuilder = AlertDialog.Builder(itemView.context, R.style.CustomAlertDialog)
            val completedDialog = completedDialogBuilder.setView(completedDialogView).create()

            val closeButton = completedDialogView.findViewById<ImageView>(R.id.closeButton)
            closeButton.setOnClickListener {
                completedDialog.dismiss()
                // Скрываем кнопку "Завершить" после закрытия второго диалога
                completeOrderButton.visibility = View.GONE
            }

            completedDialog.show()
        }
    }
}