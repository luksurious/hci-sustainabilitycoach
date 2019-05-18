package eu.eitdigital.hcid.sustainabilitycoach.explore

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout

import eu.eitdigital.hcid.sustainabilitycoach.R
import kotlinx.android.synthetic.main.fragment_explore_details_dialog.*


class ExploreDetailsDialog : DialogFragment() {

    private lateinit var demoCollectionPagerAdapter: DemoCollectionPagerAdapter
    private lateinit var viewPager: ViewPager

    companion object {
        val TAG = "ExploreDetailsDialog"

        fun newInstance() = ExploreDetailsDialog()
    }

    private var listener: ExploreFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_explore_details_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageClose.setOnClickListener {
            dialog?.dismiss()
        }

        button_start_habit.setOnClickListener {
            listener?.showPlanningDialog()
            dialog?.hide()
        }

        demoCollectionPagerAdapter = DemoCollectionPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager_details_habit)
        viewPager.adapter = demoCollectionPagerAdapter

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout_details_habit)
        tabLayout.setupWithViewPager(viewPager)


    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            (dialog as Dialog).window?.setLayout(width, height)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ExploreFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement ExploreFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}


class DemoCollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return "Details"
            else -> {
                return "tips"
            }
        }

    }


    override fun getItem(i: Int): Fragment {
        when (i) {
            0 -> return DetailsDescription()
            else -> {
                return DetailsTips()
            }
        }
    }
}


class DetailsDescription : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.explore_details_description, container, false)
}

class DetailsTips : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.explore_details_tips, container, false)

}
