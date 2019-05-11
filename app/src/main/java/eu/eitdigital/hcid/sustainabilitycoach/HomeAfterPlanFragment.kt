package eu.eitdigital.hcid.sustainabilitycoach

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import eu.eitdigital.hcid.sustainabilitycoach.explore.ExploreActivity
import kotlinx.android.synthetic.main.home_fragment.*

class HomeAfterPlanFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.home_after_plan_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun newInstance(): HomeAfterPlanFragment = HomeAfterPlanFragment()
    }
}