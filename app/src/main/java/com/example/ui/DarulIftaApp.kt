package com.example.ui

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.localization.AppStrings
import com.example.ui.components.IslamicTopAppBar
import com.example.ui.screens.AskFatwaScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.DailyPostDetailScreen
import com.example.ui.screens.DailyPostsScreen
import com.example.ui.screens.FatwaDetailScreen
import com.example.ui.screens.FatwasScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MethodologyScreen
import com.example.ui.screens.PdfViewerScreen
import com.example.ui.screens.ScholarDetailScreen
import com.example.ui.screens.ScholarsScreen
import com.example.ui.screens.ServicesScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.theme.IslamicGoldBright
import com.example.ui.theme.IslamicGoldLight
import com.example.ui.theme.IslamicNavyDark
import com.example.ui.theme.IslamicNavyMedium
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextOnDark

object Destinations {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val FATWAS = "fatwas"
    const val FATWA_DETAIL = "fatwa_detail/{slug}"
    const val DAILY_POSTS = "daily_posts"
    const val DAILY_POST_DETAIL = "daily_post_detail/{slug}"
    const val SCHOLARS = "scholars"
    const val SCHOLAR_DETAIL = "scholar_detail/{slug}"
    const val SERVICES = "services"
    const val CONTACT = "contact"
    const val ASK_FATWA = "ask_fatwa"
    const val METHODOLOGY = "methodology"
    const val PDF_VIEWER = "pdf_viewer?url={url}&title={title}"

    fun fatwaDetail(slug: String) = "fatwa_detail/$slug"
    fun dailyPostDetail(slug: String) = "daily_post_detail/$slug"
    fun scholarDetail(slug: String) = "scholar_detail/$slug"
    fun pdfViewer(url: String, title: String): String {
        val encodedUrl = Uri.encode(url)
        val encodedTitle = Uri.encode(title)
        return "pdf_viewer?url=$encodedUrl&title=$encodedTitle"
    }
}

