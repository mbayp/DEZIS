package com.dezis.geeks_dezis.presentation.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentHomeBinding
import com.dezis.geeks_dezis.presentation.fragments.viewBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    override val viewModel: HomeViewModel by viewModels()

    private lateinit var serviceAdapter: ServiceAdapter
    private lateinit var informationAdapter: InformationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDataService()
        addDataInformation()
    }

    private fun addDataInformation() {
        informationAdapter = InformationAdapter()
        informationAdapter.submitList(
            listOf(
                InformationModel(1, getString(R.string.information1)),
                InformationModel(2, getString(R.string.information2)),
                InformationModel(3, getString(R.string.information3)),

                )
        )
        binding.rvInformation.adapter = informationAdapter
    }

    private fun addDataService() {
        serviceAdapter = ServiceAdapter()
        serviceAdapter.submitList(
            listOf(
                ServiceModel(
                    service = getString(R.string.deratization),
                    info = getString(R.string.deratization_info),
                    btn = getString(R.string.deratization)
                ),
                ServiceModel(
                    service = getString(R.string.disinfection),
                    info = getString(R.string.disinfection_info),
                    btn = getString(R.string.disinfection)
                ),
                ServiceModel(
                    service = getString(R.string.disinsection),
                    info = getString(R.string.disinsection_info),
                    btn = getString(R.string.disinsection)
                ),
            )
        )
        binding.rvService.adapter = serviceAdapter
    }

}