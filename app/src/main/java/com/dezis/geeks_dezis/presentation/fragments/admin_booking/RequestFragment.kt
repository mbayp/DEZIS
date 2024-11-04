package com.dezis.geeks_dezis.presentation.fragments.admin_booking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dezis.geeks_dezis.data.remote.apiservice.DezisApiService
import com.dezis.geeks_dezis.data.remote.model.Booking
import com.dezis.geeks_dezis.databinding.FragmentRequestBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class RequestFragment : Fragment() {

    private var _binding: FragmentRequestBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var dezisApiService: DezisApiService

    private lateinit var requestAdapter: RequestAdapter
    private val bookings: MutableList<Booking> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRequestBinding.inflate(inflater, container, false)

        setupRecyclerView()
        fetchBookings()
        setupOnBackPressedCallback()

        return binding.root
    }

    private fun setupRecyclerView() {
        requestAdapter = RequestAdapter(bookings)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = requestAdapter
        }
    }

    private fun fetchBookings() {
        dezisApiService.getBookings().enqueue(object : Callback<List<Booking>> {
            override fun onResponse(call: Call<List<Booking>>, response: Response<List<Booking>>) {
                if (response.isSuccessful) {
                    response.body()?.let { bookingsResponse ->
                        Log.d("RequestFragment", "Получены данные: $bookingsResponse")
                        updateBookings(bookingsResponse)
                    } ?: showError("Ошибка: Пустой ответ от сервера")
                } else {
                    showError("Ошибка ответа: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Booking>>, t: Throwable) {
                showError("Ошибка вызова API: ${t.message}")
            }
        })
    }

    private fun updateBookings(newBookings: List<Booking>) {
        bookings.clear()
        bookings.addAll(newBookings)
        requestAdapter.notifyDataSetChanged()
    }

    private fun setupOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finishAffinity() // Закрываем приложение для экрана администратора
                }
            })
    }

    private fun showError(message: String) {
        Log.e("RequestFragment", message)
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        bookings.clear() // Сброс данных для предотвращения утечек памяти
    }
}
