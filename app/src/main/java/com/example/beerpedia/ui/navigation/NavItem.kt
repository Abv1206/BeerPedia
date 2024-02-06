package com.example.beerpedia.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem (
    val baseRoute: String,
    val navArgs: List<NavArg> = emptyList()
) {
    val route = run {
        val argKeys = navArgs.map { "{${it.key}}"}
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

    object Main : NavItem("main")
    object Detail : NavItem("detail", listOf(NavArg.BeerId)) {
        fun createNavRoute(beerId: Int) = "$baseRoute/$beerId"
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    BeerId("beerId", NavType.IntType)
}