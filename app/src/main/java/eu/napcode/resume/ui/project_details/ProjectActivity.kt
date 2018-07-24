package eu.napcode.resume.ui.project_details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import eu.napcode.resume.R
import eu.napcode.resume.model.Project

class ProjectActivity : AppCompatActivity() {

    companion object {
        public const val ARG_PROJECT= "project"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        AndroidInjection.inject(this)

        var project = intent.getParcelableExtra<Project>(ARG_PROJECT)
    }
}