package eu.napcode.resume.model

import android.support.annotation.Nullable
import eu.napcode.resume.entity.EducationEntity

data class Education(val id: Int,
                     @Nullable
                     val startYear: Int?,
                     @Nullable
                     val startMonth: Int?,
                     @Nullable
                     val endYear: Int?,
                     @Nullable
                     val endMonth: Int?,
                     @Nullable
                     val field: String?,
                     val school: String,
                     @Nullable
                     val title: String?) {

    constructor(entity: EducationEntity) :
            this(entity.id, entity.startYear, entity.startMonth, entity.endYear,
                    entity.endMonth, entity.field, entity.school, entity.title)
}