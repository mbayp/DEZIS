package com.dezis.geeks_dezis.presentation.fragments.admin_order_history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.data.remote.apiservice.DezisApiService
import com.dezis.geeks_dezis.data.remote.model.booking.Booking
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class NewOrderFragment : Fragment() {

    @Inject
    lateinit var dezisApiService: DezisApiService

    private var orderAdapter = OrderAdapter()

    private lateinit var ordersRecyclerView: RecyclerView

    private lateinit var tabLayout: TabLayout

    private val orders: MutableList<Booking> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_new_order, container, false)


        val backButton: ImageView = view.findViewById(R.id.img_back)


        backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        ordersRecyclerView = view.findViewById(R.id.ordersRecyclerView)
        tabLayout = view.findViewById(R.id.tabLayout)

        ordersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ordersRecyclerView.adapter = orderAdapter

        fetchNewOrders()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> fetchNewOrders()
                    1 -> showCompletedOrders()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        return view
    }

    private fun fetchNewOrders() {
        dezisApiService.getBookings().enqueue(object : Callback<List<Booking>> {
            override fun onResponse(call: Call<List<Booking>>, response: Response<List<Booking>>) {
                if (response.isSuccessful && response.body() != null) {
                    val ordersResponse = response.body()!!
                    orders.clear()
                    orders.addAll(ordersResponse)
                    orderAdapter.setOrders(orders)
                    orderAdapter.notifyDataSetChanged()
                } else {
                    Log.e(
                        "NewOrderFragment",
                        "Ошибка ответа: ${response.code()} ${response.message()}"
                    )
                }
            }

            override fun onFailure(call: Call<List<Booking>>, t: Throwable) {
                Log.e("NewOrderFragment", "Ошибка вызова API: ${t.message}")
            }
        })
    }

    private fun showCompletedOrders() {
    }

}