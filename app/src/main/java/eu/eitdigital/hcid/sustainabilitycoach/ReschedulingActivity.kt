package eu.eitdigital.hcid.sustainabilitycoach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import eu.eitdigital.hcid.sustainabilitycoach.plan.ui.PlanViewModel
import kotlinx.android.synthetic.main.activity_fill_results.*
import kotlinx.android.synthetic.main.activity_rescheduling.*
import kotlinx.android.synthetic.main.activity_rescheduling.appToolbar
import kotlinx.android.synthetic.main.activity_rescheduling.fragment_container

class ReschedulingActivity : AppCompatActivity() {
    private lateinit var viewModel: PlanViewModel
    private var flag=false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rescheduling)
        setSupportActionBar(appToolbar)
        //activate_button.isEnabled = false
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProviders.of(this).get(PlanViewModel::class.java)
        notification_days_button_group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            handleDayChange(checkedId, isChecked)
            if ((!viewModel.hasNotification
                || viewModel.notificationDays["Monday"]!!
                || viewModel.notificationDays["Tuesday"]!!
                || viewModel.notificationDays["Wednesday"]!!) && isChecked
            ){
                notification_days_button_group2.clearChecked()
            }

        }
        notification_days_button_group2.addOnButtonCheckedListener { group, checkedId, isChecked ->
            handleDayChange(checkedId, isChecked)
            if ((!viewModel.hasNotification
                || viewModel.notificationDays["Thursday"]!!
                || viewModel.notificationDays["Friday"]!!
                || viewModel.notificationDays["Saturday"]!!
                || viewModel.notificationDays["Sunday"]!!)&& isChecked

            ){
                notification_days_button_group.clearChecked()
            }


        }

        activate_button.setOnClickListener {
            if(button_notification_tuesday.isChecked){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext,"Your changes were saved!", Toast.LENGTH_SHORT).show()
            }else{
                showUnsupportedActionMessage()
            }

        }
        skip_button.setOnClickListener {
            showUnsupportedActionMessage()
        }

    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true

            }
            R.id.explore_plan_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleDayChange(checkedId: Int, isChecked: Boolean) {
        when (checkedId) {
            R.id.button_notification_monday -> viewModel.notificationDays["Monday"] = isChecked
            R.id.button_notification_tuesday -> viewModel.notificationDays["Tuesday"] = isChecked
            R.id.button_notification_wednesday -> viewModel.notificationDays["Wednesday"] = isChecked
            R.id.button_notification_thursday -> viewModel.notificationDays["Thursday"] = isChecked
            R.id.button_notification_friday -> viewModel.notificationDays["Friday"] = isChecked
            R.id.button_notification_saturday -> viewModel.notificationDays["Saturday"] = isChecked
            R.id.button_notification_sunday -> viewModel.notificationDays["Sunday"] = isChecked
        }

    }
    fun  showUnsupportedActionMessage() {
        Snackbar.make(fragment_container, "This selection is currently not supported!", Snackbar.LENGTH_LONG)
            .show()
    }
}
