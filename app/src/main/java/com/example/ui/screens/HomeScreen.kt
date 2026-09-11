package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.localization.AppStrings
import com.example.model.AppLanguage
import com.example.model.DailyPost
import com.example.model.Fatwa
import com.example.model.Scholar
import com.example.ui.DarulIftaViewModel
import com.example.ui.components.DailyPostCard
import com.example.ui.components.DecorativeGoldDivider
import com.example.ui.components.DonationCard
import com.example.ui.components.FatwaCard
import com.example.ui.components.OfflineBanner
import com.example.ui.components.ScholarCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.CardWhite
import com.example.ui.theme.IslamicBlue
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldBright
import com.example.ui.theme.IslamicGoldLight
import com.example.ui.theme.IslamicNavyDark
import com.example.ui.theme.IslamicNavyMedium
import com.example.ui.theme.ParchmentBorder
import com.example.ui.theme.ParchmentSurface
import com.example.ui.theme.TextOnDark
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun HomeScreen(
    viewModel: DarulIftaViewModel,
    onNavigateToFatwas: () -> Unit,
    onNavigateToFatwaDetail: (String) -> Unit,
    onNavigateToAskFatwa: () -> Unit,
    onNavigateToDailyPosts: () -> Unit,
    onNavigateToDailyPostDetail: (String) -> Unit,
    onNavigateToScholars: () -> Unit,
    onNavigateToScholarDetail: (String) -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToMethodology: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val fatwas by viewModel.fatwas.collectAsState()
    val scholars by viewModel.scholars.collectAsState()
    val dailyPosts by viewModel.dailyPosts.collectAsState()
    val isOnline by viewModel.isOnline.collectAsState()
    val bookmarkedSlugs by viewModel.bookmarkedSlugs.collectAsState()
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // Hero Section
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(IslamicNavyDark, IslamicNavyMedium, Color(0xFF041427))
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Calligraphy masthead
                    Image(
                        painter = painterResource(id = R.drawable.calligraphy_masthead),
                        contentDescription = "Bismillah Calligraphy",
                        modifier = Modifier
                            .height(48.dp)
                            .padding(bottom = 8.dp),
                        contentScale = ContentScale.Fit
                    )

                    Text(
                        text = "دارالافتاء ارشاد السائلین کراچی",
                        style = MaterialTheme.typography.displayMedium.copy(
                            color = TextOnDark,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Darul Ifta Irshad Us Saileen Karachi",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = IslamicGoldLight,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = AppStrings.heroIntro(currentLanguage),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextOnDark.copy(alpha = 0.85f),
                            textAlign = TextAlign.Center,
                            lineHeight = 22.sp
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Quick Action Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onNavigateToAskFatwa,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = IslamicGoldBright,
                                contentColor = IslamicNavyDark
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("hero_ask_fatwa_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.QuestionAnswer,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = AppStrings.navAskFatwa(currentLanguage),
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }

                        OutlinedButton(
                            onClick = onNavigateToFatwas,
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = TextOnDark
                            ),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = Brush.linearGradient(listOf(IslamicGoldLight, IslamicGold))
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("hero_browse_fatwas_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = IslamicGoldLight,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = AppStrings.navFatwas(currentLanguage),
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Direct WhatsApp Action
                    Surface(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/923332617671"))
                            context.startActivity(intent)
                        },
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF25D366).copy(alpha = 0.18f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF25D366).copy(alpha = 0.5f)),
                        modifier = Modifier.fillMaxWidth().testTag("hero_whatsapp_direct_button")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Chat,
                                contentDescription = "WhatsApp",
                                tint = Color(0xFF25D366),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${AppStrings.whatsAppUs(currentLanguage)}: +92 333 2617671",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = TextOnDark,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }
                }
            }
        }

        // Offline Banner
        if (!isOnline) {
            item {
                OfflineBanner(
                    currentLanguage = currentLanguage,
                    onRetry = { viewModel.retryConnection() }
                )
            }
        }

        // Quick Category Navigation Row
        item {
            Spacer(modifier = Modifier.height(14.dp))
            SectionHeader(
                title = AppStrings.navServices(currentLanguage),
                actionText = AppStrings.viewAll(currentLanguage),
                onActionClick = onNavigateToServices
            )

            val services = viewModel.repository.services
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(services) { s ->
                    Card(
                        onClick = onNavigateToServices,
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = CardWhite),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                        modifier = Modifier
                            .width(180.dp)
                            .height(130.dp)
                            .border(1.dp, ParchmentBorder.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(IslamicNavyDark, RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.School,
                                    contentDescription = null,
                                    tint = IslamicGoldBright,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = s.title.get(currentLanguage),
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = IslamicNavyDark
                                    ),
                                    maxLines = 1
                                )
                                Text(
                                    text = s.description.get(currentLanguage),
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextSecondary,
                                        fontSize = 11.sp
                                    ),
                                    maxLines = 2
                                )
                            }
                        }
                    }
                }
            }
        }

        // Featured Fatwas Section (204 total)
        item {
            Spacer(modifier = Modifier.height(18.dp))
            SectionHeader(
                title = "${AppStrings.featuredFatwas(currentLanguage)} (${fatwas.size})",
                actionText = AppStrings.viewAll(currentLanguage),
                onActionClick = onNavigateToFatwas
            )
        }

        // Show first 4 fatwas
        items(fatwas.take(4)) { f ->
            FatwaCard(
                fatwa = f,
                currentLanguage = currentLanguage,
                onClick = { onNavigateToFatwaDetail(f.slug) },
                isBookmarked = bookmarkedSlugs.contains(f.slug),
                onBookmarkToggle = { viewModel.toggleBookmark(f.slug) }
            )
        }

        // Scholarly Supervision Highlight
        item {
            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader(
                title = AppStrings.supervisorTitle(currentLanguage),
                actionText = AppStrings.readMore(currentLanguage),
                onActionClick = onNavigateToScholars
            )
            scholars.firstOrNull()?.let { scholar ->
                ScholarCard(
                    scholar = scholar,
                    currentLanguage = currentLanguage,
                    onClick = { onNavigateToScholarDetail(scholar.slug) }
                )
            }
        }

        // Latest Daily Guidance / Posts
        item {
            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader(
                title = AppStrings.recentPosts(currentLanguage),
                actionText = AppStrings.viewAll(currentLanguage),
                onActionClick = onNavigateToDailyPosts
            )
        }

        items(dailyPosts.take(2)) { post ->
            DailyPostCard(
                post = post,
                currentLanguage = currentLanguage,
                onClick = { onNavigateToDailyPostDetail(post.slug) }
            )
        }

        // Methodology Card Banner
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                onClick = onNavigateToMethodology,
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = ParchmentSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .border(1.dp, IslamicGold.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(IslamicNavyDark, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = null,
                            tint = IslamicGoldBright,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = AppStrings.navMethodology(currentLanguage),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = IslamicNavyDark
                            )
                        )
                        Text(
                            text = if (currentLanguage == AppLanguage.ENGLISH)
                                "Groundwork in Qur'an, Sunnah, and verified Hanafi sources."
                            else if (currentLanguage == AppLanguage.ARABIC)
                                "الاستناد إلى القرآن والسنة والتدقيق الفقهي الدقيق."
                            else "قرآن و سنت کی اساس اور معتبر فقہی مآخذ کی روشنی میں۔",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                        )
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = IslamicGold,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        // Bank / Donations Section
        item {
            DonationCard(currentLanguage = currentLanguage)
        }
    }
}
