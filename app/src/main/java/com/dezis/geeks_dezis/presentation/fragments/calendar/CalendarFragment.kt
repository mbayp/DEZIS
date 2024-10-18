package com.dezis.geeks_dezis.presentation.fragments.calendar

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentCalendarBinding
import com.dezis.geeks_dezis.presentation.fragments.calendar.view_model.CalendarViewModel
import com.dezis.geeks_dezis.presentation.fragments.viewBinding
import java.util.Calendar

class CalendarFragment : BaseFragment<FragmentCalendarBinding, CalendarViewModel>(
    R.layout.fragment_calendar
) {

    override val binding: FragmentCalendarBinding by viewBinding(FragmentCalendarBinding::bind)

    override val viewModel: CalendarViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCalendar()
        setupServiceSelection()
        setupOrderButton()

        viewModel.bookingMessage.observe(viewLifecycleOwner, Observer { message ->
            if (message != null) showToast(message)
        })
    }

    private fun setupCalendar() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        binding.calendarView.minDate = calendar.timeInMillis

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            viewModel.selectedDate.value = "$year-${month + 1}-$dayOfMonth"
            showTimePickerDialog()
        }
    }

    private fun setupServiceSelection() {
        binding.radioDisinfection.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateSelectedService(binding.radioDisinfection.text.toString(), isChecked)
        }
        binding.radioDeratization.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateSelectedService(binding.radioDeratization.text.toString(), isChecked)
        }
        binding.radioDisinsection.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateSelectedService(binding.radioDisinsection.text.toString(), isChecked)
        }
    }

    private fun showTimePickerDialog() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.custom_time_picker, null)
        val timePicker: TimePicker = dialogView.findViewById(R.id.datePicker1)
        val resetButton: Button = dialogView.findViewById(R.id.reset_button)
        val doneButton: Button = dialogView.findViewById(R.id.done_button)

        val timePickerDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)
            .create()

        val calendar = Calendar.getInstance()
        timePicker.hour = calendar.get(Calendar.HOUR_OF_DAY)
        timePicker.minute = calendar.get(Calendar.MINUTE)

        doneButton.setOnClickListener {
            viewModel.selectedTime.value =
                String.format("%02d:%02d", timePicker.hour, timePicker.minute)
            timePickerDialog.dismiss()
        }

        resetButton.setOnClickListener {
            timePicker.hour = calendar.get(Calendar.HOUR_OF_DAY)
            timePicker.minute = calendar.get(Calendar.MINUTE)
        }

        timePickerDialog.show()
    }

    private fun setupOrderButton() {
        binding.orderServiceButton.setOnClickListener {
            viewModel.bookService(userId = 7)  // Укажите реальный ID пользователя
        }
    }

    private fun showToast(message: String) {
        val layoutInflater = layoutInflater
        val customToastView = layoutInflater.inflate(R.layout.custom_toast, null)

        val toastMessageTextView = customToastView.findViewById<TextView>(R.id.toast_message)
        toastMessageTextView.text = message

        val toast = Toast(requireContext())
        toast.duration = Toast.LENGTH_LONG
        toast.view = customToastView
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}
