package eu.napcode.resume.ui.project_details

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.chip.Chip
import android.support.v7.app.AppCompatActivity
import android.view.View.VISIBLE
import dagger.android.AndroidInjection
import eu.napcode.resume.R
import eu.napcode.resume.model.Project
import eu.napcode.resume.utils.getDateSpannableString
import eu.napcode.resume.utils.getProjectTypeString
import eu.napcode.resume.utils.startAppPlayStoreActivity
import eu.napcode.resume.utils.startWebActivity
import kotlinx.android.synthetic.main.activity_project_details.*

class ProjectActivity : AppCompatActivity() {

    companion object {
        const val ARG_PROJECT= "project"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_details)
        AndroidInjection.inject(this)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        var project = intent.getParcelableExtra<Project>(ARG_PROJECT)
        displayProject(project)

        var begin = (topGuideline.layoutParams as ConstraintLayout.LayoutParams).guideBegin
        topGuideline.setGuidelineBegin(begin + resources.getDimension(R.dimen.base_margin).toInt())
    }

    fun displayProject(project: Project) {
        appBarProjectTitleTextView.text = project.name
        projectTitleTextView.text = project.name

        projectTypeTextView.text = getString(
                R.string.project_type,
                getProjectTypeString(this, project.type)
        )

        projectStartDateTextView.text = getString(
                R.string.development_started,
                getDateSpannableString(month = project.startMonth, year = project.startYear)
        )

        if (project.company != null) {
            projectCompanyTextView.text = getString(R.string.commercial_project_at, project.company)
            projectCompanyTextView.visibility = VISIBLE
        }

        if (project.github != null) {
            githubFAB.setOnClickListener{ startWebActivity(this, project.github)}
            githubFAB.visibility = VISIBLE
        }

        if (project.playstore != null) {
            playStoreFAB.setOnClickListener{ startAppPlayStoreActivity(this, project.playstore) }
            playStoreFAB.visibility = VISIBLE
        }

        descriptionTextView.text = project.description

        for (tech in project.tech) {
            var chip = Chip(this)
            chip.text = tech
            var padding = resources.getDimension(R.dimen.base_margin).toInt()
            chip.setPadding(padding, padding, padding, padding)

            techChipGroup.addView(chip)
        }
    }
}