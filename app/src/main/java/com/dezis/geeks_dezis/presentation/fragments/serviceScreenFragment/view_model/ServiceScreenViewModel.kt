package com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment.view_model

import com.dezis.geeks_dezis.core.base.BaseViewModel

class ServiceScreenViewModel : BaseViewModel() {

    private val serviceSchedule = mutableMapOf<String, MutableList<Pair<String, String>>>()

    fun handleTimeSelection(serviceName: String, selectedDate: String, selectedTime: String, onBookingSuccess: (String) -> Unit, onBookingFailure: (String) -> Unit) {
        // Проверка на уже занятое время
        val isTimeTaken = serviceSchedule.values.any {
            it.any { it.first == selectedDate && it.second == selectedTime }
        }

        // Проверка на выбор нескольких услуг в одну дату
        if (isTimeTaken) {
            onBookingFailure("Время занято на выбранную дату.")
            return
        }

        // Проверка на выбор одной услуги на одну дату
        if (serviceSchedule.containsKey(serviceName) && serviceSchedule[serviceName]?.any { it.first == selectedDate } == true) {
            onBookingFailure("Услуга \"$serviceName\" уже забронирована на $selectedDate.")
            return
        }

        // Если все проверки пройдены, добавляем запись в расписание
        if (!serviceSchedule.containsKey(serviceName)) {
            serviceSchedule[serviceName] = mutableListOf()
        }
        serviceSchedule[serviceName]?.add(Pair(selectedDate, selectedTime))

        val message = "Вы забронировали услугу \"$serviceName\" на $selectedDate в $selectedTime. С вами вскоре свяжется менеджер."
        onBookingSuccess(message)
    }
}
