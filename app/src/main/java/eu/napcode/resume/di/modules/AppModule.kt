package eu.napcode.resume.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import eu.napcode.resume.dao.DeveloperDao
import eu.napcode.resume.db.ResumeDb
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): ResumeDb {

        return Room
                .databaseBuilder(app, ResumeDb::class.java, "resume.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideDeveloperDao(db: ResumeDb): DeveloperDao {
        return db.developerDao()
    }
}