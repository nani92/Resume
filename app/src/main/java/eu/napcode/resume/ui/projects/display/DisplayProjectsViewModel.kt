package eu.napcode.resume.ui.projects.display

import android.arch.lifecycle.ViewModel
import eu.napcode.resume.model.ProjectType
import eu.napcode.resume.model.ProjectType.ALL
import eu.napcode.resume.repository.ProjectRepository
import javax.inject.Inject

class DisplayProjectsViewModel
@Inject constructor(var projectRepository: ProjectRepository) : ViewModel() {

    fun getProjects(projectType: ProjectType) =
            if (projectType == ALL) {
                projectRepository.getProjects()
            } else {
                projectRepository.getProjectsByType(projectType)
            }
}