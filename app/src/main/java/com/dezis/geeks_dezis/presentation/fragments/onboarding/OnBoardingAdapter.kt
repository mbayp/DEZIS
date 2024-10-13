package com.dezis.geeks_dezis.presentation.fragments.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingAdapter(list: ArrayList<Fragment>, fm:FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fm,lifecycle) {

    private val fragmentList = list
    override fun getItemCount(): Int {
        return fragmentList.size
<<<<<<< HEAD
=======

>>>>>>> log_in_screen_remasted-changes
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
<<<<<<< HEAD

=======
>>>>>>> log_in_screen_remasted-changes
}