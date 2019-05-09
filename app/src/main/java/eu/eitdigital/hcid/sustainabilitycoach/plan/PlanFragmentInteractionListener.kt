package eu.eitdigital.hcid.sustainabilitycoach.plan

interface PlanFragmentInteractionListener {
    fun showNotificationFragment()

    fun showActivationDialog()

    fun showCoachInfoDialog()

    fun finishTaskToDashboard()
}