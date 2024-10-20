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
import com.dezis.geeks_dezis.databinding.FragmentSignInBinding
import com.dezis.geeks_dezis.presentation.fragments.authorization.sign_in.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment: BaseFragment<FragmentChatBinding, ChatViewModel>(R.layout.fragment_chat) {

    override val binding: FragmentChatBinding by viewBinding(FragmentChatBinding::bind)
    override val viewModel: ChatViewModel by viewModels()


    override fun init() {
        super.init()
        initClick()
    }

    private fun initClick() {
        binding.imgBackArrow.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }
}