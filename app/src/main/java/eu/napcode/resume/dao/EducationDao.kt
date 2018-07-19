package eu.napcode.resume.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import eu.napcode.resume.entity.EducationEntity

@Dao
abstract class EducationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg education: EducationEntity)

    @Query("SELECT * FROM educationentity ORDER BY endYear DESC")
    abstract fun load(): LiveData<List<EducationEntity>>
}
