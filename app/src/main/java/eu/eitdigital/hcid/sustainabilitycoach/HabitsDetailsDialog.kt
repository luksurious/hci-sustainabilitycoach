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
import eu.eitdigital.hcid.sustainabilitycoach.HomeInteractionListener

import eu.eitdigital.hcid.sustainabilitycoach.R
import kotlinx.android.synthetic.main.fragment_explore_details_dialog.*


class HabitsDetailsDialog (number: Int) : DialogFragment() {

    private lateinit var demoCollectionPagerAdapter: HomePagerAdapter
    private lateinit var viewPager: ViewPager
    private var numberHabitsScreen: Int = number
    private var listener: HomeInteractionListener? = null


    companion object {
        val TAG = "HabitsDetailsDialog"
        fun newInstance(number: Int) = HabitsDetailsDialog(number)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_explore_details_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogToolbar?.setNavigationIcon(R.drawable.ic_close_black_24dp)
        dialogToolbar?.navigationIcon?.setTint(resources.getColor(R.color.grey, null));
        dialogToolbar?.setNavigationOnClickListener {
            dialog?.dismiss()
        }

        if(numberHabitsScreen == 1){
            button_start_habit.setText("VIEW IMPACT")

            button_start_habit.setOnClickListener {
                listener?.openImpactScreen()
                dialog?.hide()
            }
        }

        else if (numberHabitsScreen == 2){
            button_start_habit.setText("ENTER RESULTS")

            button_start_habit.setOnClickListener {
                listener?.enterResultsScreen()
                dialog?.hide()
            }
        }

        demoCollectionPagerAdapter = HomePagerAdapter(childFragmentManager)
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
        if (context is HomeInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement HomeInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}


class HomePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

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
            0 -> return DetailsDescription2()
            else -> {
                return DetailsTips2()
            }
        }
    }
}


class DetailsDescription2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.explore_details_description, container, false)
}

class DetailsTips2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.explore_details_tips, container, false)

}
