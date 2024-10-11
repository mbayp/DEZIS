package com.dezis.geeks_dezis.presentation.fragments.profile.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>(
    R.layout.fragment_settings
) {

    override val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    override val viewModel: SettingsViewModel by viewModels()

    override fun init() {
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            toggleDarkMode(isChecked)
        }

        binding.languageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedLanguage = parent?.getItemAtPosition(position).toString()
                    showLanguageSelectionToast(selectedLanguage)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        binding.tvDeleteAccount.setOnClickListener {
            showDeleteDialog()
        }
    }

    private fun showLanguageSelectionToast(selectedLanguage: String) {
        Toast.makeText(
            requireContext(),
            "Выбран язык: $selectedLanguage",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun toggleDarkMode(isChecked: Boolean) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("dark_mode", isChecked)
        editor.apply()

        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun showDeleteDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Удаление аккаунта")
        builder.setMessage("Вы уверены, что хотите удалить свой аккаунт?")

        builder.setPositiveButton("Да") { _, _ ->
            deleteAccount()
        }

        builder.setNegativeButton("Нет") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun deleteAccount() {
        Toast.makeText(requireContext(), "Аккаунт удален", Toast.LENGTH_SHORT).show()
    }
}