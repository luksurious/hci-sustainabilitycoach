package eu.eitdigital.hcid.sustainabilitycoach.explore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import eu.eitdigital.hcid.sustainabilitycoach.MainActivity
import eu.eitdigital.hcid.sustainabilitycoach.R
import kotlinx.android.synthetic.main.fragment_explore_results.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.plan_notification_fragment.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "category"
private const val ARG_PARAM2 = "difficulty"

class ExploreResultsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var category: String? = null
    private var difficulty: String? = null
    private var listener: ExploreFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(ARG_PARAM1)
            difficulty = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonChangeFilters.setOnClickListener {
            listener?.goBackToCategorySelection()
        }
        /*
        MeatCard.setOnClickListener{
            (activity as MainActivity).openDetailsDialog()
        }
        */
        /* THE ONE WORKING AS EXPLORE DETAILS DIALOG AS AN ACTIVITY
        MeatCard.setOnClickListener {
            val intent = Intent(activity, ExploreDetailsDialog::class.java)
            startActivity(intent)
        }
        */

        MeatCard.setOnClickListener {
            listener?.showDetailsDialog()
        }

        /*
        MeatCard.setOnClickListener {
            fun onClick(v: View) {
                // When button is clicked, call up to owning activity.
                (activity as ExploreDetailsDialog).getDialog()
            }
        }
        */
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

    companion object {
        val STEP = "Results"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param category Parameter 1.
         * @param difficulty Parameter 2.
         * @return A new instance of fragment ExploreCategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(category: String?, difficulty: String?) =
            ExploreResultsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, category)
                    putString(ARG_PARAM2, difficulty)
                }
            }
    }
}
