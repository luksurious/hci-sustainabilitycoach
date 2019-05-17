package eu.eitdigital.hcid.sustainabilitycoach.explore

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import eu.eitdigital.hcid.sustainabilitycoach.MainActivity
import eu.eitdigital.hcid.sustainabilitycoach.R
import eu.eitdigital.hcid.sustainabilitycoach.model.DummyDataModel
import eu.eitdigital.hcid.sustainabilitycoach.model.ExploreFilters
import eu.eitdigital.hcid.sustainabilitycoach.model.PREF_NAME
import eu.eitdigital.hcid.sustainabilitycoach.plan.PlanActivity
import eu.eitdigital.hcid.sustainabilitycoach.plan.ui.PlanCoachInfoDialogFragment
import kotlinx.android.synthetic.main.activity_explore.*

class ExploreActivity : AppCompatActivity(),
    ExploreFragmentInteractionListener {

    var filters: ExploreFilters =
        ExploreFilters("", "")

    var state: String = ""

    private lateinit var preferences: DummyDataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        setSupportActionBar(appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        preferences = DummyDataModel(getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE));

        showCategorySelection(false)
        state = ExploreCategoryFragment.STEP
    }

    private fun showCategorySelection(addToStack: Boolean = true) {
        openFragment(
            ExploreCategoryFragment.newInstance(
                filters.category
            ), addToStack)
    }
    private fun showDifficultySelection(addToStack: Boolean = true) {
        state = ExploreDifficultyFragment.STEP
        openFragment(
            ExploreDifficultyFragment.newInstance(
                filters.difficulty
            ), addToStack)
    }
    private fun showResultsSelection(addToStack: Boolean = true) {
        state = ExploreResultsFragment.STEP
        openFragment(
            ExploreResultsFragment.newInstance(
                filters.category,
                filters.difficulty
            ), addToStack)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.explore_plan_options, menu)
        return true
    }

    override fun goBackToCategorySelection() {
        // TODO: does this work in any scenario/flow;
        //  is there a state where you enter results without coming from the selection process?
        // remove transition of results -> difficulty selection
        supportFragmentManager.popBackStack();
        // remove transition of difficulty -> category selection
        supportFragmentManager.popBackStack();
        showCategorySelection(false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()

                return true
            }
            R.id.explore_plan_cancel -> {
                finish()
//                val intent = Intent(this, MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                startActivity(intent)
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

    override fun showUnsupportedActionMessage() {
        Snackbar.make(fragment_container, "This selection is currently not supported!", Snackbar.LENGTH_LONG)
            .show()
    }

    override fun showDetailsDialog() {
        val dialog = ExploreDetailsDialog.newInstance()

        val ft = supportFragmentManager.beginTransaction()
        dialog.show(ft, ExploreDetailsDialog.TAG)
    }

    override fun showPlanningDialog() {
        val dialog = DetailsPlanningDialog.newInstance()

        dialog.showNow(supportFragmentManager, DetailsPlanningDialog.TAG)
    }

    override fun startPlanning() {
        preferences.state = DummyDataModel.States.ACTIVE_PLANNED

        finish()
        val intent = Intent(this, PlanActivity::class.java)
        startActivity(intent)
    }

    override fun finishExploreActive() {
        preferences.state = DummyDataModel.States.ACTIVE_UNPLANNED

        finish()
    }
}