@Composable
fun DarulIftaApp(
    viewModel: DarulIftaViewModel = viewModel()
) {
    val navController = rememberNavController()
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val layoutDirection = if (currentLanguage.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        val bottomNavRoutes = setOf(
            Destinations.HOME,
            Destinations.FATWAS,
            Destinations.DAILY_POSTS,
            Destinations.SCHOLARS,
            Destinations.CONTACT
        )
        val showBottomBar = currentRoute in bottomNavRoutes
        val showTopBar = currentRoute in bottomNavRoutes

        Scaffold(
            topBar = {
                if (showTopBar) {
                    val title = when (currentRoute) {
                        Destinations.FATWAS -> AppStrings.navFatwas(currentLanguage)
                        Destinations.DAILY_POSTS -> AppStrings.navDailyPosts(currentLanguage)
                        Destinations.SCHOLARS -> AppStrings.navScholars(currentLanguage)
                        Destinations.CONTACT -> AppStrings.navContact(currentLanguage)
                        else -> null
                    }
                    IslamicTopAppBar(
                        currentLanguage = currentLanguage,
                        title = title,
                        onLanguageSelected = { viewModel.setLanguage(it) }
                    )
                }
            },
            bottomBar = {
                if (showBottomBar) {
                    NavigationBar(
                        containerColor = IslamicNavyDark,
                        contentColor = TextOnDark
                    ) {
                        // Home
                        NavigationBarItem(
                            selected = currentRoute == Destinations.HOME,
                            onClick = {
                                navController.navigate(Destinations.HOME) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
                            label = { Text(AppStrings.navHome(currentLanguage), fontSize = 11.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = IslamicGoldBright,
                                selectedTextColor = IslamicGoldLight,
                                indicatorColor = IslamicNavyMedium,
                                unselectedIconColor = TextMuted,
                                unselectedTextColor = TextMuted
                            ),
                            modifier = Modifier.testTag("nav_item_home")
                        )

                        // Fatwas
                        NavigationBarItem(
                            selected = currentRoute == Destinations.FATWAS,
                            onClick = {
                                navController.navigate(Destinations.FATWAS) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(imageVector = Icons.Default.MenuBook, contentDescription = "Fatwas") },
                            label = { Text(AppStrings.navFatwas(currentLanguage), fontSize = 11.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = IslamicGoldBright,
                                selectedTextColor = IslamicGoldLight,
                                indicatorColor = IslamicNavyMedium,
                                unselectedIconColor = TextMuted,
                                unselectedTextColor = TextMuted
                            ),
                            modifier = Modifier.testTag("nav_item_fatwas")
                        )

                        // Daily Posts
                        NavigationBarItem(
                            selected = currentRoute == Destinations.DAILY_POSTS,
                            onClick = {
                                navController.navigate(Destinations.DAILY_POSTS) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(imageVector = Icons.Default.Article, contentDescription = "Daily Posts") },
                            label = { Text(AppStrings.navDailyPosts(currentLanguage), fontSize = 11.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = IslamicGoldBright,
                                selectedTextColor = IslamicGoldLight,
                                indicatorColor = IslamicNavyMedium,
                                unselectedIconColor = TextMuted,
                                unselectedTextColor = TextMuted
                            ),
                            modifier = Modifier.testTag("nav_item_daily_posts")
                        )

                        // Scholars
                        NavigationBarItem(
                            selected = currentRoute == Destinations.SCHOLARS,
                            onClick = {
                                navController.navigate(Destinations.SCHOLARS) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(imageVector = Icons.Default.People, contentDescription = "Scholars") },
                            label = { Text(AppStrings.navScholars(currentLanguage), fontSize = 11.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = IslamicGoldBright,
                                selectedTextColor = IslamicGoldLight,
                                indicatorColor = IslamicNavyMedium,
                                unselectedIconColor = TextMuted,
                                unselectedTextColor = TextMuted
                            ),
                            modifier = Modifier.testTag("nav_item_scholars")
                        )

                        // Contact
                        NavigationBarItem(
                            selected = currentRoute == Destinations.CONTACT,
                            onClick = {
                                navController.navigate(Destinations.CONTACT) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(imageVector = Icons.Default.ContactPhone, contentDescription = "Contact") },
                            label = { Text(AppStrings.navContact(currentLanguage), fontSize = 11.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = IslamicGoldBright,
                                selectedTextColor = IslamicGoldLight,
                                indicatorColor = IslamicNavyMedium,
                                unselectedIconColor = TextMuted,
                                unselectedTextColor = TextMuted
                            ),
                            modifier = Modifier.testTag("nav_item_contact")
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Destinations.SPLASH,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                composable(Destinations.SPLASH) {
                    SplashScreen(
                        currentLanguage = currentLanguage,
                        onSplashFinished = {
                            navController.navigate(Destinations.HOME) {
                                popUpTo(Destinations.SPLASH) { inclusive = true }
                            }
                        }
                    )
                }

                composable(Destinations.HOME) {
                    HomeScreen(
                        viewModel = viewModel,
                        onNavigateToFatwas = { navController.navigate(Destinations.FATWAS) },
                        onNavigateToFatwaDetail = { slug -> navController.navigate(Destinations.fatwaDetail(slug)) },
                        onNavigateToAskFatwa = { navController.navigate(Destinations.ASK_FATWA) },
                        onNavigateToDailyPosts = { navController.navigate(Destinations.DAILY_POSTS) },
                        onNavigateToDailyPostDetail = { slug -> navController.navigate(Destinations.dailyPostDetail(slug)) },
                        onNavigateToScholars = { navController.navigate(Destinations.SCHOLARS) },
                        onNavigateToScholarDetail = { slug -> navController.navigate(Destinations.scholarDetail(slug)) },
                        onNavigateToServices = { navController.navigate(Destinations.SERVICES) },
                        onNavigateToMethodology = { navController.navigate(Destinations.METHODOLOGY) }
                    )
                }

                composable(Destinations.FATWAS) {
                    FatwasScreen(
                        viewModel = viewModel,
                        onNavigateToFatwaDetail = { slug -> navController.navigate(Destinations.fatwaDetail(slug)) }
                    )
                }

                composable(
                    route = Destinations.FATWA_DETAIL,
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backStackEntry ->
                    val slug = backStackEntry.arguments?.getString("slug") ?: ""
                    FatwaDetailScreen(
                        slug = slug,
                        viewModel = viewModel,
                        onBack = { navController.popBackStack() },
                        onViewPdf = { url, fatwaTitle ->
                            navController.navigate(Destinations.pdfViewer(url, fatwaTitle))
                        }
                    )
                }

                composable(
                    route = Destinations.PDF_VIEWER,
                    arguments = listOf(
                        navArgument("url") { type = NavType.StringType },
                        navArgument("title") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val pdfUrl = backStackEntry.arguments?.getString("url") ?: ""
                    val pdfTitle = backStackEntry.arguments?.getString("title") ?: ""
                    PdfViewerScreen(
                        pdfUrl = pdfUrl,
                        title = pdfTitle,
                        viewModel = viewModel,
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(Destinations.DAILY_POSTS) {
                    DailyPostsScreen(
                        viewModel = viewModel,
                        onNavigateToPostDetail = { slug -> navController.navigate(Destinations.dailyPostDetail(slug)) }
                    )
                }

                composable(
                    route = Destinations.DAILY_POST_DETAIL,
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backStackEntry ->
                    val slug = backStackEntry.arguments?.getString("slug") ?: ""
                    DailyPostDetailScreen(
                        slug = slug,
                        viewModel = viewModel,
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(Destinations.SCHOLARS) {
                    ScholarsScreen(
                        viewModel = viewModel,
                        onNavigateToScholarDetail = { slug -> navController.navigate(Destinations.scholarDetail(slug)) }
                    )
                }

                composable(
                    route = Destinations.SCHOLAR_DETAIL,
                    arguments = listOf(navArgument("slug") { type = NavType.StringType })
                ) { backStackEntry ->
                    val slug = backStackEntry.arguments?.getString("slug") ?: ""
                    ScholarDetailScreen(
                        slug = slug,
                        viewModel = viewModel,
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(Destinations.SERVICES) {
                    ServicesScreen(
                        viewModel = viewModel,
                        onNavigateToAskFatwa = { navController.navigate(Destinations.ASK_FATWA) },
                        onNavigateToFatwas = { navController.navigate(Destinations.FATWAS) }
                    )
                }

                composable(Destinations.CONTACT) {
                    ContactScreen(
                        viewModel = viewModel,
                        onNavigateToAskFatwa = { navController.navigate(Destinations.ASK_FATWA) }
                    )
                }

                composable(Destinations.ASK_FATWA) {
                    AskFatwaScreen(
                        viewModel = viewModel,
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(Destinations.METHODOLOGY) {
                    MethodologyScreen(
                        viewModel = viewModel,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
