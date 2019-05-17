package eu.eitdigital.hcid.sustainabilitycoach.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import eu.eitdigital.hcid.sustainabilitycoach.MainActivity
import eu.eitdigital.hcid.sustainabilitycoach.R
import kotlinx.android.synthetic.main.home_after_weeks.*

class HomeAfterWeeksFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.home_after_weeks, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_impact.setOnClickListener { (activity as MainActivity).showImpactTab() }
        button3.setOnClickListener { (activity as MainActivity).showUnsupportedActionMessage() }

        MeatCard.setOnClickListener { (activity as MainActivity).showUnsupportedActionMessage() }
        RecycleCard.setOnClickListener { (activity as MainActivity).showUnsupportedActionMessage() }
    }

    companion object {
        fun newInstance(): HomeAfterWeeksFragment =
            HomeAfterWeeksFragment()
    }
}