package eu.napcode.resume.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.transition.Slide
import android.util.Log
import android.view.MenuItem
import com.google.gson.Gson
import eu.napcode.developerdataprovider.LocalDataProvider
import eu.napcode.resume.R
import eu.napcode.resume.model.Developer
import eu.napcode.resume.ui.developer.DeveloperFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var fragmentToSet: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        setupDrawer()

        if (savedInstanceState == null) {
            displayFirstFragment()
        }

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
