package eu.eitdigital.hcid.sustainabilitycoach

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.habits_fragment3.*

class Habits3Fragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.habits_fragment3, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MeatCard.setOnClickListener { showUnsupportedActionMessage() }
        RecycleCard.setOnClickListener { showUnsupportedActionMessage() }
    }

    private fun showUnsupportedActionMessage() {
        Snackbar.make(habits_fragment, "This action is currently not supported!", Snackbar.LENGTH_LONG)
            .show()
    }
    companion object {
        fun newInstance(): Habits3Fragment = Habits3Fragment()
    }
}
