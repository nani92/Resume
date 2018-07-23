package eu.napcode.resume.utils

import android.content.Context
import eu.napcode.resume.R
import eu.napcode.resume.model.ProjectType

fun getProjectTypeString(context: Context, projectType: ProjectType) : String {

    return when(projectType) {

        ProjectType.OWN -> context.getString(R.string.project_own)
        ProjectType.COMMERCIAL -> context.getString(R.string.project_commercial)
        ProjectType.OPEN_SOURCE -> context.getString(R.string.project_opens)
        ProjectType.OTHER -> context.getString(R.string.project_other)
    }
}
