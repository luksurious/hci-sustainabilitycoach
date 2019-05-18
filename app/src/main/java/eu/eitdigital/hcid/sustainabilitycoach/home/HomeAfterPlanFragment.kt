package eu.eitdigital.hcid.sustainabilitycoach.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import eu.eitdigital.hcid.sustainabilitycoach.FillResultsActivity
import eu.eitdigital.hcid.sustainabilitycoach.MainActivity
import eu.eitdigital.hcid.sustainabilitycoach.R
import eu.eitdigital.hcid.sustainabilitycoach.model.DummyDataModel
import eu.eitdigital.hcid.sustainabilitycoach.model.PREF_NAME
import kotlinx.android.synthetic.main.home_after_plan_fragment.*




class HomeAfterPlanFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.home_after_plan_fragment, container, false)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button2.setOnClickListener {
            val intent = Intent(activity, FillResultsActivity::class.java)
            startActivity(intent)
        }

        MeatCard.setOnClickListener { (activity as MainActivity).showUnsupportedActionMessage() }
    }

    companion object {
        fun newInstance(): HomeAfterPlanFragment =
            HomeAfterPlanFragment()
    }
}