package com.dezis.geeks_dezis.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dezis.geeks_dezis.R
import com.google.android.material.button.MaterialButton

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

        // Добавляем кнопку "ОК"
        builder.setPositiveButton("ОК") { dialog, _ ->
            dialog.dismiss() // Закрываем диалог при нажатии на кнопку
        }

        // Создаем и показываем диалог
        val dialog = builder.create()
        dialog.show()
    }
}