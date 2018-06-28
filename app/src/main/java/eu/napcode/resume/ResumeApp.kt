package eu.napcode.resume

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import eu.napcode.resume.di.componets.DaggerAppComponent

class ResumeApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        setupDagger()
    }

    private fun setupDagger() {
        DaggerAppComponent.
                builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = this.activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = this.fragmentInjector
}
