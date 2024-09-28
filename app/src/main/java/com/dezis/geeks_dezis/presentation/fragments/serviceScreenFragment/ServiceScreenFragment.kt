package com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.databinding.BottomSheetTimePickerBinding
import com.dezis.geeks_dezis.databinding.CalendarViewBinding
import com.dezis.geeks_dezis.databinding.FragmentServiceScreenBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ServiceScreenFragment : Fragment() {

    private var _binding: FragmentServiceScreenBinding? = null
    private val binding get() = _binding!!
    private val serviceSchedule = mutableMapOf<String, MutableList<Pair<String, String>>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentServiceScreenBinding.inflate(inflater, container, false)

        binding.button1.setOnClickListener {
            showDialog("Дезинфекция", "Описание процесса дезинфекции...")
        }

        binding.button2.setOnClickListener {
            showDialog("Дезинсекция", "Описание процесса дезинсекции...")
        }

        binding.button3.setOnClickListener {
            showDialog("Дератизация", "Описание процесса дератизации...")
        }

        return binding.root
    }

    private fun showDialog(serviceName: String, message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(serviceName)
        builder.setMessage(message)

        builder.setPositiveButton("Открыть календарь") { dialog, _ ->
            openCustomCalendar(serviceName)
        }

        builder.setNegativeButton("Назад") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
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

        builder.setNegativeButton("Закрыть") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
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
        val isTimeTaken = serviceSchedule.values.any { it.any { it.first == selectedDate && it.second == selectedTime } }

        if (isTimeTaken) {
            Toast.makeText(requireContext(), "Ошибка: выбранное время уже занято.", Toast.LENGTH_SHORT).show()
        } else {
            if (!serviceSchedule.containsKey(serviceName)) {
                serviceSchedule[serviceName] = mutableListOf()
            }
            serviceSchedule[serviceName]?.add(Pair(selectedDate, selectedTime))

            showBookingNotification(serviceName, selectedDate, selectedTime)
            dialog.dismiss()
        }
    }

    private fun showBookingNotification(serviceName: String, selectedDate: String, selectedTime: String) {
        val message = "Вы забронировали услугу \"$serviceName\" на $selectedDate в $selectedTime. С вами вскоре свяжется менеджер."

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




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
