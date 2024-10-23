package uba.fi.goodreads.core.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import uba.fi.goodreads.R
import uba.fi.goodreads.core.design_system.icon.GoodReadsIcon

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = GoodReadsIcon.Home,
        unselectedIcon = GoodReadsIcon.Home,
        titleTextId = R.string.home_bottom_nav_title,
    ),
    SHELVES(
        selectedIcon = GoodReadsIcon.Home,
        unselectedIcon = GoodReadsIcon.Home,
        titleTextId = R.string.shelves_bottom_nav_title,
    ),
    DISCOVER(
        selectedIcon = GoodReadsIcon.Home,
        unselectedIcon = GoodReadsIcon.Home,
        titleTextId = R.string.discover_bottom_nav_title,
    ),
    SEARCH(
        selectedIcon = GoodReadsIcon.Home,
        unselectedIcon = GoodReadsIcon.Home,
        titleTextId = R.string.search_bottom_nav_title,
    ),
}