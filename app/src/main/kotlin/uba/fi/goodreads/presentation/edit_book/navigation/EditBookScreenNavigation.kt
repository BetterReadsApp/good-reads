package uba.fi.goodreads.presentation.add_book.navigation
//
//import androidx.navigation.NavController
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavOptions
//import androidx.navigation.compose.composable
//import androidx.navigation.navDeepLink
//import uba.fi.goodreads.presentation.add_book.composables.AddBookRoute
//import uba.fi.goodreads.presentation.review.composables.ReviewRoute
//
//const val EDITBOOKSCREEN_ROUTE = "profile/add_book"
//private const val DEEP_LINK_URI_PATTERN =
//    "goodReads://profile/add_book"
//
//fun NavController.navigateToAddBook(navOptions: NavOptions? = null) = navigate(
//    "profile/add_book", navOptions
//)
//
//fun NavGraphBuilder.addBookScreen(
//    onBack: () -> Unit,
//) {
//    composable(
//        route = ADDBOOKSCREEN_ROUTE,
//        deepLinks = listOf(
//            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN })
//    ) { navBackResult ->
//        //val bookId = navBackResult.arguments?.getString("bookId") ?: ""
//        AddBookRoute(
//            navigate = { destination ->
//                navigate(
//                    destination = destination,
//                    onBack = onBack
//                )
//            }
//        )
//    }
//}
//
//internal fun navigate(
//    destination: AddBookDestination,
//    onBack: () -> Unit,
//) {
//    when (destination) {
//        is AddBookDestination.Back -> onBack()
//    }
//}
