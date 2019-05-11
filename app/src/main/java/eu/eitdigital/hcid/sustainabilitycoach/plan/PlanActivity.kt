package eu.eitdigital.hcid.sustainabilitycoach.plan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import eu.eitdigital.hcid.sustainabilitycoach.MainActivity
import eu.eitdigital.hcid.sustainabilitycoach.R
import eu.eitdigital.hcid.sustainabilitycoach.model.DummyDataModel
import eu.eitdigital.hcid.sustainabilitycoach.model.PREF_NAME
import eu.eitdigital.hcid.sustainabilitycoach.plan.ui.PlanCoachInfoDialogFragment
import eu.eitdigital.hcid.sustainabilitycoach.plan.ui.PlanNotificationFragment
import eu.eitdigital.hcid.sustainabilitycoach.plan.ui.PlanScheduleFragment
import eu.eitdigital.hcid.sustainabilitycoach.plan.ui.PlanSuccessDialogFragment
import kotlinx.android.synthetic.main.plan_activity.*

class PlanActivity : AppCompatActivity(), PlanFragmentInteractionListener {

    private lateinit var preferences: DummyDataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plan_activity)
        setSupportActionBar(appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PlanScheduleFragment.newInstance())
                .commitNow()
        }

        preferences = DummyDataModel(getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE));
    }

    override fun showNotificationFragment() {
        openFragment(PlanNotificationFragment.newInstance())
    }

    override fun showActivationDialog() {
        val dialog = PlanSuccessDialogFragment.newInstance()

        val ft = supportFragmentManager.beginTransaction()
        dialog.show(ft, PlanSuccessDialogFragment.TAG)
    }

    override fun showCoachInfoDialog() {
        val dialog = PlanCoachInfoDialogFragment.newInstance()

        val ft = supportFragmentManager.beginTransaction()
        dialog.show(ft, PlanCoachInfoDialogFragment.TAG)
    }

    override fun finishTaskToDashboard() {
        preferences.state = DummyDataModel.States.ACTIVE_PLANNED

        finish()
    }

    private fun openFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()

                return true
            }
            R.id.explore_plan_cancel -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.explore_plan_options, menu)
        return true
    }
}
