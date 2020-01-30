package com.mobigod.domain.executors

import io.reactivex.Scheduler


/**
 * Thread to deliver the results after work
 */
interface PostExecutionThread {
    val scheduler: Scheduler
}