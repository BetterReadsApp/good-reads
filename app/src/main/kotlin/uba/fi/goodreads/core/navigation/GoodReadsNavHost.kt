package uba.fi.goodreads.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import uba.fi.goodreads.core.ui.GoodReadsAppState
import uba.fi.goodreads.presentation.book_info.navigation.bookInfoScreen
import uba.fi.goodreads.presentation.book_info.navigation.navigateToBookInfo
import uba.fi.goodreads.presentation.review.navigation.navigateToReviewScreen
import uba.fi.goodreads.presentation.home.navigation.HOME_ROUTE
import uba.fi.goodreads.presentation.home.navigation.homeScreen
import uba.fi.goodreads.presentation.profile.navigation.navigateToProfile
import uba.fi.goodreads.presentation.profile.navigation.profileScreen
import uba.fi.goodreads.presentation.shelves.shelfBooksScreen.navigation.navigateToShelfBooks
import uba.fi.goodreads.presentation.shelves.shelfBooksScreen.navigation.shelfBooksScreen
import uba.fi.goodreads.presentation.shelves.shelvesScreen.navigation.shelvesScreen
import uba.fi.goodreads.presentation.search.navigation.searchScreen
import uba.fi.goodreads.presentation.review.navigation.reviewScreen
import uba.fi.goodreads.presentation.shelves.add_book.navigation.addBookToShelvesScreen
import uba.fi.goodreads.presentation.shelves.add_book.navigation.navigateToAddBookToShelves

@Composable
fun GoodReadsNavHost(
    appState: GoodReadsAppState,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(
            navigateToBook = navController::navigateToBookInfo
        )

        shelvesScreen(
            navigateToShelfBooks = navController::navigateToShelfBooks
        )

        shelfBooksScreen(
            onBack = navController::popBackStack,
            onBookInfo = navController::navigateToBookInfo
        )

        bookInfoScreen(
            navigateToReview = navController::navigateToReviewScreen,
            navigateToAddBookToShelf = navController::navigateToAddBookToShelves
        )

        profileScreen(
            onBack = navController::popBackStack
        )

        searchScreen(
            navigateToBook = navController::navigateToBookInfo,
            navigateToProfile = navController::navigateToProfile
        )

        reviewScreen(
            onBack = navController::popBackStack
        )

        addBookToShelvesScreen(
            onBack = navController::popBackStack
        )
    }
}
