package com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.BottomSheetTimePickerBinding
import com.dezis.geeks_dezis.databinding.CalendarViewBinding
import com.dezis.geeks_dezis.databinding.FragmentServiceScreenBinding
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
            .setPositiveButton("Открыть календарь") { dialog, _ -> openCustomCalendar(serviceName) }
            .setNegativeButton("Назад") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun openCustomCalendar(serviceName: String) {
        val builder = AlertDialog.Builder(requireContext())
        val calendarBinding = CalendarViewBinding.inflate(layoutInflater)
        builder.setView(calendarBinding.root)

        calendarBinding.calendarView.minDate = System.currentTimeMillis()
        calendarBinding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            Toast.makeText(requireContext(), "Выбрана дата: $selectedDate", Toast.LENGTH_SHORT).show()
            showTimePicker(serviceName, selectedDate)
        }

        builder.setNegativeButton("Закрыть") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

    private fun showTimePicker(serviceName: String, selectedDate: String) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val timePickerBinding = BottomSheetTimePickerBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(timePickerBinding.root)

        timePickerBinding.timeButton1.setOnClickListener {
            handleTimeSelection(serviceName, selectedDate, "10:00 - 11:00", bottomSheetDialog)
        }
        timePickerBinding.timeButton2.setOnClickListener {
            handleTimeSelection(serviceName, selectedDate, "12:00 - 13:00", bottomSheetDialog)
        }
        timePickerBinding.timeButton3.setOnClickListener {
            handleTimeSelection(serviceName, selectedDate, "14:00 - 15:00", bottomSheetDialog)
        }
        timePickerBinding.timeButton4.setOnClickListener {
            handleTimeSelection(serviceName, selectedDate, "16:00 - 17:00", bottomSheetDialog)
        }

        bottomSheetDialog.show()
    }

    private fun handleTimeSelection(serviceName: String, selectedDate: String, selectedTime: String, dialog: BottomSheetDialog) {
        viewModel.handleTimeSelection(serviceName, selectedDate, selectedTime,
            { message ->
                if (isAdded) {
                    showBookingNotification(message)
                    dialog.dismiss()
                }
            },
            { errorMessage ->
                if (isAdded) {
                    showBookingNotification(errorMessage)
                }
            }
        )
    }

    private fun showBookingNotification(message: String) {
        val inflater = layoutInflater
        val toastLayout = inflater.inflate(R.layout.custom_toast, null)
        val toastMessage = toastLayout.findViewById<TextView>(R.id.toast_message)
        toastMessage.text = message

        val toast = Toast(requireContext())
        toast.duration = Toast.LENGTH_LONG
        toast.view = toastLayout
        toast.setGravity(android.view.Gravity.CENTER, 0, 0)
        toast.show()
    }
}
