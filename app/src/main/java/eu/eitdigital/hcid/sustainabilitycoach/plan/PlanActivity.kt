package eu.eitdigital.hcid.sustainabilitycoach.plan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import eu.eitdigital.hcid.sustainabilitycoach.R
import eu.eitdigital.hcid.sustainabilitycoach.plan.ui.PlanNotificationFragment
import eu.eitdigital.hcid.sustainabilitycoach.plan.ui.PlanScheduleFragment
import eu.eitdigital.hcid.sustainabilitycoach.plan.ui.PlanSuccessDialogFragment
import kotlinx.android.synthetic.main.plan_activity.*

class PlanActivity : AppCompatActivity(), PlanFragmentInteractionListener {
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
//        TODO("not implemented")
    }

    override fun finishTaskToDashboard() {
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
        }
        return super.onOptionsItemSelected(item)
    }
}
