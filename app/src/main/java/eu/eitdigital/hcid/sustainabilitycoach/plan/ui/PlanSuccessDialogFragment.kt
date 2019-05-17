package eu.eitdigital.hcid.sustainabilitycoach.plan.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import eu.eitdigital.hcid.sustainabilitycoach.R
import eu.eitdigital.hcid.sustainabilitycoach.plan.PlanFragmentInteractionListener
import kotlinx.android.synthetic.main.plan_success_dialog_fragment.*

class PlanSuccessDialogFragment: DialogFragment() {
    companion object {
        val TAG = "PlanSuccessDialogFragment"

        fun newInstance() = PlanSuccessDialogFragment()
    }

    private var listener: PlanFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.plan_success_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogToolbar?.setNavigationIcon(R.drawable.ic_close_white_24dp)
        dialogToolbar?.navigationIcon?.setTint(resources.getColor(R.color.grey, null))
        dialogToolbar?.setNavigationOnClickListener { listener?.finishTaskToDashboard() }

        finish_button.setOnClickListener { listener?.finishTaskToDashboard() }
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
        if (context is PlanFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement PlanFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}