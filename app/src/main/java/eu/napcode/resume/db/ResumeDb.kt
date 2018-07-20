package eu.napcode.resume.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import eu.napcode.resume.dao.DeveloperDao
import eu.napcode.resume.dao.EducationDao
import eu.napcode.resume.dao.ProjectDao
import eu.napcode.resume.entity.DeveloperEntity
import eu.napcode.resume.entity.EducationEntity
import eu.napcode.resume.entity.ProjectEntity
import eu.napcode.resume.utils.ProjectTypeConverters
import eu.napcode.resume.utils.TechConverters

@Database(
        entities = [
            DeveloperEntity::class,
            EducationEntity::class,
            ProjectEntity::class
        ],
        version = 1,
        exportSchema = false)
@TypeConverters(
        TechConverters::class,
        ProjectTypeConverters::class)
abstract class ResumeDb : RoomDatabase() {

    abstract fun developerDao(): DeveloperDao

    abstract fun educationDao(): EducationDao

    abstract fun projectDao(): ProjectDao
}
