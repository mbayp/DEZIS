package com.dezis.geeks_dezis.admin.presentation.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.databinding.ActivityRequestBinding
import com.dezis.geeks_dezis.admin.presentation.fragment.RequestFragment

class RequestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Устанавливаем обработку для отступов
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Проверяем, был ли ранее добавлен фрагмент
        if (savedInstanceState == null) {
            // Добавляем RequestFragment в контейнер активности
            supportFragmentManager.beginTransaction()
                .replace(binding.main.id, RequestFragment()) // Используйте ID контейнера для замены
                .commit()
        }
    }
}
