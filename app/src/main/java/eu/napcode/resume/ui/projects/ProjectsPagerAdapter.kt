package eu.napcode.resume.ui.projects

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import eu.napcode.resume.model.ProjectType
import eu.napcode.resume.ui.projects.DisplayProjectsFragment.Companion.newInstance

class ProjectsPagerAdapter(private var titles: Array<String>, fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getCount() = titles.size

    override fun getItem(position: Int): Fragment? {

        return when (position) {

            0 -> newInstance(ProjectType.OWN)

            1 -> newInstance(ProjectType.COMMERCIAL)

            2 -> newInstance(ProjectType.OPEN_SOURCE)

            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}
