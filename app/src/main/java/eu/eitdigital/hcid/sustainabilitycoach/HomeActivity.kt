package eu.eitdigital.hcid.sustainabilitycoach

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class HomeActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                toolbar.title = resources.getString(R.string.app_name)

                openFragment(HomeFragment.newInstance())

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_habits -> {
                toolbar.title = resources.getString(R.string.title_habits)

                openFragment(HabitsFragment.newInstance())

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_impact -> {
                toolbar.title = resources.getString(R.string.title_impact)

                openFragment(ImpactFragment.newInstance())

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                toolbar.title = resources.getString(R.string.title_profile)

                openFragment(ProfileFragment.newInstance())

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar = findViewById(R.id.appToolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        toolbar.title = resources.getString(R.string.app_name)
        openFragment(HomeFragment.newInstance())
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
