package com.dezis.geeks_dezis.presentation.fragments.chat

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentChatBinding
import com.dezis.geeks_dezis.presentation.fragments.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>(R.layout.fragment_chat) {

    override val binding: FragmentChatBinding by viewBinding(FragmentChatBinding::bind)

    override val viewModel: ChatViewModel by viewModels()

    private lateinit var chatAdapter: ChatAdapter

    override fun init() {
        super.init()
        initClick()
        setupRecyclerView()
    }

    private fun initClick() {
        binding.imgBackArrow.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter()
        binding.rvChat.adapter = chatAdapter
    }

}