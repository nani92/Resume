package eu.napcode.resume.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import eu.napcode.resume.entity.DeveloperEntity

@Dao
abstract class DeveloperDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg developer: DeveloperEntity)

    @Query("SELECT * FROM developerentity WHERE id = :id")
    abstract fun load(id: Int): LiveData<DeveloperEntity>
}
