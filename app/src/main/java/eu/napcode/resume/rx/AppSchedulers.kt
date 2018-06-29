package eu.napcode.resume.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AppSchedulers : RxSchedulers {

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun androidMainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}