package eu.eitdigital.hcid.sustainabilitycoach

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import eu.eitdigital.hcid.sustainabilitycoach.plan.PlanFragmentInteractionListener
import kotlinx.android.synthetic.main.activity_fill_results.*
import kotlinx.android.synthetic.main.plan_success_dialog_fragment.*


class FillResultsActivity : AppCompatActivity() {

    private var listener: PlanFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_results)

        setSupportActionBar(appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //openFragment(HomeFillResults.newInstance(""))
        goButton.setOnClickListener{
            // Get the checked radio button id from radio group
            var id: Int = radio_group.checkedRadioButtonId
            if (id!=-1){ // If any radio button checked from radio group
                // Get the instance of radio button using id
                val radio: RadioButton = findViewById(id)
                //Toast.makeText(applicationContext,"On button click : ${radio.text}", Toast.LENGTH_SHORT).show()

            }
            Toast.makeText(applicationContext,"Your results were saved!", Toast.LENGTH_SHORT).show()
            listener?.finishTaskToDashboard()
        }


    }

    fun radio_button_click(view: View){
        // Get the clicked radio button instance
        val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
        if(radio.text.equals("NO")){
            checkboxCard.visibility = View.VISIBLE
        }else{
            checkboxCard.visibility = View.GONE
        }
        /*Toast.makeText(applicationContext,"On click : ${radio.text}",
            Toast.LENGTH_SHORT).show()*/

    }
}
