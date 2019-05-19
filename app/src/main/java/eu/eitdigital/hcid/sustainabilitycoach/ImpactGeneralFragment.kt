package eu.eitdigital.hcid.sustainabilitycoach

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.impact_general_co2.*
import kotlinx.android.synthetic.main.impact_general_forest.*
import kotlinx.android.synthetic.main.impact_general_water.*


class ImpactGeneralFragment: Fragment() {
    private lateinit var demoCollectionPagerAdapter: GeneralCollectionPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.impact_general, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        demoCollectionPagerAdapter = GeneralCollectionPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = demoCollectionPagerAdapter

        val wormDotsIndicator:WormDotsIndicator = view.findViewById(R.id.worm_dots_indicator)
        wormDotsIndicator.setViewPager(viewPager)
    }
}

class GeneralCollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int  = 3

    override fun getPageTitle(position: Int): CharSequence {
        when(position){
            0 -> return "General"
            1 -> return "Statistics"
            else -> {
                return "History"
            }
        }
    }

    override fun getItem(i: Int): Fragment {
        when(i){
            0 -> return ImpactCO2Fragment()
            1 -> return ImpactForestFragment()
            else -> {
                return ImpactWaterFragment()
            }
        }
    }
}

class ImpactCO2Fragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.impact_general_co2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        co_info.setOnClickListener {
            val dialog = ImpactCO2Explanation.newInstance()

            val ft = activity?.supportFragmentManager?.beginTransaction()
            if (ft != null) {
                dialog.show(ft, ImpactCO2Explanation.TAG)
            }
        }
    }
}

class ImpactForestFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.impact_general_forest, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        forest_info.setOnClickListener {
            val dialog = ImpactForestExplanation.newInstance()

            val ft = activity?.supportFragmentManager?.beginTransaction()
            if (ft != null) {
                dialog.show(ft, ImpactForestExplanation.TAG)
            }
        }
    }
}

class ImpactWaterFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.impact_general_water, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        water_info.setOnClickListener {
            val dialog = ImpactWaterExplanation.newInstance()

            val ft = activity?.supportFragmentManager?.beginTransaction()
            if (ft != null) {
                dialog.show(ft, ImpactWaterExplanation.TAG)
            }
        }
    }
}

