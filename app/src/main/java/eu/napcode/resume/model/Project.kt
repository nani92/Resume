package eu.napcode.resume.model

import android.support.annotation.Nullable
import eu.napcode.resume.entity.ProjectEntity

data class Project(

        val id: Int,

        @Nullable
        val startYear: Int?,

        @Nullable
        val startMonth: Int?,

        @Nullable
        val name: String?,

        @Nullable
        val description: String?,

        val tech: Array<String>,

        val type: ProjectType = ProjectType.OTHER,

        @Nullable
        val company: String?) {

    constructor(projectEntity: ProjectEntity) : this(
            id = projectEntity.id,
            startYear = projectEntity.startYear,
            startMonth = projectEntity.startMonth,
            name = projectEntity.name,
            description = projectEntity.description,
            tech = projectEntity.tech,
            type = projectEntity.type,
            company = projectEntity.company
    )
}
