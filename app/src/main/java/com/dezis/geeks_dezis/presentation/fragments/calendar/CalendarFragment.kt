package com.dezis.geeks_dezis.presentation.fragments.calendar

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.core.common.PreferenceHelper
import com.dezis.geeks_dezis.databinding.FragmentCalendarBinding
import com.dezis.geeks_dezis.presentation.fragments.calendar.view_model.CalendarViewModel
import com.dezis.geeks_dezis.presentation.fragments.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding, CalendarViewModel>(
    R.layout.fragment_calendar
) {

    override val binding: FragmentCalendarBinding by viewBinding(FragmentCalendarBinding::bind)
    override val viewModel: CalendarViewModel by viewModels()

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCalendar()
        setupServiceSelection()
        setupOrderButton()
        setupGoToHomeButton()

        binding.ivArrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_calendarFragment_to_serviceScreenFragment)
        }

        viewModel.bookingMessage.observe(viewLifecycleOwner, Observer { message ->
            message?.let {
                showToastMessage(it)
            }
        })
    }

    private fun setupGoToHomeButton() {
        val successLayout = binding.root.findViewById<View>(R.id.success_layout)
        val btnGoToHome = successLayout?.findViewById<Button>(R.id.btn_go_to_home)
        btnGoToHome?.setOnClickListener {
            findNavController().navigate(R.id.action_calendarFragment_to_homeFragment)
        }
    }

    private fun setupCalendar() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        binding.calendarView.minDate = calendar.timeInMillis

        calendar.add(Calendar.DAY_OF_YEAR, 30)
        binding.calendarView.maxDate = calendar.timeInMillis

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            viewModel.selectedDate.value = "$year-${month + 1}-$dayOfMonth"
            showTimePickerDialog()
        }
    }

    private fun setupServiceSelection() {
        binding.checkboxDisinfection.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleServiceSelection(binding.checkboxDisinfection.text.toString(), isChecked)
        }
        binding.checkboxDeratization.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleServiceSelection(binding.checkboxDeratization.text.toString(), isChecked)
        }
        binding.checkboxDisinsection.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleServiceSelection(binding.checkboxDisinsection.text.toString(), isChecked)
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

        doneButton.setOnClickListener {
            viewModel.selectedTime.value =
                String.format("%02d:%02d", timePicker.hour, timePicker.minute)
            timePickerDialog.dismiss()
        }

        resetButton.setOnClickListener {
            timePickerDialog.dismiss()
        }

        timePickerDialog.show()
    }

    private fun setupOrderButton() {
        binding.orderServiceButton.setOnClickListener {
            val userId = preferenceHelper.getUserId()
            if (userId != -1) {  // проверяем, что userId существует
                viewModel.bookService(userId = userId)
            } else {
                showToastMessage("Ошибка: пользователь не авторизован")
            }
        }
    }

    private fun showToastMessage(message: String) {
        val customToastView = if (message.startsWith("Ошибка")) {
            layoutInflater.inflate(R.layout.custom_toast_error, null)
        } else {
            layoutInflater.inflate(R.layout.custom_toast, null)
        }

        val toastMessageTextView = customToastView.findViewById<TextView>(R.id.toast_message)
        toastMessageTextView.text = message

        val dialog = Dialog(requireContext())
        dialog.setContentView(customToastView)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)

        val goToHomeButton = customToastView.findViewById<Button>(R.id.btn_go_to_home)
        goToHomeButton?.setOnClickListener {
            findNavController().navigate(R.id.action_calendarFragment_to_homeFragment)
            dialog.dismiss()
        }

        val okButton = customToastView.findViewById<Button>(R.id.btn_ok)
        okButton?.setOnClickListener {
            dialog.dismiss()
        }

        val params = dialog.window?.attributes
        params?.width = ViewGroup.LayoutParams.WRAP_CONTENT
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes = params

        dialog.show()
    }
}
