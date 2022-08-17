package com.example.modular_main

import com.example.core.CoreNavigation

interface ModularMainNavigation : CoreNavigation {

    fun onLogout(activity: MainActivity)

}