package eu.eitdigital.hcid.sustainabilitycoach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ProfileFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.profile_fragment, container, false)

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}