package eu.napcode.resume.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.napcode.resume.ui.main.MainActivity
import eu.napcode.resume.ui.splash.SplashActivity

@Module
interface ActivitiesModule {

    @ContributesAndroidInjector
    fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}
