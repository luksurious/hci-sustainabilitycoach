package eu.eitdigital.hcid.sustainabilitycoach

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import eu.eitdigital.hcid.sustainabilitycoach.explore.ExploreActivity
import kotlinx.android.synthetic.main.habits_fragment.*

class HabitsFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.habits_fragment, container, false)

    companion object {
        fun newInstance(): HabitsFragment = HabitsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_create_habit.setOnClickListener {
            val intent = Intent(activity, ExploreActivity::class.java)
            startActivity(intent)
        }
    }

}