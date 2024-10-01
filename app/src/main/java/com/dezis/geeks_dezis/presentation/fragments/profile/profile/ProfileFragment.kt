package com.dezis.geeks_dezis.presentation.fragments.profile.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUserData()
        setUpListener()
    }

    private fun setUpListener() {
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.imgEditName.setOnClickListener {
            showEditNameDialog()
        }

        binding.imgEditPhone.setOnClickListener {
            showEditPhoneDialog()
        }

        binding.btnSave.setOnClickListener {
            saveUserData()
        }
    }

    private fun loadUserData() {
        val sharedPreferences =
            requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("user_name", "Ваше имя")
        val savedPhone = sharedPreferences.getString("user_phone", "Ваш номер")

        binding.tvUserName.text = savedName
        binding.tvPhoneNumber.text = savedPhone
    }

    private fun showEditNameDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val editText = EditText(requireContext())
        editText.hint = "Введите новое имя"
        editText.setText(binding.tvUserName.text)

        builder.setTitle("Редактировать имя")
        builder.setView(editText)

        builder.setPositiveButton("Сохранить") { dialog, which ->
            val newName = editText.text.toString()
            binding.tvUserName.text = newName
        }

        builder.setNegativeButton("Отмена") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun showEditPhoneDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val editText = EditText(requireContext())
        editText.hint = "Введите новый телефон"
        editText.setText(binding.tvPhoneNumber.text)

        builder.setTitle("Редактировать телефон")
        builder.setView(editText)

        builder.setPositiveButton("Сохранить") { dialog, which ->
            val newPhone = editText.text.toString()
            binding.tvPhoneNumber.text = newPhone
        }

        builder.setNegativeButton("Отмена") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun saveUserData() {
        val sharedPreferences =
            requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("user_name", binding.tvUserName.text.toString())
        editor.putString("user_phone", binding.tvPhoneNumber.text.toString())
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}