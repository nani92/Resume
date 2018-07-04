package eu.napcode.resume.di.modules

import dagger.Module
import dagger.Provides
import eu.napcode.resume.rx.AppSchedulers
import eu.napcode.resume.rx.RxSchedulers

@Module
class RxModule {

    @Provides
    internal fun provideRxSchedulers(): RxSchedulers {
        return AppSchedulers()
    }
}
