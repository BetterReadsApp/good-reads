package uba.fi.goodreads.core.navigation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import uba.fi.goodreads.R
import uba.fi.goodreads.core.design_system.icon.GoodReadsIcon

enum class TopLevelDestination(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = R.drawable.ic_home_4_fill,
        unselectedIcon = R.drawable.ic_home_4_line,
        titleTextId = R.string.home_bottom_nav_title,
    ),
    MY_BOOKS(
        selectedIcon = R.drawable.ic_notebook_2_fill,
        unselectedIcon = R.drawable.ic_notebook_2_line,
        titleTextId = R.string.my_books_bottom_nav_title,
    ),
    DISCOVER(
        selectedIcon = R.drawable.ic_notebook_2_line,
        unselectedIcon = R.drawable.ic_notebook_2_line,
        titleTextId = R.string.discover_bottom_nav_title,
    ),
    SEARCH(
        selectedIcon = R.drawable.ic_notebook_2_line,
        unselectedIcon = R.drawable.ic_notebook_2_line,
        titleTextId = R.string.search_bottom_nav_title,
    ),
}