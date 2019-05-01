package eu.eitdigital.hcid.sustainabilitycoach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment(val homeActivity: HomeActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.home_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        start_button.setOnClickListener {
            homeActivity.openExploreActivity()
        }
    }

    companion object {
        fun newInstance(homeActivity: HomeActivity): HomeFragment = HomeFragment(homeActivity)
    }
}