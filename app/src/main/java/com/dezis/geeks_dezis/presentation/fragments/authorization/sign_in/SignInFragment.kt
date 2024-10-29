package com.dezis.geeks_dezis.presentation.fragments.authorization.sign_in

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.data.remote.apiservice.UserApiService
import com.dezis.geeks_dezis.data.remote.model.LoginRequest
import com.dezis.geeks_dezis.databinding.FragmentSignInBinding
import com.dezis.geeks_dezis.presentation.fragments.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding,SignInViewModel>(R.layout.fragment_sign_in){

    override val binding: FragmentSignInBinding by viewBinding(FragmentSignInBinding::bind)
    override val viewModel: SignInViewModel by viewModels()

    //private val constantEmail = "alohadance@gmail.com"
    //private val constantPassword = "aloha12345"

    @Inject
    lateinit var userApiService: UserApiService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun constructorListeners() {
        binding.etLogIn.addTextChangedListener { validateFields() }
        binding.etPasswordl.addTextChangedListener { validateFields() }

        binding.btnContinue.setOnClickListener {
            if (validateInputs()) {
                val email = binding.etLogIn.text.toString()
                val password = binding.etPasswordl.text.toString()
                loginUser(email, password)
            }
        }
        setupClickableText()
    }

    private fun loginUser(email: String, password: String) {
        val loginRequest = LoginRequest(email = email, password = password)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = userApiService.loginUser(loginRequest)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        loginResponse?.let {
                            Toast.makeText(requireContext(), "Вход выполнен успешно", Toast.LENGTH_SHORT).show()
                            val action = SignInFragmentDirections.actionSignInFragmentToHomeFragment(email = email)
                            findNavController().navigate(action)
                        }
                    } else {
                        Toast.makeText(requireContext(), "Ошибка входа: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Ошибка сети: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateFields() {
        val isAllFieldsValid =
            binding.etLogIn.text.toString().isNotEmpty() &&
                    binding.etPasswordl.text.toString().isNotEmpty()
        binding.btnContinue.setBackgroundColor(
            if (isAllFieldsValid)
                ContextCompat.getColor(requireContext(), R.color.grey_dark)
            else
                ContextCompat.getColor(requireContext(), R.color.true_gray)
        )
    }

    private fun setupClickableText() {
        val termsTextView = binding.termsOfSale
        val spannableString = SpannableString(getString(R.string.policy_txt))

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
        spannableString.setSpan(privacyPolicyClickable, 100, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.blue)), 100, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        termsTextView.text = spannableString
        termsTextView.movementMethod = LinkMovementMethod.getInstance()
        termsTextView.highlightColor = Color.TRANSPARENT
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        if (binding.etLogIn.text.toString().isEmpty()) {
            binding.tilLogIn.error = "Введите email"
            isValid = false
        } else {
            binding.tilLogIn.error = null
        }

        if (binding.etPasswordl.text.toString().isEmpty()) {
            binding.tilPassword.error = "Введите пароль"
            isValid = false
        } else {
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