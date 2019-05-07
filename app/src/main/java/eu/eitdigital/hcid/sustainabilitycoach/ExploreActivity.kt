package eu.eitdigital.hcid.sustainabilitycoach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_explore.*

class ExploreActivity : AppCompatActivity(),
    ExploreFragmentInteractionListener {

    var filters: ExploreFilters = ExploreFilters("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        setSupportActionBar(appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showCategorySelection()
    }

    private fun showCategorySelection() {
        openFragment(ExploreCategoryFragment.newInstance(null))
    }
    private fun showDifficultySelection() {
        openFragment(ExploreDifficultyFragment.newInstance(null, null))
    }
    private fun showResultsSelection() {
        openFragment(ExploreResultsFragment.newInstance(filters.category, filters.difficulty))
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
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

    override fun setCategorySelection(category: String) {
        filters.category = category
    }
    override fun setDifficultySelection(difficulty: String) {
        filters.difficulty = difficulty
    }
}
