package eu.eitdigital.hcid.sustainabilitycoach

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import eu.eitdigital.hcid.sustainabilitycoach.plan.PlanFragmentInteractionListener
import kotlinx.android.synthetic.main.activity_fill_results.*
import com.google.android.material.snackbar.Snackbar
import eu.eitdigital.hcid.sustainabilitycoach.model.DummyDataModel
import eu.eitdigital.hcid.sustainabilitycoach.model.PREF_NAME
import kotlinx.android.synthetic.main.activity_fill_results.appToolbar
import kotlinx.android.synthetic.main.activity_fill_results.fragment_container


class FillResultsActivity : AppCompatActivity() {
    private lateinit var preferences: DummyDataModel

    private var listener: PlanFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_results)
        preferences = DummyDataModel(getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE));
        setSupportActionBar(appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var flag=false;
        goButton.isEnabled = false
        goButton.setOnClickListener{

            var id: Int = radio_group.checkedRadioButtonId
                val radio: RadioButton = findViewById(id)
            if (id!=-1){
                if(radio.text.equals("NO")){
                    flag=true;
                }else{
                    showUnsupportedActionMessage()
                }
               if (restaurantOpt.isChecked && !(groceriesOpt.isChecked) && !(desireOpt.isChecked) && !(otherOpt.isChecked) && flag){
                   flag=true;
               }else{
                   showUnsupportedActionMessage()
                   flag=false;
               }
            }
            if (flag){
                preferences.state = DummyDataModel.States.FAILED_ONCE
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                Toast.makeText(applicationContext,"Your results were saved!", Toast.LENGTH_SHORT).show()
                listener?.finishTaskToDashboard()
            }

        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.explore_plan_options, menu)
        return true
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
    fun radio_button_click(view: View){
        val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
        if(radio.text.equals("NO")){
            checkboxCard.visibility = View.VISIBLE
        }else{
            checkboxCard.visibility = View.GONE
        }
        goButton.isEnabled=true;

    }
    fun  showUnsupportedActionMessage() {
        Snackbar.make(fragment_container, "This selection is currently not supported!", Snackbar.LENGTH_LONG)
            .show()
    }

}
