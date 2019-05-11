package eu.eitdigital.hcid.sustainabilitycoach.plan.ui

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import eu.eitdigital.hcid.sustainabilitycoach.R
import eu.eitdigital.hcid.sustainabilitycoach.plan.PlanFragmentInteractionListener
import kotlinx.android.synthetic.main.plan_schedule_fragment.*

class PlanScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = PlanScheduleFragment()
    }

    private lateinit var viewModel: PlanViewModel
    private var listener: PlanFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.plan_schedule_fragment, container, false)
    }

    private val textViewResources: Array<Int> = arrayOf(
        R.id.plan_weekly_text_1,
        R.id.plan_weekly_text_2,
        R.id.plan_weekly_text_3,
        R.id.plan_weekly_text_4,
        R.id.plan_weekly_text_5,
        R.id.plan_weekly_text_6,
        R.id.plan_weekly_text_7
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSeekbarInteraction()

        next_button.setOnClickListener {
            if (viewModel.frequency != 3) {
                Snackbar.make(plan_schedule, "This selection is currently not supported!", Snackbar.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            listener?.showNotificationFragment()
        }

        learn_coach_button.setOnClickListener {
            listener?.showCoachInfoDialog()
        }
    }

    private fun setupSeekbarInteraction() {
        updateSelectedSlideTextStyles(seekBar.progress)

        textViewResources.forEachIndexed { index, textViewId ->
            val textView = activity?.findViewById<TextView>(textViewId)
            textView?.isClickable = true
            textView?.setOnClickListener {
                seekBar.progress = index
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val frequency = progress + 1
                viewModel.frequency = frequency

                updateSelectedSlideTextStyles(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateSelectedSlideTextStyles(progress: Int) {
        textViewResources.forEachIndexed { index, i ->
            val textView = activity?.findViewById<TextView>(i)
            if (index == progress) {
                textView?.setTextAppearance(R.style.AppTheme_SeekSelected)
            } else {
                textView?.setTextAppearance(R.style.AppTheme_SeekUnselected)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlanViewModel::class.java)
        // TODO: Use the ViewModel
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