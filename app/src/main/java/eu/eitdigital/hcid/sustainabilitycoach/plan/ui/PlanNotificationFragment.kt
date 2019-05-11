package eu.eitdigital.hcid.sustainabilitycoach.plan.ui

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import eu.eitdigital.hcid.sustainabilitycoach.R
import eu.eitdigital.hcid.sustainabilitycoach.plan.PlanFragmentInteractionListener
import kotlinx.android.synthetic.main.plan_notification_fragment.*

class PlanNotificationFragment : Fragment() {

    companion object {
        fun newInstance() = PlanNotificationFragment()
    }

    private lateinit var viewModel: PlanViewModel
    private var listener: PlanFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.plan_notification_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notification_days_button_group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            handleDayChange(checkedId, isChecked)
        }
        notification_days_button_group2.addOnButtonCheckedListener { group, checkedId, isChecked ->
            handleDayChange(checkedId, isChecked)
        }

        activate_button.setOnClickListener {
            if (!viewModel.hasNotification
                || !viewModel.notificationDays["Monday"]!!
                || !viewModel.notificationDays["Wednesday"]!!
                || !viewModel.notificationDays["Friday"]!!
            ) {
                Snackbar.make(plan_notification, "This selection is currently not supported!", Snackbar.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            listener?.showActivationDialog()
        }

        learn_coach_button.setOnClickListener {
            listener?.showCoachInfoDialog()
        }
    }

    private fun handleDayChange(checkedId: Int, isChecked: Boolean) {
        when (checkedId) {
            R.id.button_notification_monday -> viewModel.notificationDays["Monday"] = isChecked
            R.id.button_notification_tuesday -> viewModel.notificationDays["Tuesday"] = isChecked
            R.id.button_notification_wednesday -> viewModel.notificationDays["Wednesday"] = isChecked
            R.id.button_notification_thursday -> viewModel.notificationDays["Thursday"] = isChecked
            R.id.button_notification_friday -> viewModel.notificationDays["Friday"] = isChecked
            R.id.button_notification_saturday -> viewModel.notificationDays["Saturday"] = isChecked
            R.id.button_notification_sunday -> viewModel.notificationDays["Sunday"] = isChecked
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