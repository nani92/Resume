package eu.napcode.resume.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import eu.napcode.resume.dao.EducationDao
import eu.napcode.resume.entity.EducationEntity
import eu.napcode.resume.model.Education
import eu.napcode.resume.rx.RxSchedulers
import io.reactivex.Completable
import javax.inject.Inject

class EducationRepository
@Inject constructor(private val educationDao: EducationDao, private val scheduler: RxSchedulers) {

    public fun saveEducation(education: Education): Completable {
        return Completable
                .fromAction { educationDao.insert(EducationEntity(education)) }
                .subscribeOn(scheduler.io())
    }

    public fun getEducations(): LiveData<List<Education>> {
        return Transformations.map(educationDao.load()) { input ->
            input.mapIndexed{ _, educationEntity -> Education(educationEntity) }
        }
    }
}