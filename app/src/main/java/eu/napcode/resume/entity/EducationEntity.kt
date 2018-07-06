package eu.napcode.resume.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.Nullable
import eu.napcode.resume.model.Education

@Entity
data class EducationEntity(

        @PrimaryKey
        val id: Int,

        @Nullable
        val startYear: Int,

        @Nullable
        val startMonth: Int,

        @Nullable
        val endYear: Int,

        @Nullable
        val endMonth: Int,

        @Nullable
        val field: String,

        val school: String,

        @Nullable
        val title: String
) {
        constructor(education: Education) :
                this(education.id, education.startYear, education.startMonth, education.endYear,
                        education.endMonth, education.field, education.school, education.title)
}


