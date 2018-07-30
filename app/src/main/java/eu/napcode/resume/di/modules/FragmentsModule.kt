package eu.napcode.resume.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import eu.napcode.resume.ui.developer.DeveloperFragment
import eu.napcode.resume.ui.education.EducationFragment
import eu.napcode.resume.ui.projects.display.DisplayProjectsFragment
import eu.napcode.resume.ui.work.WorkFragment

@Module
interface FragmentsModule {

    @ContributesAndroidInjector
    fun bindDeveloperFragment(): DeveloperFragment

    @ContributesAndroidInjector
    fun bindEducationFragment(): EducationFragment

    @ContributesAndroidInjector
    fun bindDisplayProjectsFragment(): DisplayProjectsFragment

    @ContributesAndroidInjector
    fun bindWorkFragment(): WorkFragment
}