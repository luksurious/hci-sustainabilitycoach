package eu.eitdigital.hcid.sustainabilitycoach.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import eu.eitdigital.hcid.sustainabilitycoach.MainActivity
import eu.eitdigital.hcid.sustainabilitycoach.R
import kotlinx.android.synthetic.main.home_after_success_fragment.*

class HomeAfterSuccessFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.home_after_success_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_impact.setOnClickListener {
            (activity as MainActivity).showImpactTab()
        }
        MeatCard.setOnClickListener { (activity as MainActivity).showUnsupportedActionMessage() }
    }

    companion object {
        fun newInstance(): HomeAfterSuccessFragment =
            HomeAfterSuccessFragment()
    }
}