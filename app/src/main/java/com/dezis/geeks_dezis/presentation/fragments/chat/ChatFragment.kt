package com.dezis.geeks_dezis.presentation.fragments.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentChatBinding

class ChatFragment(
    override var binding: FragmentChatBinding,
    override val viewModel: ChatViewModel
) : BaseFragment<FragmentChatBinding, ChatViewModel>(R.layout.fragment_chat) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }
}