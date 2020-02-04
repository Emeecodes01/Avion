package com.mobigod.avin.rx

import com.mobigod.domain.executors.ISchedulersFactory
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 20:25*/
class AppSchedulers: ISchedulersFactory {

    override fun io() = Schedulers.io()

    override fun computation() = Schedulers.computation()

    override fun ui() = AndroidSchedulers.mainThread()
}