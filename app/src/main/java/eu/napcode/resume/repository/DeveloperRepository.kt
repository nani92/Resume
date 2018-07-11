package eu.napcode.resume.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import eu.napcode.resume.dao.DeveloperDao
import eu.napcode.resume.entity.DeveloperEntity
import eu.napcode.resume.model.Developer
import eu.napcode.resume.rx.RxSchedulers
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject

class DeveloperRepository
@Inject constructor(private val developerDao: DeveloperDao, private val scheduler: RxSchedulers) {

    public fun getDeveloper(id: Int) : LiveData<Developer> {
        return Transformations.map(developerDao.load(id)) { Developer(it) }
    }

    public fun saveDeveloper(developer: Developer) : Completable {
        return Completable
                .fromAction{developerDao.insert(DeveloperEntity(developer))}
                .subscribeOn(scheduler.io())
    }
}