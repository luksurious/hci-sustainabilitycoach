package eu.eitdigital.hcid.sustainabilitycoach

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import eu.eitdigital.hcid.sustainabilitycoach.explore.ExploreActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.home_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        start_button.setOnClickListener {
            val intent = Intent(activity, ExploreActivity::class.java)
            startActivity(intent)
        }
        fill_results.setOnClickListener {
            (activity as HomeActivity).openFillResults()
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}