package com.dezis.geeks_dezis.core.common

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

object Extensions {

    fun Fragment.showToast(message: String) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun ImageView.loadImage(url: String) {
        Glide.with(this).load(url).into(this)
    }

    inline fun <T> Fragment.observeData(
        flow: Flow<T>,
        lifecycleOwner: LifecycleOwner = viewLifecycleOwner,
        state: Lifecycle.State = Lifecycle.State.STARTED,
        crossinline block: (T) -> Unit,
    ) = lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(state) {
            flow.collect { data ->
                block(data)
            }
        }
    }

}