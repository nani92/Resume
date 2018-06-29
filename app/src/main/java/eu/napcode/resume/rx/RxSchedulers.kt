package eu.napcode.resume.rx

import io.reactivex.Scheduler

interface RxSchedulers {

    fun io(): Scheduler

    fun androidMainThread(): Scheduler
}