package eu.napcode.resume.ui.developer

import android.arch.lifecycle.ViewModel
import eu.napcode.resume.repository.DeveloperRepository
import javax.inject.Inject

class DeveloperViewModel @Inject constructor(val developerRepository: DeveloperRepository): ViewModel() {

}
