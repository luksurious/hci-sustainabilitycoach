package eu.eitdigital.hcid.sustainabilitycoach

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_main.*

class HomeActivity : AppCompatActivity() {

    private var currentFragment: String? = null

    private val onBottomNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
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

    private val onDrawerNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.nav_start_new -> {
                setupHomeTab()
            }
            R.id.nav_plan_habit -> {

            }
            R.id.nav_start_planned -> {

            }
            R.id.nav_start_fill_dayone -> {

            }
            R.id.nav_start_fill_daytwo -> {

            }
            R.id.nav_start_after_twoweeks -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return@OnNavigationItemSelectedListener true
    }

    private fun setupHomeTab(addToBack: Boolean = true) {
        appToolbar.title = resources.getString(R.string.app_name)

        openFragment(HomeFragment.newInstance(), addToBack)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(appToolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, appToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        main_drawer_view.setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)

        nav_view.setOnNavigationItemSelectedListener(onBottomNavigationItemSelectedListener)

        setupHomeTab()
    }

    private fun openFragment(fragment: Fragment, addToBack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        if (addToBack && currentFragment != null && currentFragment != fragment.javaClass.name) {
            transaction.addToBackStack(null)
        }
        transaction.commit()

        currentFragment = fragment.javaClass.name
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
