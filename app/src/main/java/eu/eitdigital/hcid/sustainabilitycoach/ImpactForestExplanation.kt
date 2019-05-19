package eu.eitdigital.hcid.sustainabilitycoach

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.impact_explanation_co2.*

class ImpactForestExplanation: DialogFragment() {
    companion object {
        val TAG = "ImpactForestExplanation"

        fun newInstance() = ImpactForestExplanation()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            R.style.FullScreenDialogStyle
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.impact_explanation_forest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogToolbar?.setNavigationIcon(R.drawable.ic_close_white_24dp)
        dialogToolbar?.setNavigationOnClickListener { dialog?.dismiss() }
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            (dialog as Dialog).window?.setLayout(width, height)
        }
    }
}