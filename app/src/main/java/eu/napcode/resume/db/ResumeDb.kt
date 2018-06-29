package eu.napcode.resume.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import eu.napcode.resume.dao.DeveloperDao

@Database(
        entities = [
            eu.napcode.resume.entity.DeveloperEntity::class
        ],
        version = 1,
        exportSchema = false)

abstract class ResumeDb : RoomDatabase() {
    abstract fun developerDao() : DeveloperDao
}
