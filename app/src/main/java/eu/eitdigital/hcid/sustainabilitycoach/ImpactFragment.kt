package eu.eitdigital.hcid.sustainabilitycoach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.google.android.material.tabs.TabLayout
import java.util.*
import android.R.attr.entries
import android.graphics.Color
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.XAxis







class ImpactFragment: Fragment() {
    private lateinit var demoCollectionPagerAdapter: DemoCollectionPagerAdapter
    private lateinit var viewPager: ViewPager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.impact_fragment, container, false)

    companion object {
        fun newInstance(): ImpactFragment = ImpactFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        demoCollectionPagerAdapter = DemoCollectionPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = demoCollectionPagerAdapter

        val tabLayout:TabLayout = view.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }

}

class DemoCollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int  = 3

    override fun getPageTitle(position: Int): CharSequence {
        when(position){
            0 -> return "Savings"
            1 -> return "Success Rate"
            else -> {
                return "History"
            }
        }
    }

    override fun getItem(i: Int): Fragment {
        when(i){
            0 -> return ImpactGeneralFragment()
            1 -> return ImpactStatisticsFragment()
            else -> {
                return ImpactHistoryFragment()
            }
        }
    }
}


class ImpactStatisticsFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.impact_statistics, container, false)
}

class ImpactHistoryFragment: Fragment() {
    private lateinit var chart: LineChart

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.impact_history, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        chart = view.findViewById(R.id.chart)


        chart.setDrawGridBackground(false)
        chart.setMaxHighlightDistance(300F)
        chart.getDescription().setEnabled(false)
        chart.getLegend().setEnabled(false)

        val y = chart.axisLeft
        y.setLabelCount(6, false)
        y.textColor = Color.WHITE
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        y.setDrawGridLines(false)
        y.axisLineColor = Color.WHITE

        val entries = ArrayList<Entry>()
        val values:Array<Float> = arrayOf(0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F)
        for (i in 0..14) {
            entries.add(Entry((i).toFloat(), values[i]))
        }
        val dataSet = LineDataSet(entries, "Volume of water saved")
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER)
        dataSet.setCubicIntensity(0.2f)
        dataSet.setDrawFilled(true)
        dataSet.setDrawCircles(false)
        dataSet.setLineWidth(1.8f)
        dataSet.setCircleRadius(4f)
        dataSet.setCircleColor(Color.WHITE)
        dataSet.setDrawValues(false)
        val lineData = LineData(dataSet)
        chart.setData(lineData)
        chart.invalidate()
    }
}
