package com.dezis.geeks_dezis.admin.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dezis.geeks_dezis.admin.data.Request
import com.dezis.geeks_dezis.databinding.FragmentRequestBinding

class RequestFragment : Fragment() {

    private var _binding: FragmentRequestBinding? = null
    private val binding get() = _binding!!

    private lateinit var requestAdapter: RequestAdapter
    private lateinit var requests: List<Request>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRequestBinding.inflate(inflater, container, false)

        // Инициализация списка запросов с тестовыми данными
        requests = listOf(
            Request(
                date = "17.11.2024",
                fullName = "Abduganueva Ayana",
                email = "ayanaabdgva1@gmail.com",
                phone = "+996 500 848 484",
                address = "Восток-5",
                apartmentNumber = "51/12"
            ),
            Request(
                date = "18.11.2024",
                fullName = "Иванов Иван",
                email = "ivanov@example.com",
                phone = "+996 500 123 456",
                address = "Центр-1",
                apartmentNumber = "10"
            ),
            Request(
                date = "19.11.2024",
                fullName = "Петрова Анна",
                email = "petrova@example.com",
                phone = "+996 500 654 321",
                address = "Юг-2",
                apartmentNumber = "15A"
            )
        )

        requestAdapter = RequestAdapter(requests)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = requestAdapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
