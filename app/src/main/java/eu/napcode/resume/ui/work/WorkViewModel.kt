package eu.napcode.resume.ui.work

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import eu.napcode.resume.model.Work
import eu.napcode.resume.repository.WorkRepository
import javax.inject.Inject

class WorkViewModel
@Inject constructor(workRepository: WorkRepository) : ViewModel() {

    val works : LiveData<List<Work>> = workRepository.getWorks()
}