package com.dezis.geeks_dezis.presentation.fragments.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.data.remote.model.chats.ChatModel
import com.dezis.geeks_dezis.databinding.ItemSendMassageBinding

class ChatAdapter : ListAdapter<ChatModel, ChatAdapter.MessageViewHolder>(MessageDiffCallback()) {

    init {
        submitList(getDummyChatData())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_send_massage, parent, false)
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
            binding.messageTime.text = message.timestamp
        }
    }

    class MessageDiffCallback : DiffUtil.ItemCallback<ChatModel>() {
        override fun areItemsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
            return oldItem == newItem
        }
    }

    private fun getDummyChatData(): List<ChatModel> {
        return listOf(
            ChatModel(id = 1, fromUserId = "User1", message = "Hello!", timestamp = "10:15 AM"),
            ChatModel(id = 2, fromUserId = "User2", message = "Hi there!", timestamp = "10:16 AM"),
            ChatModel(
                id = 3,
                fromUserId = "User1",
                message = "How are you?",
                timestamp = "10:17 AM"
            ),
            ChatModel(
                id = 4,
                fromUserId = "User2",
                message = "I'm good, thanks!",
                timestamp = "10:18 AM"
            ),
            ChatModel(
                id = 5,
                fromUserId = "User1",
                message = "Great to hear!",
                timestamp = "10:19 AM"
            )
        )
    }

}