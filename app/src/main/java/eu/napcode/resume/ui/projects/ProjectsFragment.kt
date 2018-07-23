package eu.napcode.resume.ui.projects

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.napcode.resume.R
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.fragment_projects.*

class ProjectsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_projects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager(viewpager)
        projectsTabLayout.setupWithViewPager(viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ProjectsPagerAdapter(getTabTitles(), fragmentManager)
        viewPager.adapter = adapter
        viewPager.currentItem = 1
    }

    private fun getTabTitles(): Array<String> {
        return arrayOf(
                "All",
                getString(R.string.project_own),
                getString(R.string.project_commercial),
                getString(R.string.project_opens))
    }
}
