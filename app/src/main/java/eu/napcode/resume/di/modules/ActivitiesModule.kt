package eu.napcode.resume.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.napcode.resume.MainActivity

@Module
interface ActivitiesModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}
