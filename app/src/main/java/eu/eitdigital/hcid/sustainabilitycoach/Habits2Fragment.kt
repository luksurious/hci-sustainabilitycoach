package eu.eitdigital.hcid.sustainabilitycoach

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.habits_fragment2.*

class Habits2Fragment: Fragment() {

    private var listener: HomeInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.habits_fragment2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MeatCard.setOnClickListener { showUnsupportedActionMessage() }
        MeatCard.setOnClickListener {
            listener?.showDetailsDialog(2)
        }
        button_create_habit.setOnClickListener { showUnsupportedActionMessage() }
    }

    private fun showUnsupportedActionMessage() {
        Snackbar.make(habits_fragment, "This action is currently not supported!", Snackbar.LENGTH_LONG)
            .show()
    }

    companion object {
        fun newInstance(): Habits2Fragment = Habits2Fragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement HomeInteractionListener")
        }
    }
}
