package eu.napcode.resume.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.Nullable
import eu.napcode.resume.model.Work

@Entity
data class WorkEntity(

        @PrimaryKey
        val id: Int,

        @Nullable
        val startYear: Int?,

        @Nullable
        val startMonth: Int?,

        @Nullable
        val endYear: Int?,

        @Nullable
        val endMonth: Int?,

        @Nullable
        val name: String?,

        @Nullable
        val role: String?,

        val tech: Array<String>
) {
    constructor(work: Work) :
            this(
                    id = work.id,
                    name = work.name,
                    role = work.role,
                    startYear = work.startYear,
                    startMonth = work.startMonth,
                    endMonth = work.endMonth,
                    endYear = work.endYear,
                    tech = work.tech
            )
}


