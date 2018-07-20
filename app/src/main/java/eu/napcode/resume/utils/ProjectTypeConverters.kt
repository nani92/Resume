package eu.napcode.resume.utils

import android.arch.persistence.room.TypeConverter
import eu.napcode.resume.model.ProjectType

class ProjectTypeConverters {

    @TypeConverter
    fun toProjectType(ordinal: Int): ProjectType {
        return ProjectType.values()[ordinal]
    }

    @TypeConverter
    fun fromProjectType(projectType: ProjectType): Int? {
        return projectType.ordinal
    }
}
