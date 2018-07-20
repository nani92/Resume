package eu.napcode.resume.ui.projects

import android.arch.lifecycle.ViewModel
import eu.napcode.resume.model.ProjectType
import eu.napcode.resume.repository.ProjectRepository
import javax.inject.Inject

class DisplayProjectsViewModel
@Inject constructor(var projectRepository: ProjectRepository) : ViewModel() {

    fun getProjects(projectType: ProjectType) = projectRepository.getProjects()
}