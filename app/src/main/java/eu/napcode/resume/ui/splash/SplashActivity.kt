package eu.napcode.resume.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import eu.napcode.developerdataprovider.LocalDataProvider
import eu.napcode.resume.model.Developer
import eu.napcode.resume.repository.DeveloperRepository
import eu.napcode.resume.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var developerRepository : DeveloperRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        loadProvidedDeveloperData()

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun loadProvidedDeveloperData() {
        var dev = LocalDataProvider(this).getDeveloper(Developer::class.java)
        developerRepository.saveDeveloper(dev as Developer).subscribe()
    }
}