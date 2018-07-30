package eu.napcode.resume.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import eu.napcode.resume.entity.WorkEntity

@Dao
abstract class WorkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg work: WorkEntity)

    @Query("SELECT * FROM workentity ORDER BY startYear DESC, startMonth DESC")
    abstract fun load(): LiveData<List<WorkEntity>>
}
