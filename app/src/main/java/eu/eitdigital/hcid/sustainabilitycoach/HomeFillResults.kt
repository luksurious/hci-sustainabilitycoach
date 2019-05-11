package eu.eitdigital.hcid.sustainabilitycoach

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_explore_category.*
import kotlinx.android.synthetic.main.fragment_home_fill_results.*
import kotlinx.android.synthetic.main.home_fragment.*

private const val ARG_PARAM1 = "filters"
class HomeFillResults: Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var listener: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fill_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goButton.setOnClickListener {
            (activity as MainActivity).openFillResults()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
            listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        val STEP = "Category"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreCategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String?) =
            HomeFillResults().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
