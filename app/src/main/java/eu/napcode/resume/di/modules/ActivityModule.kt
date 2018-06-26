package eu.napcode.resume.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.napcode.resume.MainActivity

@Module
interface ActivityModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}
