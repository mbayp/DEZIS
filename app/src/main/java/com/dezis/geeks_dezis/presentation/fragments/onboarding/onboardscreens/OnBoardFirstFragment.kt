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
class OnBoardFirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_on_board_first, container, false)

        view.findViewById<MaterialButton>(R.id.btn_continue).setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_onBoardFirstFragment_to_onBoardSecondFragment)
        }
        view.findViewById<MaterialButton>(R.id.btn_skip).setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_onBoardFirstFragment_to_onBoardFifthFragment)
        }

        return view
    }

}