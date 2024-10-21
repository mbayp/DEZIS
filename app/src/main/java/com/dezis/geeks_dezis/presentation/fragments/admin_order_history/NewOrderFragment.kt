package com.dezis.geeks_dezis.presentation.fragments.admin_order_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.R
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewOrderFragment : Fragment() {

    private lateinit var orderAdapter: OrderAdapter
    private lateinit var ordersRecyclerView: RecyclerView
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_new_order, container, false)

        ordersRecyclerView = view.findViewById(R.id.ordersRecyclerView)
        tabLayout = view.findViewById(R.id.tabLayout)

        orderAdapter = OrderAdapter()
        ordersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ordersRecyclerView.adapter = orderAdapter

        showNewOrders()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> showNewOrders()
                    1 -> showCompletedOrders()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        return view
    }

    private fun showNewOrders() {
        val newOrders = listOf(
            Order("Alexey Ivanovich", "Дезинфекция", "Восток-5, 13/21", "17.11.2024, 21:30", false),
        )
        orderAdapter.setOrders(newOrders)
    }

    private fun showCompletedOrders() {
        val completedOrders = listOf(
            Order("Alexey Ivanovich", "Дезинфекция", "Восток-5, 13/21", "17.11.2024, 21:30", true),
        )
        orderAdapter.setOrders(completedOrders)
    }

}