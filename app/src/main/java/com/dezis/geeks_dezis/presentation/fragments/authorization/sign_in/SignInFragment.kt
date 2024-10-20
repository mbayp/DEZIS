package com.dezis.geeks_dezis.presentation.fragments.authorization.sign_in

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentSignInBinding


class SignInFragment : BaseFragment<FragmentSignInBinding,SignInViewModel>(R.layout.fragment_sign_in){
    override val binding: FragmentSignInBinding by viewBinding(FragmentSignInBinding::bind)
    override val viewModel: SignInViewModel by viewModels()

    override fun constructorListeners() {
        binding.etLogIn.addTextChangedListener { validateFields() }
        binding.etPasswordl.addTextChangedListener { validateFields() }

        binding.btnContinue.setOnClickListener{
            if (validateInputs()){
                findNavController().navigate(R.id.action_signInFragment_to_codeVerificationFragment)
            }
        }
        setupClickableText()

    }
    private fun validateFields() {
        val isAllFieldsValid =
            binding.etLogIn.text.toString().isNotEmpty() &&
            binding.etPasswordl.text.toString().isNotEmpty()
        if (isAllFieldsValid) {
            binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue))
        } else {
            binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
        }
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



    private fun validateInputs():Boolean{
        var isValid = true
        if (binding.etLogIn.text.toString().isEmpty()){
            binding.tilLogIn.error = " "
            isValid = false
        }else{
            binding.tilLogIn.error = null
        }
        if (binding.etPasswordl.text.toString().isEmpty()){
            binding.tilPassword.error = " "
            isValid = false
        }else{
            binding.tilPassword.error = null
        }
        if (!isValid) {
            setErrorBorderColor()
        } else {
            resetBorderColor()
        }
        return isValid

    }
    private fun setErrorBorderColor() {
        binding.tilLogIn.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
        binding.tilPassword.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
    }

    private fun resetBorderColor() {
        binding.tilLogIn.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
        binding.tilPassword.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
    }

}