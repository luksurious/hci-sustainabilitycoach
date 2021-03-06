package eu.eitdigital.hcid.sustainabilitycoach.explore

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 *
 *
 * See the Android Training lesson [Communicating with Other Fragments]
 * (http://developer.android.com/training/basics/fragments/communicating.html)
 * for more information.
 */
interface ExploreFragmentInteractionListener {
    fun onFragmentInteraction(origin: String)

    fun setCategorySelection(category: String)

    fun setDifficultySelection(difficulty: String)

    fun goBackToCategorySelection()

    fun showUnsupportedActionMessage()

    fun showDetailsDialog()

    fun showPlanningDialog()

    fun startPlanning()

    fun finishExploreActive()
}