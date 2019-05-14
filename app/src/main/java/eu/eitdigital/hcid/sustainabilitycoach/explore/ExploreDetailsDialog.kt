
/*
package eu.eitdigital.hcid.sustainabilitycoach.explore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import eu.eitdigital.hcid.sustainabilitycoach.HomeFillResults
import eu.eitdigital.hcid.sustainabilitycoach.R


class ExploreDetailsDialog : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_dialog)
    }
}
*/

package eu.eitdigital.hcid.sustainabilitycoach.explore

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import eu.eitdigital.hcid.sustainabilitycoach.R
import eu.eitdigital.hcid.sustainabilitycoach.plan.PlanActivity
import kotlinx.android.synthetic.main.activity_details_dialog.*
import kotlinx.android.synthetic.main.plan_success_dialog_fragment.*

class ExploreDetailsDialog: DialogFragment() {
    companion object {
        val TAG = "ExploreDetailsDialog"

        fun newInstance() = ExploreDetailsDialog()
    }

    private var listener: ExploreFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_details_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        dialogToolbar?.setNavigationIcon(R.drawable.ic_close_white_24dp)
        dialogToolbar?.setNavigationOnClickListener { dialog?.dismiss() }
        */

        imageClose.setOnClickListener{
            dialog?.dismiss()
        }

        button_start_habit.setOnClickListener {
            val intent = Intent(activity, PlanActivity::class.java)
            startActivity(intent)
        }
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