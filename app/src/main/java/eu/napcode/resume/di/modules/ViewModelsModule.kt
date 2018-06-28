package eu.napcode.resume.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import eu.napcode.resume.ui.developer.DeveloperViewModel

@Module
interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DeveloperViewModel::class)
    abstract fun bindDeveloperViewModel(developerViewModel: DeveloperViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ResumeViewModelFactory): ViewModelProvider.Factory
}