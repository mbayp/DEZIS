package com.dezis.geeks_dezis.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_controller) as NavHostFragment
        navHostFragment.navController
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initBottom()
    }

    private fun initBottom() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashScreenFragment,
                R.id.onBoardingFragment,
                R.id.authorizationFragment,
                R.id.secondAuthorizationFragment,
                R.id.signInFragment,
                R.id.codeVerificationFragment,
                R.id.successfulVerificationFragment-> {
                    binding.bottomNav.visibility = View.GONE
                }
                else -> {
                    binding.bottomNav.visibility = View.VISIBLE
                }
            }
        }
    }


}