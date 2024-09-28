package com.dezis.geeks_dezis.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment.ServiceScreenFragment

class ServiceScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_screen)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ServiceScreenFragment())
                .commit()
        }
    }
}
