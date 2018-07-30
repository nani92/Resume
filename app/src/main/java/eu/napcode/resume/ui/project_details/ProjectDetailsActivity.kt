package eu.napcode.resume.ui.project_details

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.constraint.ConstraintSet.*
import android.support.design.chip.Chip
import android.support.v7.app.AppCompatActivity
import android.transition.Explode
import android.view.View
import android.view.View.VISIBLE
import android.widget.TextView
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
        const val ARG_PROJECT = "project"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_details)
        AndroidInjection.inject(this)

        window.enterTransition = Explode()

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
            githubFAB.setOnClickListener { startWebActivity(this, project.github) }
            githubFAB.visibility = VISIBLE
        }

        if (project.playstore != null) {
            playStoreFAB.setOnClickListener { startAppPlayStoreActivity(this, project.playstore) }
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

        if (project.links.isNotEmpty()) {
            linksLabelTextView.visibility = VISIBLE
            var lastViewId = R.id.linksLabelTextView

            for (link in project.links) {
                var linkTextView = layoutInflater.inflate(R.layout.item_link, null, false) as TextView
                linkTextView.text = link
                linkTextView.id = View.generateViewId()

                projectDetailsConstraintLayout.addView(linkTextView)
                var constraintSet = ConstraintSet()
                constraintSet.clone(projectDetailsConstraintLayout)
                constraintSet.connect(linkTextView.id, LEFT, R.id.leftGuideline, RIGHT)
                constraintSet.connect(linkTextView.id, RIGHT, R.id.rightGuideline, LEFT)
                constraintSet.connect(linkTextView.id, TOP, lastViewId, BOTTOM)
                constraintSet.constrainWidth(linkTextView.id, MATCH_CONSTRAINT)
                constraintSet.applyTo(projectDetailsConstraintLayout)

                lastViewId = linkTextView.id
            }
        }
    }
}