package com.dezis.geeks_dezis.presentation.activity

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.common.PreferenceHelper
import com.dezis.geeks_dezis.core.common.SharedPreferencesKeys.APP_ACTIVITY
import com.dezis.geeks_dezis.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController

    @Inject
    lateinit var sharedPreferences: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        APP_ACTIVITY = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        if (resources.configuration.smallestScreenWidthDp < 600) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_controller) as NavHostFragment
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

    @SuppressLint("SwitchIntDef")
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {}
            Configuration.ORIENTATION_PORTRAIT -> {}
        }
    }

    private fun handleWindowInsets() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = android.graphics.Color.TRANSPARENT
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, systemBarsInsets.top, 0, 0)
            insets
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
                binding.bottomNav.visibility = View.GONE
                binding.adminBottomNav.visibility = View.GONE
            }
        }
    }

    private fun initUserBottomNav() {
        binding.bottomNav.setOnItemSelectedListener { menuItem ->
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
        binding.adminBottomNav.setOnItemSelectedListener { menuItem ->
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