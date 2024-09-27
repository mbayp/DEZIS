package com.dezis.geeks_dezis.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dezis.geeks_dezis.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import java.util.*

class ServiceScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_screen)

        val button1 = findViewById<MaterialButton>(R.id.button1)
        val button2 = findViewById<MaterialButton>(R.id.button2)
        val button3 = findViewById<MaterialButton>(R.id.button3)

        // Нажатие на кнопку "Дезинфекция"
        button1.setOnClickListener {
            showDialog("Дезинфекция", "Описание процесса дезинфекции...")
        }

        // Нажатие на кнопку "Дезинсекция"
        button2.setOnClickListener {
            showDialog("Дезинсекция", "Описание процесса дезинсекции...")
        }

        // Нажатие на кнопку "Дератизация"
        button3.setOnClickListener {
            showDialog("Дератизация", "Описание процесса дератизации...")
        }
    }

    // Метод для показа диалога
    private fun showDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)

        // Добавляем кнопку "Открыть календарь"
        builder.setPositiveButton("Открыть календарь") { dialog, _ ->
            openCustomCalendar() // Открываем кастомный календарь
        }

        // Добавляем кнопку "Назад"
        builder.setNegativeButton("Назад") { dialog, _ ->
            dialog.dismiss() // Закрываем диалог при нажатии на кнопку "Назад"
        }

        // Создаем и показываем диалог
        val dialog = builder.create()
        dialog.show()
    }

    // Метод для открытия кастомного календаря
    private fun openCustomCalendar() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.calendar_view, null)
        builder.setView(dialogView)

        val calendarView = dialogView.findViewById<CalendarView>(R.id.calendarView)

        // Обработка выбора даты
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            Toast.makeText(this, "Выбрана дата: $selectedDate", Toast.LENGTH_SHORT).show()
            // Показать выбор времени после выбора даты
            showTimePicker()
        }

        builder.setNegativeButton("Закрыть") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    // Метод для показа BottomSheet с выбором времени
    private fun showTimePicker() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_time_picker, null)

        // Устанавливаем слушатели для кнопок времени
        val timeButton1 = bottomSheetView.findViewById<MaterialButton>(R.id.timeButton1)
        val timeButton2 = bottomSheetView.findViewById<MaterialButton>(R.id.timeButton2)
        val timeButton3 = bottomSheetView.findViewById<MaterialButton>(R.id.timeButton3)
        val timeButton4 = bottomSheetView.findViewById<MaterialButton>(R.id.timeButton4)

        timeButton1.setOnClickListener {
            Toast.makeText(this, "Выбрано время: 10:00 - 11:00", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }

        timeButton2.setOnClickListener {
            Toast.makeText(this, "Выбрано время: 12:00 - 13:00", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }

        timeButton3.setOnClickListener {
            Toast.makeText(this, "Выбрано время: 14:00 - 15:00", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }

        timeButton4.setOnClickListener {
            Toast.makeText(this, "Выбрано время: 16:00 - 17:00", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }
}
