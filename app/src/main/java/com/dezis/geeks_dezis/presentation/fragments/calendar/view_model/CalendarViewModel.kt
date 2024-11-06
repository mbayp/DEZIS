package com.dezis.geeks_dezis.presentation.fragments.calendar.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dezis.geeks_dezis.core.base.BaseViewModel
import com.dezis.geeks_dezis.data.remote.apiservice.DezisApiService
import com.dezis.geeks_dezis.data.remote.model.booking.BookingRequest
import com.dezis.geeks_dezis.data.remote.model.booking.BookingResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val dezisApiService: DezisApiService
) : BaseViewModel() {

    private val selectedServices = MutableLiveData<MutableList<String>>(mutableListOf())
    val selectedDate = MutableLiveData<String?>()
    val selectedTime = MutableLiveData<String?>()
    val bookingMessage = MutableLiveData<String?>()

    fun toggleServiceSelection(serviceName: String, isChecked: Boolean) {
        val services = selectedServices.value ?: mutableListOf()
        if (isChecked) {
            services.add(serviceName)
        } else {
            services.remove(serviceName)
        }
        selectedServices.value = services
    }

    fun bookService(userId: Int) {
        val services = selectedServices.value
        val date = selectedDate.value
        val time = selectedTime.value

        if (!services.isNullOrEmpty() && date != null && time != null) {
            val bookingRequest = BookingRequest(
                user = userId,
                service = services.joinToString(", "),
                date = date,
                time = time
            )

            viewModelScope.launch(Dispatchers.IO) {
                dezisApiService.bookService(bookingRequest)
                    .enqueue(object : Callback<BookingResponse> {
                        override fun onResponse(
                            call: Call<BookingResponse>,
                            response: Response<BookingResponse>
                        ) {
                            if (response.isSuccessful) {
                                bookingMessage.postValue("Бронирование успешно")
                            } else {
                                bookingMessage.postValue("Ошибка бронирования: ${response.message()}")
                            }
                        }

                        override fun onFailure(call: Call<BookingResponse>, t: Throwable) {
                            bookingMessage.postValue("Ошибка соединения: ${t.message}")
                        }
                    })
            }
        } else {
            bookingMessage.postValue("Ошибка: Пожалуйста, выберите услугу, дату и время")
        }
    }

}