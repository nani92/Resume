package eu.napcode.resume.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import eu.napcode.resume.entity.ProjectEntity

@Dao
abstract class ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg projectEntity: ProjectEntity)

    @Query("SELECT * FROM projectentity ORDER BY startYear, startMonth DESC")
    abstract fun load(): LiveData<List<ProjectEntity>>
}
