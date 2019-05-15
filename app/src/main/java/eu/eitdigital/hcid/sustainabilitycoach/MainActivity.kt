package eu.eitdigital.hcid.sustainabilitycoach

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import eu.eitdigital.hcid.sustainabilitycoach.explore.ExploreDetailsDialog
import eu.eitdigital.hcid.sustainabilitycoach.model.DummyDataModel
import eu.eitdigital.hcid.sustainabilitycoach.model.PREF_NAME
import eu.eitdigital.hcid.sustainabilitycoach.plan.Home3Fragment
import eu.eitdigital.hcid.sustainabilitycoach.plan.PlanActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main.*

class MainActivity : AppCompatActivity() {

    private var currentFragment: String? = null

    private lateinit var preferences: DummyDataModel

    private var stateFragments: HashMap<DummyDataModel.States, Class<out Fragment>> = hashMapOf(
        Pair(DummyDataModel.States.NEW, HomeFragment::class.java),
        Pair(DummyDataModel.States.ACTIVE_UNPLANNED, HomeAfterPlanFragment::class.java),
        Pair(DummyDataModel.States.ACTIVE_PLANNED, HomeAfterPlanFragment::class.java),
        Pair(DummyDataModel.States.FAILED_ONCE, HomeAfterPlanFragment::class.java),
        Pair(DummyDataModel.States.SUCCEEDED_ONCE, HomeFragment::class.java),
        Pair(DummyDataModel.States.AFTER_WEEKS, HomeFragment::class.java)
    )

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
                preferences.state = DummyDataModel.States.NEW
                showFragmentOfState()
            }
            R.id.nav_plan_habit -> {
                preferences.state = DummyDataModel.States.ACTIVE_UNPLANNED
                Intent(this, PlanActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.nav_start_planned -> {
                preferences.state = DummyDataModel.States.ACTIVE_PLANNED
                showFragmentOfState()
            }
            R.id.nav_start_fill_dayone -> {
                preferences.state = DummyDataModel.States.ACTIVE_PLANNED
                showFragmentOfState()
            }
            R.id.nav_start_fill_daytwo -> {
                preferences.state = DummyDataModel.States.FAILED_ONCE
                showFragmentOfState()
            }
            R.id.nav_start_after_twoweeks -> {
                preferences.state = DummyDataModel.States.AFTER_WEEKS
                showFragmentOfState()
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
        setContentView(R.layout.activity_main)

        preferences = DummyDataModel(getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE));

        setSupportActionBar(appToolbar)

        // TODO: is a drawer good? might users try to use it? instead use menu with hidden option?
        //  or otherwise make admin only?
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, appToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        main_drawer_view.setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)

        nav_view.setOnNavigationItemSelectedListener(onBottomNavigationItemSelectedListener)

        showFragmentOfState()
    }

    override fun onRestart() {
        super.onRestart()

        if (!isFinishing) {
            showFragmentOfState()
        }
    }

    private fun showFragmentOfState() {
        val fragment: Fragment? = stateFragments[preferences.state]?.newInstance()

        if (fragment == null) {
            setupHomeTab()
        } else {
            Log.i("STATE", "State is " + preferences.state)
            openFragment(fragment)
        }
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
    fun openFillResults() {
        openFragment(Home2Fragment.newInstance("",""))
    }
    private fun openFillResults2() {
        appToolbar.title = resources.getString(R.string.app_name)
        openFragment(Home3Fragment.newInstance("",""))
    }
}
