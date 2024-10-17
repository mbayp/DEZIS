package com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentServiceScreenBinding
import com.dezis.geeks_dezis.presentation.fragments.calendar.CalendarFragment
import com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment.view_model.ServiceModel
import com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment.view_model.ServicePagerAdapter
import com.dezis.geeks_dezis.presentation.fragments.serviceScreenFragment.view_model.ServiceScreenViewModel
import com.dezis.geeks_dezis.presentation.fragments.viewBinding

class ServiceScreenFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_service_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)

        val services = listOf(
            ServiceModel(R.drawable.image1, "Дезинсекция", "Дезинсекция — это профессиональное уничтожение любых видов вредителей," +
                    " таких как тараканы, муравьи, клопы, моль и другие насекомые. Мы применяем современные и безопасные методы обработки, чтобы " +
                    "гарантировать полное избавление от вредителей в жилых и коммерческих помещениях. Наши специалисты проводят тщательный анализ " +
                    "для выявления очагов заражения и используют эффективные средства для устранения проблемы."),
            ServiceModel(R.drawable.image1, "Дератизация", "Описание дератизации..."),
            ServiceModel(R.drawable.image1, "Дезинфекция", "Описание дезинфекции...")
        )

        viewPager.adapter = ServicePagerAdapter(services)
    }
}