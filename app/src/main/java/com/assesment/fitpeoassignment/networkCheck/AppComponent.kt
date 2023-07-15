package com.assesment.fitpeoassignment.networkCheck

import dagger.Component


@Component
interface AppComponent {
    fun getDefaultOnlineChecker(): DefaultOnlineChecker
}