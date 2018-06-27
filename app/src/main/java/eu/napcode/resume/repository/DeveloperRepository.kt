package eu.napcode.resume.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import eu.napcode.resume.dao.DeveloperDao
import eu.napcode.resume.model.Developer
import javax.inject.Inject

class DeveloperRepository @Inject constructor(private val developerDao: DeveloperDao) {

    public fun getDeveloper(id: Int) : LiveData<Developer> {
        return Transformations.map(developerDao.load(id), { Developer(it) })
    }

}