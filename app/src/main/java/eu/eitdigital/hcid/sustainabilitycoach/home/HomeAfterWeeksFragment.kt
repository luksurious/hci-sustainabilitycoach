package eu.eitdigital.hcid.sustainabilitycoach.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import eu.eitdigital.hcid.sustainabilitycoach.R
import kotlinx.android.synthetic.main.home_after_weeks.*

class HomeAfterWeeksFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.home_after_weeks, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button2.setOnClickListener { showUnsupportedActionMessage() }
        button3.setOnClickListener { showUnsupportedActionMessage() }
    }

    private fun showUnsupportedActionMessage() {
        Snackbar.make(top_layout, "This action is currently not supported!", Snackbar.LENGTH_LONG)
            .show()
    }

    companion object {
        fun newInstance(): HomeAfterWeeksFragment =
            HomeAfterWeeksFragment()
    }
}