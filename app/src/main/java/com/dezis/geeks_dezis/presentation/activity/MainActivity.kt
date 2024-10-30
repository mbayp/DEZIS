package com.dezis.geeks_dezis.presentation.activity

import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_controller) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initBottomNav()
        initBottom()
        initAdminBottom()

        val warningText = SpannableStringBuilder("Нет соединения с интернетом\n\n")
        warningText.append("Проверьте подключение к интернету")
        binding.networkWarning.text = warningText

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
            window.statusBarColor = android.graphics.Color.TRANSPARENT
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            binding.root.setOnApplyWindowInsetsListener { view, insets ->
                view.setPadding(0, insets.systemWindowInsetTop, 0, 0)
                insets
            }
        }

        viewModel.networkLiveData.observe(this) { isConnected ->
            if (isConnected) {
                binding.networkWarning.visibility = View.GONE
                window.statusBarColor = ContextCompat.getColor(this, R.color.dark_blue)
            } else {
                binding.networkWarning.visibility = View.VISIBLE
                window.statusBarColor = ContextCompat.getColor(this, R.color.grey_dark)

            }
            getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
        }
    }

    private fun initBottom() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashScreenFragment,
                R.id.onBoardingFragment,
                R.id.authorizationFragment,
                R.id.secondAuthorizationFragment,
                R.id.codeVerificationFragment,
                R.id.waitingFragment3,
                R.id.signInFragment,
                R.id.adminOrUserFragment,
                R.id.adminSignInFragment,
                R.id.logInOrSignInFragment -> {
                    binding.bottomNav.visibility = View.GONE
                }
                else -> {
                    binding.bottomNav.visibility = View.VISIBLE
                }
            }
        }

        binding.bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.calendar -> {
                    navController.navigate(R.id.calendarFragment)
                    true
                }
                R.id.chatFragment -> {
                    navController.navigate(R.id.chatFragment2)
                    true
                }
                R.id.profile -> {
                    navController.navigate(R.id.profile)
                    true
                }
                else -> false
            }
        }
    }

    private fun initAdminBottom() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.newOrderFragment, R.id.requestFragment -> {
                    binding.bottomNav.visibility = View.GONE
                    binding.adminBottomNav.visibility = View.VISIBLE
                }
                else -> {
                    binding.adminBottomNav.visibility = View.GONE
                }
            }
        }

        binding.adminBottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.requestFragment-> {
                    navController.navigate(R.id.requestFragment)
                    true
                }
                R.id.chatFragment -> {
                    navController.navigate(R.id.chatFragment)
                    true
                }
                R.id.newOrderFragment -> {
                    navController.navigate(R.id.newOrderFragment) // Фрагмент "Запросы"
                    true
                }
                else -> false
            }
        }
    }
    private fun initBottomNav() {
        binding.bottomNav.apply {
            setupWithNavController(navController)
        }
    }


}
