package eu.eitdigital.hcid.sustainabilitycoach

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import eu.eitdigital.hcid.sustainabilitycoach.explore.HabitsDetailsDialog
import eu.eitdigital.hcid.sustainabilitycoach.home.*
import eu.eitdigital.hcid.sustainabilitycoach.model.DummyDataModel
import eu.eitdigital.hcid.sustainabilitycoach.model.PREF_NAME
import eu.eitdigital.hcid.sustainabilitycoach.plan.PlanActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main.*

class MainActivity : AppCompatActivity(), HomeInteractionListener {

    private var currentFragment: String? = null

    private lateinit var preferences: DummyDataModel
    private var defaultElevation: Float = 12f

    private var homeStateFragments: HashMap<DummyDataModel.States, Class<out Fragment>> = hashMapOf(
        Pair(DummyDataModel.States.NEW, HomeFragment::class.java),
        Pair(DummyDataModel.States.ACTIVE_UNPLANNED, HomeAfterPlanFragment::class.java),
        Pair(DummyDataModel.States.ACTIVE_PLANNED, HomeAfterPlanFragment::class.java),
        Pair(DummyDataModel.States.FAILED_ONCE, HomeAfterPlanFragment::class.java),
        Pair(DummyDataModel.States.SUCCEEDED_ONCE, HomeAfterSuccessFragment::class.java),
        Pair(DummyDataModel.States.AFTER_WEEKS, HomeAfterWeeksFragment::class.java)
    )

    private var habitsStateFragments: HashMap<DummyDataModel.States, Class<out Fragment>> = hashMapOf(
        Pair(DummyDataModel.States.NEW, HabitsFragment::class.java),
        Pair(DummyDataModel.States.ACTIVE_UNPLANNED, Habits2Fragment::class.java),
        Pair(DummyDataModel.States.ACTIVE_PLANNED, Habits2Fragment::class.java),
        Pair(DummyDataModel.States.FAILED_ONCE, Habits2Fragment::class.java),
        Pair(DummyDataModel.States.SUCCEEDED_ONCE, Habits2Fragment::class.java),
        Pair(DummyDataModel.States.AFTER_WEEKS, Habits3Fragment::class.java)
    )

    private var activeTab = Tabs.HOME

    enum class Tabs {
        HOME, HABITS, IMPACT, PROFILE
    }

    private val onBottomNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                activeTab = Tabs.HOME

                showFragmentOfState()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_habits -> {
                activeTab = Tabs.HABITS

                showFragmentOfState()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_impact -> {
                activeTab = Tabs.IMPACT

                showFragmentOfState()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                activeTab = Tabs.PROFILE
                showFragmentOfState()

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val onDrawerNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {
        activeTab = Tabs.HOME
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
                /*Intent(this, FillResultsActivity::class.java).apply {
                    startActivity(this)
                }*/
                showFragmentOfState()
            }
            R.id.nav_start_fill_daytwo -> {
                preferences.state = DummyDataModel.States.FAILED_ONCE
                Intent(this, FillResultsSucceedActivity::class.java).apply {
                    startActivity(this)
                }
                //showFragmentOfState()
            }
            R.id.nav_start_fill_end -> {
                preferences.state = DummyDataModel.States.SUCCEEDED_ONCE
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
        openFragment(HomeFragment.newInstance(), addToBack)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = DummyDataModel(getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE));

        setSupportActionBar(appToolbar)

        // TODO: is a drawer good? might users try to use it? instead use menu with hidden option?
        //  or otherwise make admin only?
//        val toggle = ActionBarDrawerToggle(
//            this, drawer_layout, appToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
//        )
//        drawer_layout.addDrawerListener(toggle)
//        toggle.syncState()

        main_drawer_view.setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)

        nav_view.setOnNavigationItemSelectedListener(onBottomNavigationItemSelectedListener)

        appToolbar.setOnLongClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
            true
        }

        activeTab = Tabs.HOME
        showFragmentOfState()
    }

    override fun onRestart() {
        super.onRestart()

        if (!isFinishing) {
            showFragmentOfState()
        }
    }

    private fun showFragmentOfState() {

        activateCorrectTab()
        when (activeTab) {
            Tabs.HOME -> {
                appToolbar.title = resources.getString(R.string.app_name)
                appBarLayout.elevation = defaultElevation


                val fragment: Fragment? = homeStateFragments[preferences.state]?.newInstance()
                if (fragment == null) {
                    setupHomeTab()
                } else {
                    Log.i("STATE", "State is " + preferences.state)
                    openFragment(fragment)
                }
            }
            Tabs.HABITS -> {
                appToolbar.title = resources.getString(R.string.title_habits)
                appBarLayout.elevation = defaultElevation

                var fragment: Fragment? = habitsStateFragments[preferences.state]?.newInstance()

                if (fragment == null) {
                    fragment = HabitsFragment.newInstance()
                }

                Log.i("STATE", "State is " + preferences.state)
                openFragment(fragment)
            }
            Tabs.IMPACT -> {
                appToolbar.title = resources.getString(R.string.title_impact)

                if (preferences.state == DummyDataModel.States.AFTER_WEEKS) {
                    appBarLayout.elevation = 0f
                    openFragment(ImpactFragment.newInstance())
                } else {
                    appBarLayout.elevation = defaultElevation
                    openFragment(ImpactEmpty.newInstance())
                }
            }
            Tabs.PROFILE -> {
                appToolbar.title = resources.getString(R.string.title_profile)
                appBarLayout.elevation = defaultElevation
                openFragment(ProfileFragment.newInstance())
            }
        }
    }

    private fun activateCorrectTab() = when (activeTab) {
        Tabs.HOME -> nav_view.menu.findItem(R.id.navigation_home)?.isChecked = true
        Tabs.HABITS -> nav_view.menu.findItem(R.id.navigation_habits)?.isChecked = true
        Tabs.IMPACT -> nav_view.menu.findItem(R.id.navigation_impact)?.isChecked = true
        Tabs.PROFILE -> nav_view.menu.findItem(R.id.navigation_profile)?.isChecked = true
    }

    private fun openFragment(fragment: Fragment, addToBack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
        currentFragment = fragment.javaClass.name
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else if (activeTab != Tabs.HOME) {
            activeTab = Tabs.HOME
            showFragmentOfState()
        } else {
            super.onBackPressed()
        }
    }
    fun  getStatus():DummyDataModel.States{
       return preferences.state
    }

    fun showImpactTab() {
        nav_view.selectedItemId = R.id.navigation_impact
    }

    fun showUnsupportedActionMessage() {
        Snackbar.make(fragment_container, "This action is currently not supported!", Snackbar.LENGTH_LONG)
            .show()
    }

    override fun showDetailsDialog(number:Int) {
        val dialog = HabitsDetailsDialog.newInstance(number)
        val ft = supportFragmentManager.beginTransaction()
        dialog.show(ft, HabitsDetailsDialog.TAG)
    }

    override fun openImpactScreen(){
        openFragment(ImpactFragment.newInstance())
    }

    override fun enterResultsScreen(){
        Intent(this, FillResultsActivity::class.java).apply {
            startActivity(this)
        }
    }

}
