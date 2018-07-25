package eu.napcode.resume.ui.project_details

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import eu.napcode.resume.R
import eu.napcode.resume.model.Project
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

        appBarProjectTitleTextView.text = project.name
        projectTitleTextView.text = project.name

        var begin = (topGuideline.layoutParams as ConstraintLayout.LayoutParams).guideBegin
        topGuideline.setGuidelineBegin(begin + resources.getDimension(R.dimen.base_margin).toInt())
    }
}