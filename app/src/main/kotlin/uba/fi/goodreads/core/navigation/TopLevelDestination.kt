package uba.fi.goodreads.core.navigation

import androidx.annotation.DrawableRes
import uba.fi.goodreads.R

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
        titleTextId = R.string.search_section_title,
    ),
    PROFILE(
        selectedIcon = R.drawable.ic_avatar_fill,
        unselectedIcon = R.drawable.ic_avatar_fill,
        titleTextId = R.string.profile_bottom_nav_title,
    ),
}