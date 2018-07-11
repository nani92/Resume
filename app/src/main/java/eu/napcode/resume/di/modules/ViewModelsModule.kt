package eu.napcode.resume.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import eu.napcode.resume.ui.developer.DeveloperViewModel
import eu.napcode.resume.ui.education.EducationViewModel

@Module
interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DeveloperViewModel::class)
    abstract fun bindDeveloperViewModel(developerViewModel: DeveloperViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EducationViewModel::class)
    abstract fun bindEducationViewModel(educationViewModel: EducationViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ResumeViewModelFactory): ViewModelProvider.Factory
}