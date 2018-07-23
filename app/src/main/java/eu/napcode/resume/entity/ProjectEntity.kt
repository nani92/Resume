package eu.napcode.resume.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.Nullable
import eu.napcode.resume.model.Project
import eu.napcode.resume.model.ProjectType

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

        val tech: Array<String>,

        val type: ProjectType = ProjectType.OTHER,

        @Nullable
        val company: String?
) {
    constructor(project: Project) : this(
            id = project.id,
            startYear = project.startYear,
            startMonth = project.startMonth,
            name = project.name,
            description = project.description,
            tech = project.tech,
            type = project.type,
            company = project.company
    )
}


