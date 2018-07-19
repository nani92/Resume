package eu.napcode.resume.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import eu.napcode.resume.dao.DeveloperDao
import eu.napcode.resume.dao.EducationDao
import eu.napcode.resume.dao.ProjectDao

@Database(
        entities = [
            eu.napcode.resume.entity.DeveloperEntity::class,
            eu.napcode.resume.entity.EducationEntity::class
        ],
        version = 1,
        exportSchema = false)

abstract class ResumeDb : RoomDatabase() {
    abstract fun developerDao(): DeveloperDao
    abstract fun educationDao(): EducationDao
    abstract fun projectDao() : ProjectDao
}
