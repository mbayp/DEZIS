package com.dezis.geeks_dezis.presentation.fragments.profile.aboutus

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentAboutUsBinding
import com.dezis.geeks_dezis.presentation.fragments.viewBinding

class AboutUsFragment : BaseFragment<FragmentAboutUsBinding, AboutUsViewModel>(
    R.layout.fragment_about_us
) {

    override val binding: FragmentAboutUsBinding by viewBinding(FragmentAboutUsBinding::bind)

    override val viewModel: AboutUsViewModel by viewModels()

    override fun init() {
        setUpListener()
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun setUpListener() = with(binding) {
        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        imgTiktok.setOnClickListener {
            openUrl("https://www.tiktok.com")
        }
        imgWhatsapp.setOnClickListener {
            openUrl("https://www.whatsapp.com")
        }
        imgInstagram.setOnClickListener {
            openUrl("https://www.instagram.com")
        }
        imgTelegram.setOnClickListener {
            openUrl("https://telegram.org")
        }
    }

}