package eu.napcode.resume.model

import android.support.annotation.Nullable
import eu.napcode.resume.entity.WorkEntity

data class Work(

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

        val tech: Array<String>,

        @Nullable
        val logo: String?) {

    constructor(entity: WorkEntity) :
            this(
                    id = entity.id,
                    startYear = entity.startYear,
                    startMonth = entity.startMonth,
                    endYear = entity.endYear,
                    endMonth = entity.endMonth,
                    name = entity.name,
                    role = entity.role,
                    tech = entity.tech,
                    logo = entity.logo
            )
}