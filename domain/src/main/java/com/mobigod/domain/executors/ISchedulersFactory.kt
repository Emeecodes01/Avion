package com.mobigod.domain.executors

import io.reactivex.Scheduler

interface ISchedulersFactory {
    fun io(): Scheduler

    fun computation(): Scheduler

    fun ui(): Scheduler
}