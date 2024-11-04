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
import com.dezis.geeks_dezis.core.utils.PreferenceHelper
import com.dezis.geeks_dezis.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    @Inject
    lateinit var sharedPreferences: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_controller) as NavHostFragment
        navController = navHostFragment.navController

        checkUserState()
        initBottomNav()
        setupNetworkWarnings()
        handleWindowInsets()

        viewModel.networkLiveData.observe(this) { isConnected ->
            updateNetworkStatus(isConnected)
        }
    }

    private fun checkUserState() {
        when {
            sharedPreferences.isAdminSignedIn() -> {
                navController.navigate(R.id.requestFragment)
            }
            sharedPreferences.isUserSignedIn() -> {
                navController.navigate(R.id.homeFragment)
            }
            sharedPreferences.isOnboardingShown() -> {
                navController.navigate(R.id.onBoardFirstFragment)
                sharedPreferences.setOnboardingShown()
            }
            else -> {
                navController.navigate(R.id.splashScreenFragment)
            }
        }
    }

    private fun setupNetworkWarnings() {
        val warningText = SpannableStringBuilder("Нет соединения с интернетом\n\n")
        warningText.append("Проверьте подключение к интернету")
        binding.networkWarning.text = warningText
    }

    private fun handleWindowInsets() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = android.graphics.Color.TRANSPARENT
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            binding.root.setOnApplyWindowInsetsListener { view, insets ->
                view.setPadding(0, insets.systemWindowInsetTop, 0, 0)
                insets
            }
        }
    }

    private fun updateNetworkStatus(isConnected: Boolean) {
        binding.networkWarning.visibility = if (isConnected) View.GONE else View.VISIBLE
        window.statusBarColor = if (isConnected) {
            ContextCompat.getColor(this, R.color.dark_blue)
        } else {
            ContextCompat.getColor(this, R.color.grey_dark)
        }
        window.navigationBarColor = ContextCompat.getColor(this, R.color.dark_blue)
    }

    private fun initBottomNav() {
        binding.bottomNav.setupWithNavController(navController)
        initUserBottomNav()
        initAdminBottomNav()

        // Добавляем слушатель изменения пункта назначения для обновления состояния bottomNav
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (isUserScreen(destination.id)) {
                binding.bottomNav.visibility = View.VISIBLE
                binding.adminBottomNav.visibility = View.GONE
                binding.bottomNav.menu.findItem(destination.id)?.isChecked = true
            } else if (isAdminScreen(destination.id)) {
                binding.bottomNav.visibility = View.GONE
                binding.adminBottomNav.visibility = View.VISIBLE
                binding.adminBottomNav.menu.findItem(destination.id)?.isChecked = true
            } else {
                // Если это экран, на котором не должно быть bottomNav, скрываем оба
                binding.bottomNav.visibility = View.GONE
                binding.adminBottomNav.visibility = View.GONE
            }
        }
    }

    private fun initUserBottomNav() {
        binding.bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.calendarFragment -> {
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

    private fun initAdminBottomNav() {
        binding.adminBottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.requestFragment -> {
                    navController.navigate(R.id.requestFragment)
                    true
                }
                R.id.chatFragment -> {
                    navController.navigate(R.id.chatFragment2)
                    true
                }
                R.id.newOrderFragment -> {
                    navController.navigate(R.id.newOrderFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun isUserScreen(destinationId: Int): Boolean {
        return destinationId in listOf(
            R.id.homeFragment,
            R.id.calendarFragment,
            R.id.chatFragment2,
            R.id.profile
        )
    }

    private fun isAdminScreen(destinationId: Int): Boolean {
        return destinationId in listOf(
            R.id.requestFragment,
            R.id.chatFragment2,
            R.id.newOrderFragment
        )
    }
}