package eu.napcode.resume.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.napcode.resume.ui.developer.DeveloperFragment

@Module
interface FragmentsModule {

    @ContributesAndroidInjector
    fun bindDeveloperFragment(): DeveloperFragment
}