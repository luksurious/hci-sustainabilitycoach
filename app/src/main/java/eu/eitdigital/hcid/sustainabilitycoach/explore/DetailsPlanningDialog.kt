package eu.eitdigital.hcid.sustainabilitycoach.explore

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import eu.eitdigital.hcid.sustainabilitycoach.R
import kotlinx.android.synthetic.main.fragment_explore_details_planning_dialog.*

class DetailsPlanningDialog: DialogFragment() {
    companion object {
        val TAG = "ExploreDetailsDialog"

        fun newInstance() = DetailsPlanningDialog()
    }

    private var listener: ExploreFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_explore_details_planning_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonStartPlanning.setOnClickListener {
            listener?.startPlanning()
        }

        imageClose.setOnClickListener{
            listener?.finishExploreActive()
        }

        MeatCard.setOnClickListener { dialog?.dismiss() }
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            (dialog as Dialog).window?.setLayout(width, height)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ExploreFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement ExploreFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}