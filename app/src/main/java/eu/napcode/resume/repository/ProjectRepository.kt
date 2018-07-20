package eu.napcode.resume.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import eu.napcode.resume.dao.ProjectDao
import eu.napcode.resume.entity.ProjectEntity
import eu.napcode.resume.model.Project
import eu.napcode.resume.rx.RxSchedulers
import io.reactivex.Completable
import javax.inject.Inject

class ProjectRepository
@Inject constructor(private val projectDao: ProjectDao, private val scheduler: RxSchedulers) {

    public fun saveProjects(projects: Array<Project>): Completable {
        return Completable
                .fromAction {
                    projects.forEach {
                        projectDao.insert(ProjectEntity(it))
                    }
                }
                .subscribeOn(scheduler.io())
    }

    public fun saveProject(project: Project): Completable {
        return Completable
                .fromAction { projectDao.insert(ProjectEntity(project)) }
                .subscribeOn(scheduler.io())
    }

    public fun getProjects(): LiveData<List<Project>> {
        return Transformations.map(projectDao.load()) { input ->
            input.mapIndexed { _, projectEntity -> Project(projectEntity) }
        }
    }
}