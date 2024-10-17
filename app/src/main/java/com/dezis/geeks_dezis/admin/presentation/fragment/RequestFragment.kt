package com.dezis.geeks_dezis.admin.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dezis.geeks_dezis.admin.data.Booking
import com.dezis.geeks_dezis.api.RetrofitClient
import com.dezis.geeks_dezis.databinding.FragmentRequestBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestFragment : Fragment() {

    private var _binding: FragmentRequestBinding? = null
    private val binding get() = _binding!!

    private lateinit var requestAdapter: RequestAdapter
    private val bookings: MutableList<Booking> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestBinding.inflate(inflater, container, false)

        requestAdapter = RequestAdapter(bookings)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = requestAdapter

        fetchBookings()

        return binding.root
    }

    private fun fetchBookings() {
        RetrofitClient.bookApiService.getBookings().enqueue(object : Callback<List<Booking>> {
            override fun onResponse(call: Call<List<Booking>>, response: Response<List<Booking>>) {
                if (response.isSuccessful && response.body() != null) {
                    val bookingsResponse = response.body()!!
                    Log.d("RequestFragment", "Получены данные: $bookingsResponse")

                    bookings.clear()
                    bookings.addAll(bookingsResponse)
                    requestAdapter.notifyDataSetChanged()
                } else {
                    Log.e("RequestFragment", "Ошибка ответа: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Booking>>, t: Throwable) {
                Log.e("RequestFragment", "Ошибка вызова API: ${t.message}")
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
