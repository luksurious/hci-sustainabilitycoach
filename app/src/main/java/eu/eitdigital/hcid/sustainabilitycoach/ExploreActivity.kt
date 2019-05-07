package eu.eitdigital.hcid.sustainabilitycoach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.NavUtils
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_explore.*

class ExploreActivity : AppCompatActivity(),
    ExploreFragmentInteractionListener {

    var filters: ExploreFilters = ExploreFilters("", "")

    var state: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        setSupportActionBar(appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showCategorySelection()
        state = ExploreCategoryFragment.STEP
    }

    private fun showCategorySelection() {
        openFragment(ExploreCategoryFragment.newInstance(null))
    }
    private fun showDifficultySelection() {
        state = ExploreDifficultyFragment.STEP
        openFragment(ExploreDifficultyFragment.newInstance(null, null))
    }
    private fun showResultsSelection() {
        state = ExploreResultsFragment.STEP
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                when (state) {
                    ExploreCategoryFragment.STEP -> NavUtils.navigateUpFromSameTask(this)
                    ExploreDifficultyFragment.STEP -> showCategorySelection()
                    ExploreResultsFragment.STEP -> showDifficultySelection()
                    else -> return false
                }

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
