package com.dezis.geeks_dezis.presentation.fragments.onboarding.onboardscreens

import android.os.Bundle
<<<<<<< HEAD
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
=======
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
>>>>>>> log_in_screen_remasted-changes
import com.dezis.geeks_dezis.R

class OnBoardSecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
<<<<<<< HEAD
        savedInstanceState: Bundle?,
    ): View {
=======
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
>>>>>>> log_in_screen_remasted-changes
        return inflater.inflate(R.layout.fragment_on_board_second, container, false)
    }

}