package eu.napcode.resume.di.componets

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import eu.napcode.resume.ResumeApp
import eu.napcode.resume.di.modules.ActivitiesModule
import eu.napcode.resume.di.modules.AppModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,
            ActivitiesModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(githubApp: ResumeApp)
}