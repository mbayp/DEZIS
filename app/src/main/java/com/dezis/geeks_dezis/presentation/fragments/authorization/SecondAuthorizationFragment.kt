package com.dezis.geeks_dezis.presentation.fragments.authorization

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.data.remote.apiservice.UserApiService
import com.dezis.geeks_dezis.data.remote.model.UserRegisterDto
import com.dezis.geeks_dezis.databinding.FragmentSecondAuthorizationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException

@AndroidEntryPoint
class SecondAuthorizationFragment : BaseFragment<FragmentSecondAuthorizationBinding, SecondAuthorizationViewModel>(R.layout.fragment_second_authorization) {

    override val binding: FragmentSecondAuthorizationBinding by viewBinding(FragmentSecondAuthorizationBinding::bind)
    override val viewModel: SecondAuthorizationViewModel by viewModels()
    private val args: SecondAuthorizationFragmentArgs by navArgs() // Получение аргументов из предыдущего фрагмента

    @Inject
    lateinit var userApiService: UserApiService

    override fun constructorListeners() {
        binding.etNumFlat.addTextChangedListener { validateFields() }
        binding.etAddress.addTextChangedListener { validateFields() }

        binding.btnRegister.setOnClickListener {
            if (validateInputs()) {
                val address = binding.etAddress.text.toString()
                val apartmentNumber = binding.etNumFlat.text.toString()

                val userRegistrationRequest = UserRegisterDto(
                    username = args.userName,
                    email = args.email,
                    apartmentNumber = apartmentNumber,
                    address = address,
                    password = args.password
                )
                sendRegistrationRequest(userRegistrationRequest)
            }
        }

        setupClickableText()
    }

    private fun sendRegistrationRequest(userRegisterDto: UserRegisterDto) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = userApiService.registerUser(userRegisterDto)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        findNavController().navigate(
                            SecondAuthorizationFragmentDirections
                                .actionSecondAuthorizationFragmentToCodeVerificationFragment(email = args.email)
                        )
                    } else {
                        handleError(response.errorBody())
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    handleError(e.response()?.errorBody())
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showToast("An error occurred: ${e.message}")
                }
            }
        }
    }

    private fun handleError(errorBody: ResponseBody?) {
        showToast("Error: ${errorBody?.string() ?: "Unknown error"}")
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
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

    private fun validateFields() {
        val isAllFieldsValid =
            binding.etAddress.text.toString().isNotEmpty() &&
                    binding.etNumFlat.text.toString().isNotEmpty()
        if (isAllFieldsValid) {
            binding.btnRegister.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_dark))
        } else {
            binding.btnRegister.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.true_gray))
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        if (binding.etAddress.text.toString().isEmpty()) {
            binding.tilAddress.error = " "
            isValid = false
        } else {
            binding.tilAddress.error = null
        }

        if (binding.etNumFlat.text.toString().isEmpty()) {
            binding.tilNumFlat.error = " "
            isValid = false
        } else {
            binding.tilNumFlat.error = null
        }
        if (!isValid) {
            setErrorBorderColor()
        } else {
            resetBorderColor()
        }
        return isValid
    }

    private fun setErrorBorderColor() {
        binding.tilAddress.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
        binding.tilNumFlat.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
    }

    private fun resetBorderColor() {
        binding.tilAddress.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
        binding.tilNumFlat.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
    }
}
