package com.dezis.geeks_dezis.admin.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.R
import com.google.android.material.tabs.TabLayout

class NewOrderFragment : Fragment() {

    private lateinit var orderAdapter: OrderAdapter
    private lateinit var ordersRecyclerView: RecyclerView
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_order, container, false)

        // Инициализация RecyclerView и TabLayout
        ordersRecyclerView = view.findViewById(R.id.ordersRecyclerView)
        tabLayout = view.findViewById(R.id.tabLayout)

        // Настройка RecyclerView
        orderAdapter = OrderAdapter()
        ordersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ordersRecyclerView.adapter = orderAdapter

        // Изначально показываем новые заказы
        showNewOrders()

        // Обработка переключения вкладок
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> showNewOrders() // Новые заказы
                    1 -> showCompletedOrders() // Завершенные заказы
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
            // Добавь другие новые заказы здесь
        )
        orderAdapter.setOrders(newOrders)
    }

    private fun showCompletedOrders() {
        val completedOrders = listOf(
            Order("Alexey Ivanovich", "Дезинфекция", "Восток-5, 13/21", "17.11.2024, 21:30", true),
            // Добавь другие завершенные заказы здесь
        )
        orderAdapter.setOrders(completedOrders)
    }
}