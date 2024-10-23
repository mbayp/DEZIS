package com.dezis.geeks_dezis.presentation.fragments.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentChatBinding
import com.dezis.geeks_dezis.presentation.fragments.authorization.sign_in.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.dezis.geeks_dezis.presentation.fragments.chat.adapter.ChatAdapter

@AndroidEntryPoint
class ChatFragment: BaseFragment<FragmentChatBinding, ChatViewModel>(R.layout.fragment_chat) {

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
//Дарика это я добавил если надо удалишь
    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter()
        binding.rvChat.adapter = chatAdapter
    }
}
