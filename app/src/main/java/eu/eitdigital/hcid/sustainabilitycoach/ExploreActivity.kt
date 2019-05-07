package eu.eitdigital.hcid.sustainabilitycoach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.NavUtils
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_explore.*

class ExploreActivity : AppCompatActivity(), ExploreFragmentInteractionListener {

    var filters: ExploreFilters = ExploreFilters("", "")

    var state: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        setSupportActionBar(appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showCategorySelection(false)
        state = ExploreCategoryFragment.STEP
    }

    private fun showCategorySelection(addToStack: Boolean = true) {
        openFragment(ExploreCategoryFragment.newInstance(filters.category), addToStack)
    }
    private fun showDifficultySelection(addToStack: Boolean = true) {
        state = ExploreDifficultyFragment.STEP
        openFragment(ExploreDifficultyFragment.newInstance(filters.difficulty), addToStack)
    }
    private fun showResultsSelection(addToStack: Boolean = true) {
        state = ExploreResultsFragment.STEP
        openFragment(ExploreResultsFragment.newInstance(filters.category, filters.difficulty), addToStack)
    }

    private fun openFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun onFragmentInteraction(origin: String) {
        when (origin) {
            ExploreCategoryFragment.STEP -> showDifficultySelection()
            ExploreDifficultyFragment.STEP -> showResultsSelection()
            ExploreResultsFragment.STEP -> {
                Toast.makeText(applicationContext, "Test", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun goBackToCategorySelection(origin: String) {
        when (origin) {
            ExploreResultsFragment.STEP -> showCategorySelection()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setCategorySelection(category: String) {
        filters.category = category
    }
    override fun setDifficultySelection(difficulty: String) {
        filters.difficulty = difficulty
    }
}
