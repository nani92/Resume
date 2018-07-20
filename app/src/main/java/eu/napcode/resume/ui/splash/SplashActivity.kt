package eu.napcode.resume.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import eu.napcode.developerdataprovider.LocalDataProvider
import eu.napcode.resume.model.Developer
import eu.napcode.resume.model.Education
import eu.napcode.resume.model.Project
import eu.napcode.resume.repository.DeveloperRepository
import eu.napcode.resume.ui.main.MainActivity
import javax.inject.Inject
import eu.napcode.resume.repository.EducationRepository
import eu.napcode.resume.repository.ProjectRepository

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var developerRepository : DeveloperRepository

    @Inject
    lateinit var educationRepository : EducationRepository

    @Inject
    lateinit var projectRepository: ProjectRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        loadProvidedDeveloperData()
        loadProvidedEducationData()
        loadProvidedProjectData()

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun loadProvidedDeveloperData() {
        var dev = LocalDataProvider(this).getDeveloper(Developer::class.java)
        developerRepository.saveDeveloper(dev as Developer).subscribe()
    }

    private fun loadProvidedEducationData() {
        var edu = LocalDataProvider(this).getEducations(Array<Education>::class.java)

        educationRepository.saveEducations(edu as Array<Education>).subscribe()
    }

    private fun loadProvidedProjectData() {
        var projects = LocalDataProvider(this).getProjects(Array<Project>::class.java)

        projectRepository.saveProjects(projects as Array<Project>)
    }
}