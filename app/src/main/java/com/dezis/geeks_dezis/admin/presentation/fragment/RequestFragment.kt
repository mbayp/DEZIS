// RequestFragment.kt
package com.dezis.geeks_dezis.admin.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dezis.geeks_dezis.admin.data.Request
import com.dezis.geeks_dezis.api.apis.BookApiService
import com.dezis.geeks_dezis.databinding.FragmentRequestBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestFragment : Fragment() {

    private var _binding: FragmentRequestBinding? = null
    private val binding get() = _binding!!

    private lateinit var requestAdapter: RequestAdapter
    private val requests: MutableList<Request> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRequestBinding.inflate(inflater, container, false)

        requestAdapter = RequestAdapter(requests)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = requestAdapter

        fetchRequests()

        return binding.root
    }

    private fun fetchRequests() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://209.38.228.54:8084/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(BookApiService::class.java)
        apiService.getRequests().enqueue(object : Callback<List<Request>> {
            override fun onResponse(call: Call<List<Request>>, response: Response<List<Request>>) {
                if (response.isSuccessful && response.body() != null) {
                    requests.clear()
                    requests.addAll(response.body()!!)
                    requestAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Request>>, t: Throwable) {
                // Обработка ошибки
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
