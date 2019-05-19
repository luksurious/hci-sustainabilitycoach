package eu.eitdigital.hcid.sustainabilitycoach

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import com.google.android.material.snackbar.Snackbar
import eu.eitdigital.hcid.sustainabilitycoach.model.DummyDataModel
import eu.eitdigital.hcid.sustainabilitycoach.model.PREF_NAME
import kotlinx.android.synthetic.main.activity_fill_results_succeed.*

class FillResultsSucceedActivity : AppCompatActivity() {
    private lateinit var preferences: DummyDataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_results_succeed)
        preferences = DummyDataModel(getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE));
        setSupportActionBar(appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        goButton2.isEnabled = false
        goButton2.setOnClickListener {
            var id: Int = radio_group.checkedRadioButtonId
            if (id != -1) {
                val radio: RadioButton = this.findViewById(id)
                if (radio.id == R.id.yesOption) {
                    preferences.state = DummyDataModel.States.SUCCEEDED_ONCE
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                } else {
                    showUnsupportedActionMessage()
                }
            }
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

    fun showUnsupportedActionMessage() {
        Snackbar.make(activity_results, "This selection is currently not supported!", Snackbar.LENGTH_LONG)
            .show()
    }

    fun radioButtonClick(view: View) {
        goButton2.isEnabled = true;

    }
}
