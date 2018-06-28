package eu.napcode.resume.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.napcode.resume.ui.main.MainActivity

@Module
interface ActivitiesModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}
