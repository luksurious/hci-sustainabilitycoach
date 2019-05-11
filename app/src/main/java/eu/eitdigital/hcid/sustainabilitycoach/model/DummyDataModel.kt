package eu.eitdigital.hcid.sustainabilitycoach.model

import android.content.SharedPreferences

const val PREF_NAME = "DummyData"

const val KEY_STATES = "States"

class DummyDataModel(private var preferences: SharedPreferences) {
    var state = States.NEW
        set(value) {
            field = value

            preferences.edit()
                .putString(KEY_STATES, value.name)
                .apply()
        }

    init {
        val savedState = preferences.getString(KEY_STATES, "")

        if (savedState.isNotEmpty()) {
            state = States.valueOf(savedState)
        }
    }

    enum class States {
        NEW, ACTIVE_UNPLANNED, ACTIVE_PLANNED, FAILED_ONCE, SUCCEEDED_ONCE, AFTER_WEEKS
    }
}