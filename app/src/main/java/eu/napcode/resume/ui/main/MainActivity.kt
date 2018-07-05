package eu.napcode.resume.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.transition.Slide
import android.view.MenuItem
import dagger.android.AndroidInjection
import eu.napcode.developerdataprovider.LocalDataProvider
import eu.napcode.resume.R
import eu.napcode.resume.model.Developer
import eu.napcode.resume.repository.DeveloperRepository
import eu.napcode.resume.ui.developer.DeveloperFragment
import eu.napcode.resume.ui.developer.DeveloperViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.*
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
            navigationView.getHeaderView(0).developerTextView.text = getString(R.string.dev_name, developer!!.name, developer.surname)
        })
    }

    private fun setupDrawer() {
        drawerToggle = ActionBarDrawerToggle(this,
                drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer)

        drawerLayout.addDrawerListener(this.drawerToggle)
        this.drawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
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

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
