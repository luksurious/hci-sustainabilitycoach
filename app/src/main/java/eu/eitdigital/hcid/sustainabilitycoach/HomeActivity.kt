package eu.eitdigital.hcid.sustainabilitycoach

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setupHomeTab()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_habits -> {
                appToolbar.title = resources.getString(R.string.title_habits)

                openFragment(HabitsFragment.newInstance())

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_impact -> {
                appToolbar.title = resources.getString(R.string.title_impact)

                openFragment(ImpactFragment.newInstance())

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                appToolbar.title = resources.getString(R.string.title_profile)

                openFragment(ProfileFragment.newInstance())

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setupHomeTab(addToBack: Boolean = true) {
        appToolbar.title = resources.getString(R.string.app_name)

        openFragment(HomeFragment.newInstance(), addToBack)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(appToolbar)

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        setupHomeTab(false)
    }

    private fun openFragment(fragment: Fragment, addToBack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        if (addToBack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }
}
