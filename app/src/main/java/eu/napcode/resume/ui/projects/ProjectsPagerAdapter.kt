package eu.napcode.resume.ui.projects

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ProjectsPagerAdapter(private var titles: Array<String>, fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getCount() = titles.size

    override fun getItem(position: Int): Fragment? {

        return when (position) {

            0 -> DisplayProjectsFragment()

            1 -> DisplayProjectsFragment()

            2 -> DisplayProjectsFragment()

            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}
