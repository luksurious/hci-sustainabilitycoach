package eu.eitdigital.hcid.sustainabilitycoach.explore

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import eu.eitdigital.hcid.sustainabilitycoach.R
import eu.eitdigital.hcid.sustainabilitycoach.model.Category
import kotlinx.android.synthetic.main.fragment_explore_category.*

private const val ARG_CATEGORY = "category"

class ExploreCategoryFragment : Fragment() {
    private var activeCategories: ArrayList<String> = ArrayList()
    private var listener: ExploreFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val catArgument = it.getString(ARG_CATEGORY)
            if (catArgument != null && catArgument != "") {
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
            if (activeCategories.size != 1 || !activeCategories.contains(Category.FOOD.category)) {
                listener?.showUnsupportedActionMessage()

                return@setOnClickListener
            }

            listener?.setCategorySelection(activeCategories.joinToString())
            listener?.onFragmentInteraction(STEP)
        }

        if (activeCategories.size == 0) {
            next_button.isEnabled = false
        }

        skip_button.setOnClickListener { listener?.showUnsupportedActionMessage() }
    }

    private fun toggleCategorySelection(category: Category, card: MaterialCardView) {
        card.toggle()

        if (card.isChecked) {
            activeCategories.add(category.category)

            next_button.isEnabled = true
        } else {
            activeCategories.remove(category.category)

            if (activeCategories.size == 0) {
                next_button.isEnabled = false
            }
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

        @JvmStatic
        fun newInstance(category: String?) =
            ExploreCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY, category)
                }
            }
    }
}
