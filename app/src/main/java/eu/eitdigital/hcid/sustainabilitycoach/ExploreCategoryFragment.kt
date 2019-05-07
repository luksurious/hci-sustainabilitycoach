package eu.eitdigital.hcid.sustainabilitycoach

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.fragment_explore_category.*

private const val ARG_CATEGORY = "category"

class ExploreCategoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var activeCategories: ArrayList<String> = ArrayList()
    private var listener: ExploreFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val catArgument = it.getString(ARG_CATEGORY)
            if (catArgument != null) {
                activeCategories.addAll(catArgument.split(","))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        card_category_food.setOnClickListener { toggleCategorySelection(Category.FOOD, it as MaterialCardView) }
        card_category_transportation.setOnClickListener { toggleCategorySelection(Category.TRANSPORTATION, it as MaterialCardView) }
        card_category_household.setOnClickListener { toggleCategorySelection(Category.HOUSEHOLD, it as MaterialCardView) }
        card_category_consumption.setOnClickListener { toggleCategorySelection(Category.CONSUMPTION, it as MaterialCardView) }

        activeCategories.forEach {
            when (it) {
                Category.FOOD.category -> card_category_food.isChecked = true
                Category.TRANSPORTATION.category -> card_category_transportation.isChecked = true
                Category.HOUSEHOLD.category -> card_category_household.isChecked = true
                Category.CONSUMPTION.category -> card_category_consumption.isChecked = true
            }
        }

        next_button.setOnClickListener {
            listener?.setCategorySelection(activeCategories.toString())
            listener?.onFragmentInteraction(STEP)
        }
    }

    fun toggleCategorySelection(category: Category, card: MaterialCardView) {
        card.toggle()

        if (card.isChecked) {
            activeCategories.add(category.category)
        } else {
            activeCategories.remove(category.category)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ExploreFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
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
         * @param category Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreCategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(category: String?) =
            ExploreCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY, category)
                }
            }
    }
}
