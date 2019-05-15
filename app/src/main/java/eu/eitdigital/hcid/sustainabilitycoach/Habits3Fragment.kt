package eu.eitdigital.hcid.sustainabilitycoach

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class Habits3Fragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.habits_fragment3, container, false)

    companion object {
        fun newInstance(): Habits3Fragment = Habits3Fragment()
    }
}
