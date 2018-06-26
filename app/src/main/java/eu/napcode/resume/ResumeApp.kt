package eu.napcode.resume

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

import dagger.android.HasActivityInjector
import eu.napcode.resume.di.componets.DaggerAppComponent

class ResumeApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        setupDagger()
    }

    fun setupDagger() {
        DaggerAppComponent.
                builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = this.dispatchingAndroidInjector
}
