package com.dezis.geeks_dezis.presentation.fragments.authorization

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentCodeVerificationBinding

class CodeVerificationFragment : BaseFragment<FragmentCodeVerificationBinding,CodeVerificationViewModel>(R.layout.fragment_code_verification) {
    override val binding: FragmentCodeVerificationBinding by viewBinding(FragmentCodeVerificationBinding::bind)
    override val viewModel: CodeVerificationViewModel by viewModels()

    override fun constructorListeners() {
        binding.etCode.addTextChangedListener { validateFields() }

        binding.btnContinue.setOnClickListener{
            val enteredCode = binding.etCode.text.toString()
            val correctCode = "1234"

            if (validateInputs()&&enteredCode == correctCode){
                findNavController().navigate(R.id.action_codeVerificationFragment_to_waitingFragment)

            }else {
                binding.tilCode.error = "Код введен неверно"
            }
        }
        setupClickableText()

    }
    private fun validateFields() {
        val isAllFieldsValid =
            binding.etCode.text.toString().isNotEmpty()
        if (isAllFieldsValid) {
            binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
        } else {
            binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
        }
    }
    private fun validateInputs():Boolean{
        var isValid = true
        if (binding.etCode.text.toString().isEmpty()){
            binding.tilCode.error = "Код введен неверно"
            isValid = false
        }else{
            binding.tilCode.error = null
        }
        if (!isValid) {
            setErrorBorderColor()
        } else {
            resetBorderColor()
        }
        return isValid
    }
    private fun setupClickableText() {
        val termsTextView = binding.termsOfSale
        val spannableString = SpannableString(
            getString(R.string.policy_txt)
        )

        val salesTermsClickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val salesTerms = "https://youtu.be/Ca8YSrtxI3s?si=7k_pwqneUTWDR-cD"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(salesTerms))
                startActivity(intent)
            }
        }

        val privacyPolicyClickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val privacyPolicy = "https://youtu.be/Ca8YSrtxI3s?si=7k_pwqneUTWDR-cD"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(privacyPolicy))
                startActivity(intent)
            }
        }

        spannableString.setSpan(salesTermsClickable, 63, 80, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.blue)), 63, 80, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannableString.setSpan(privacyPolicyClickable, 100,spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.blue)), 100, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        termsTextView.text = spannableString
        termsTextView.movementMethod = LinkMovementMethod.getInstance()
        termsTextView.highlightColor = Color.TRANSPARENT
    }


    private fun setErrorBorderColor() {
        binding.tilCode.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
    }

    private fun resetBorderColor() {
        binding.tilCode.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
    }






}
