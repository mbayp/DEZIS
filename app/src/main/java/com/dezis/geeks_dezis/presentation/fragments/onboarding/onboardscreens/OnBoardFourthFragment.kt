package com.dezis.geeks_dezis.presentation.fragments.onboarding.onboardscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.dezis.geeks_dezis.R
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardFourthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_on_board_fourth, container, false)

        view.findViewById<MaterialButton>(R.id.btn_continue).setOnClickListener {
            view.findNavController().navigate(R.id.onBoardFifthFragment)
        }
        view.findViewById<MaterialButton>(R.id.btn_skip).setOnClickListener {
            view.findNavController().navigate(R.id.onBoardFifthFragment) // Пропускает к последнему фрагменту
        }

        return view
    }
}
