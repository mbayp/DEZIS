package com.dezis.geeks_dezis.presentation.fragments.calendar.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dezis.geeks_dezis.data.remote.model.BookingRequest
import com.dezis.geeks_dezis.data.remote.model.BookingResponse
import com.dezis.geeks_dezis.data.remote.retrofit.RetrofitClient
import com.dezis.geeks_dezis.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(): BaseViewModel() {

    val selectedService = MutableLiveData<String?>()
    val selectedDate = MutableLiveData<String?>()
    val selectedTime = MutableLiveData<String?>()
    val bookingMessage = MutableLiveData<String?>()

    fun updateSelectedService(serviceName: String, isChecked: Boolean) {
        if (isChecked) {
            selectedService.value = serviceName
        } else if (selectedService.value == serviceName) {
            selectedService.value = null
        }
    }

    fun bookService(userId: Int) {
        val service = selectedService.value
        val date = selectedDate.value
        val time = selectedTime.value

        if (service != null && date != null && time != null) {
            val bookingRequest = BookingRequest(
                user = userId,
                service = service,
                date = date,
                time = time
            )

            viewModelScope.launch(Dispatchers.IO) {
                RetrofitClient.bookApiService.bookService(bookingRequest)
                    .enqueue(object : Callback<BookingResponse> {
                        override fun onResponse(
                            call: Call<BookingResponse>,
                            response: Response<BookingResponse>
                        ) {
                            if (response.isSuccessful) {
                                bookingMessage.postValue("Бронирование успешно: ${response.body()}")
                            } else {
                                bookingMessage.postValue("Ошибка бронирования: ${response.errorBody()}")
                            }
                        }

                        override fun onFailure(call: Call<BookingResponse>, t: Throwable) {
                            bookingMessage.postValue("Ошибка соединения: ${t.message}")
                        }
                    })
            }
        }
    }
}
