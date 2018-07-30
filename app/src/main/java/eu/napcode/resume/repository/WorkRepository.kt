package eu.napcode.resume.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import eu.napcode.resume.dao.WorkDao
import eu.napcode.resume.entity.WorkEntity
import eu.napcode.resume.model.Work
import eu.napcode.resume.rx.RxSchedulers
import io.reactivex.Completable
import javax.inject.Inject

class WorkRepository
@Inject constructor(private val workDao: WorkDao, private val scheduler: RxSchedulers) {

    public fun saveWorks(works: Array<Work>): Completable {
        return Completable
                .fromAction { works.forEach { workDao.insert(WorkEntity(it)) } }
                .subscribeOn(scheduler.io())
    }

    public fun getWorks(): LiveData<List<Work>> {
        return Transformations.map(workDao.load()) { input ->
            input.mapIndexed { _, workEntity -> Work(workEntity) }
        }
    }
}