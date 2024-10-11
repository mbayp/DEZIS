package com.dezis.geeks_dezis.presentation.fragments.calendar.view_model

import android.app.Application
import com.dezis.geeks_dezis.core.base.BaseViewModel
import androidx.lifecycle.MutableLiveData

class CalendarViewModel : BaseViewModel() {

    val selectedService = MutableLiveData<String?>()
    val selectedDate = MutableLiveData<String?>()
    val selectedTime = MutableLiveData<String?>()
    val bookingMessage = MutableLiveData<String?>()

    private val bookedServices = mutableMapOf<String, Pair<String, String>>()

    fun updateSelectedService(serviceName: String, isChecked: Boolean) {
        if (isChecked) {
            if (selectedService.value == null) {
                selectedService.value = serviceName
            } else {
                selectedService.value = serviceName
            }
        } else if (selectedService.value == serviceName) {
            selectedService.value = null
        }
    }

    fun bookService(): Boolean {
        val service = selectedService.value
        val date = selectedDate.value
        val time = selectedTime.value

        if (service != null && date != null && time != null) {
            if (bookedServices.containsKey(service) && bookedServices[service]?.first == date && bookedServices[service]?.second == time) {
                return false
            }
            bookedServices[service] = Pair(date, time)
            bookingMessage.value = "Услуга: $service\nДата: $date\nВремя: $time"
            return true
        }
        return false
    }
}
