package com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.BottomSheetTimePickerBinding
import com.dezis.geeks_dezis.databinding.FragmentServiceScreenBinding
import com.dezis.geeks_dezis.presentation.fragments.calendar.CalendarFragment
import com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment.view_model.ServiceScreenViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class ServiceScreenFragment : BaseFragment<FragmentServiceScreenBinding, ServiceScreenViewModel>(R.layout.fragment_service_screen) {

    override val binding: FragmentServiceScreenBinding by viewBinding(FragmentServiceScreenBinding::bind)
    override val viewModel: ServiceScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun init() {
        super.init()
        setupListeners()
    }

    private fun setupListeners() {
        binding.button1.setOnClickListener {
            showDialog("Дезинфекция", "Описание процесса дезинфекции...")
        }

        binding.button2.setOnClickListener {
            showDialog("Дезинсекция", "Описание процесса дезинсекции...")
        }

        binding.button3.setOnClickListener {
            showDialog("Дератизация", "Описание процесса дератизации...")
        }
    }

    private fun showDialog(serviceName: String, message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(serviceName)
            .setMessage(message)
            .setPositiveButton("Открыть календарь") { dialog, _ -> openCalendarFragment() }
            .setNegativeButton("Назад") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun openCalendarFragment() {
        val calendarFragment = CalendarFragment()

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, calendarFragment)
            .addToBackStack(null)
            .commit()
    }
}
