package com.dezis.geeks_dezis.presentation.fragments.onboarding.onboardscreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.dezis.geeks_dezis.R
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardFifthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_on_board_fifth, container, false)

        view.findViewById<MaterialButton>(R.id.btn_continue).setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_onBoardFifthFragment_to_adminOrUserFragment)
        }
        return view
    }

}