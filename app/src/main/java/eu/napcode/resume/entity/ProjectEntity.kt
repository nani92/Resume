package eu.napcode.resume.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.Nullable

@Entity
data class ProjectEntity(

        @PrimaryKey
        val id: Int,

        @Nullable
        val startYear: Int?,

        @Nullable
        val startMonth: Int?,

        @Nullable
        val name: String?,

        @Nullable
        val description: String?,

        @Nullable
        val tech : Array<String>?
)


