package eu.napcode.resume.ui.developer

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import eu.napcode.resume.BuildConfig
import eu.napcode.resume.model.Developer
import eu.napcode.resume.repository.DeveloperRepository
import javax.inject.Inject

class DeveloperViewModel
@Inject constructor(private val developerRepository: DeveloperRepository) : ViewModel() {

    val developer : LiveData<Developer> = developerRepository.getDeveloper(BuildConfig.developerId)
}
