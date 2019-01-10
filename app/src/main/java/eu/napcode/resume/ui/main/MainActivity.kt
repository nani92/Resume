package eu.napcode.resume.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.transition.Slide
import android.view.MenuItem
import android.view.View
import dagger.android.AndroidInjection
import eu.napcode.resume.R
import eu.napcode.resume.model.Developer
import eu.napcode.resume.ui.developer.DeveloperFragment
import eu.napcode.resume.ui.developer.DeveloperViewModel
import eu.napcode.resume.ui.education.EducationFragment
import eu.napcode.resume.ui.projects.ProjectsFragment
import eu.napcode.resume.ui.work.WorkFragment
import eu.napcode.resume.utils.startSendMailActivity
import eu.napcode.resume.utils.startWebActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var fragmentToSet: Fragment

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var developerViewModel: DeveloperViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        setSupportActionBar(toolbar)
        setupDrawer()

        if (savedInstanceState == null) {
            displayFirstFragment()
        }

        developerViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DeveloperViewModel::class.java)

        developerViewModel.developer.observe(this, Observer { developer ->
            displayDeveloperInDrawer(developer!!)
        })
    }

    private fun setupDrawer() {
        drawerToggle = ActionBarDrawerToggle(this,
                drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer)

        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {

            override fun onDrawerStateChanged(p0: Int) {}

            override fun onDrawerSlide(p0: View, p1: Float) {}

            override fun onDrawerOpened(p0: View) {}

            override fun onDrawerClosed(drawerView: View) = displayFragment()
        })
    }

    private fun displayFirstFragment() {
        fragmentToSet = DeveloperFragment()

        displayFragment()
    }

    private fun displayFragment() {
        fragmentToSet.enterTransition = getTransitionForFragment()

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainContainerFrame, fragmentToSet)
                .commit()
    }

    private fun getTransitionForFragment(): Slide {
        val slide = Slide()
        slide.duration = resources.getInteger(R.integer.anim_duration_medium).toLong()

        return slide
    }

    private fun displayDeveloperInDrawer(developer: Developer) {
        var header = navigationView.getHeaderView(0)

        header.developerTextView.text =
                getString(R.string.dev_name, developer.name, developer.surname)
        header.mailImageView
                .setOnClickListener { startSendMailActivity(this, developer.mail) }
        header.devAvatarImageView.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            displayFirstFragment()
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)

        when (menuItem.itemId) {

            R.id.projects -> {
                fragmentToSet = ProjectsFragment()

                return true
            }

            R.id.education -> {
                fragmentToSet = EducationFragment()

                return true
            }

            R.id.work -> {
                fragmentToSet = WorkFragment()

                return true
            }

            R.id.portfolio_web -> {
                startWebActivity(this, getString(R.string.portfolio_address))

                return true
            }

            R.id.resume_pdf -> {
                startWebActivity(this, getString(R.string.resume_pdf))

                return true
            }

            else -> return false
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
