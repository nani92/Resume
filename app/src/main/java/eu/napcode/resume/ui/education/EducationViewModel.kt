package eu.napcode.resume.ui.education

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import eu.napcode.resume.BuildConfig
import eu.napcode.resume.model.Developer
import eu.napcode.resume.model.Education
import eu.napcode.resume.repository.EducationRepository
import javax.inject.Inject

class EducationViewModel
@Inject constructor(educationRepository: EducationRepository) : ViewModel() {

    val educations : LiveData<List<Education>> = educationRepository.getEducations()
}