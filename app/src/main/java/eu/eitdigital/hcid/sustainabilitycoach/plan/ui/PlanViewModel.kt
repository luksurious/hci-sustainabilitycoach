package eu.eitdigital.hcid.sustainabilitycoach.plan.ui

import androidx.lifecycle.ViewModel

class PlanViewModel : ViewModel() {
    var notificationDays: MutableMap<String, Boolean> = mutableMapOf(
        Pair("Monday", false),
        Pair("Tuesday", false),
        Pair("Wednesday", false),
        Pair("Thursday", false),
        Pair("Friday", false),
        Pair("Saturday", false),
        Pair("Sunday", false)
    )
    var hasNotification: Boolean = true
    var frequency: Int = 1
}
