package com.dezis.geeks_dezis.presentation.fragments.profile.aboutus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}