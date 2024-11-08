package com.dezis.geeks_dezis.presentation.fragments.admin_order_history

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.data.remote.model.booking.Booking
import javax.inject.Inject

class OrderAdapter @Inject constructor() : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private var ordersList = listOf<Booking>()
    private var isCompletedTab = false

    fun setTab(isCompletedTab: Boolean) {
        this.isCompletedTab = isCompletedTab
        notifyDataSetChanged()
    }

    fun setOrders(orders: List<Booking>) {
        val diffCallback = OrdersDiffCallback(this.ordersList, orders)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.ordersList = orders
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(ordersList[position], isCompletedTab)
    }

    override fun getItemCount(): Int = ordersList.size

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val clientName: TextView = itemView.findViewById(R.id.clientName)
        private val serviceType: TextView = itemView.findViewById(R.id.serviceType)
        private val address: TextView = itemView.findViewById(R.id.address)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val completeOrderButton: Button = itemView.findViewById(R.id.completeOrderButton)

        fun bind(order: Booking, isCompletedTab: Boolean) {
            clientName.text = "Client: ${order.user}"
            serviceType.text = order.service
            address.text = "Address: ${order.id}"
            date.text = "${order.date}, ${order.time}"

            completeOrderButton.visibility = if (isCompletedTab) View.GONE else View.VISIBLE

            completeOrderButton.setOnClickListener {
                showConfirmationDialog(order)
            }
        }

        private fun showConfirmationDialog(order: Booking) {
            val dialogView = LayoutInflater.from(itemView.context)
                .inflate(R.layout.custom_dialog_layout, null)
            val dialog = AlertDialog.Builder(itemView.context, R.style.CustomAlertDialog)
                .setView(dialogView)
                .create()

            dialogView.findViewById<TextView>(R.id.dialog_yes_button).setOnClickListener {
                dialog.dismiss()
                showOrderCompletedDialog()
            }

            dialogView.findViewById<TextView>(R.id.dialog_no_button).setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }

        private fun showOrderCompletedDialog() {
            val completedDialogView = LayoutInflater.from(itemView.context)
                .inflate(R.layout.order_completed_dialog, null)
            val completedDialog = AlertDialog.Builder(itemView.context, R.style.CustomAlertDialog)
                .setView(completedDialogView)
                .create()

            completedDialogView.findViewById<ImageView>(R.id.closeButton).setOnClickListener {
                completedDialog.dismiss()
                completeOrderButton.visibility = View.GONE
            }

            completedDialog.show()
        }
    }

    class OrdersDiffCallback(
        private val oldList: List<Booking>,
        private val newList: List<Booking>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}