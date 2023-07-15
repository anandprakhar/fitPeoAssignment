package com.assesment.fitpeoassignment.networkCheck

import dagger.Component


@Component(modules = [DefaultOnlineChecker::class])
interface AppComponent {
    fun getDefaultOnlineChecker(): DefaultOnlineChecker
}