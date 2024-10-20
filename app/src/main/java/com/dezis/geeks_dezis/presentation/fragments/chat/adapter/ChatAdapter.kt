package com.dezis.geeks_dezis.presentation.fragments.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.data.remote.apiservice.ChatModel
import com.dezis.geeks_dezis.databinding.ItemSendMassageBinding

class ChatAdapter : ListAdapter<ChatModel, ChatAdapter.MessageViewHolder>(MessageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_send_massage, parent, false)
        return MessageViewHolder(ItemSendMassageBinding.bind(view))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = getItem(position)
        holder.bind(message)
    }

    class MessageViewHolder(
        private val binding: ItemSendMassageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatModel) {
            binding.tvMessage.text = message.message
            binding.messageTime.text = message.fromUserId
        }
    }

    class MessageDiffCallback : DiffUtil.ItemCallback<ChatModel>() {
        override fun areItemsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
            return oldItem.message == newItem.message && oldItem.fromUserId == newItem.fromUserId
        }
    }
}