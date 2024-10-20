package com.dezis.geeks_dezis.presentation.fragments.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.databinding.FragmentOnBoardingBinding
import com.dezis.geeks_dezis.presentation.fragments.onboarding.onboardscreens.OnBoardFifthFragment
import com.dezis.geeks_dezis.presentation.fragments.onboarding.onboardscreens.OnBoardFirstFragment
import com.dezis.geeks_dezis.presentation.fragments.onboarding.onboardscreens.OnBoardFourthFragment
import com.dezis.geeks_dezis.presentation.fragments.onboarding.onboardscreens.OnBoardSecondFragment
import com.dezis.geeks_dezis.presentation.fragments.onboarding.onboardscreens.OnBoardThirdFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {
    private val binding by lazy {
        FragmentOnBoardingBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fragmentList = arrayListOf(
            OnBoardFirstFragment(),
            OnBoardSecondFragment(),
            OnBoardThirdFragment(),
            OnBoardFourthFragment(),
            OnBoardFifthFragment(),
        )
        binding.viewPager.adapter = OnBoardingAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)
        binding.btnContinue.setOnClickListener {
            if (binding.viewPager.currentItem < fragmentList.size - 1) {
                binding.viewPager.currentItem = binding.viewPager.currentItem + 1
            } else {
                findNavController().navigate(R.id.action_onBoardingFragment_to_adminOrUserFragment)
            }
        }
        binding.btnSkip.setOnClickListener{
            findNavController().navigate(R.id.action_onBoardingFragment_to_adminOrUserFragment)
        }
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == fragmentList.size - 1) {
                    binding.btnSkip.visibility = View.GONE
                } else {
                    binding.btnSkip.visibility = View.VISIBLE
                }
            }
        })

    }

}
