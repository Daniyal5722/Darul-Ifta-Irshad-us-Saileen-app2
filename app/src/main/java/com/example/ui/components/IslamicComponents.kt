package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.R
import com.example.localization.AppStrings
import com.example.model.AppLanguage
import com.example.model.DailyPost
import com.example.model.Fatwa
import com.example.model.Scholar
import com.example.ui.theme.CardWhite
import com.example.ui.theme.DividerLight
import com.example.ui.theme.IslamicBlue
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldBright
import com.example.ui.theme.IslamicGoldLight
import com.example.ui.theme.IslamicGoldSubtle
import com.example.ui.theme.IslamicNavyDark
import com.example.ui.theme.IslamicNavyMedium
import com.example.ui.theme.ParchmentBorder
import com.example.ui.theme.ParchmentSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextOnDark
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun DecorativeGoldDivider(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            color = IslamicGold.copy(alpha = 0.35f),
            thickness = 1.dp
        )
        Box(
            modifier = Modifier
                .size(7.dp)
                .background(IslamicGoldBright, shape = CircleShape)
        )
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            color = IslamicGold.copy(alpha = 0.35f),
            thickness = 1.dp
        )
    }
}

@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(width = 4.dp, height = 18.dp)
                        .background(IslamicGoldBright, RoundedCornerShape(2.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = IslamicNavyDark
                    )
                )
            }
            if (!subtitle.isNullOrBlank()) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary
                    ),
                    modifier = Modifier.padding(start = 12.dp, top = 2.dp)
                )
            }
        }
        if (actionText != null && onActionClick != null) {
            Surface(
                onClick = onActionClick,
                shape = RoundedCornerShape(12.dp),
                color = IslamicGold.copy(alpha = 0.12f)
            ) {
                Text(
                    text = actionText,
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = IslamicNavyDark,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Composable
fun FatwaCard(
    fatwa: Fatwa,
    currentLanguage: AppLanguage,
    onClick: () -> Unit,
    onBookmarkToggle: (() -> Unit)? = null,
    isBookmarked: Boolean = false,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .border(1.dp, ParchmentBorder.copy(alpha = 0.6f), RoundedCornerShape(14.dp))
            .testTag("fatwa_card_${fatwa.number}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Fatwa number badge
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = IslamicNavyDark
                ) {
                    Text(
                        text = fatwa.number,
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = IslamicGoldLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                // Category Tag
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = IslamicGold.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = fatwa.category,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = IslamicNavyMedium,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                if (onBookmarkToggle != null) {
                    IconButton(
                        onClick = onBookmarkToggle,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) IslamicGoldBright else TextMuted,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Fatwa Title
            Text(
                text = fatwa.localizedTitle(currentLanguage),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    fontSize = 16.sp,
                    lineHeight = 23.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Question excerpt
            val excerpt = fatwa.localizedQuestion(currentLanguage).ifBlank { fatwa.localizedAnswer(currentLanguage) }
            if (excerpt.isNotBlank()) {
                Text(
                    text = excerpt,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary,
                        lineHeight = 19.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Bottom row: reference tag + action
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (fatwa.pdfUrl != null) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.PictureAsPdf,
                            contentDescription = "PDF Available",
                            tint = IslamicBlue,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "PDF",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = IslamicBlue,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(1.dp))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    Text(
                        text = AppStrings.readMore(currentLanguage),
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = IslamicNavyDark,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = IslamicNavyDark,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DailyPostCard(
    post: DailyPost,
    currentLanguage: AppLanguage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, ParchmentBorder.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
    ) {
        Column {
            // Post Image or Header Pattern
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(IslamicNavyDark)
            ) {
                // Select local image or URL
                val localResId = when {
                    post.featuredImageUrl?.contains("7ffa3c72") == true -> R.drawable.daily_post_hadees_1
                    post.featuredImageUrl?.contains("0c0873fa") == true -> R.drawable.daily_post_hadees_2
                    else -> null
                }

                if (localResId != null) {
                    Image(
                        painter = painterResource(id = localResId),
                        contentDescription = post.localizedTitle(currentLanguage),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(180.dp)
                    )
                } else if (!post.featuredImageUrl.isNullOrBlank()) {
                    val fullUrl = if (post.featuredImageUrl.startsWith("/")) {
                        "https://darulifta-bkfbzf6u.manus.space${post.featuredImageUrl}"
                    } else post.featuredImageUrl

                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(fullUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = post.localizedTitle(currentLanguage),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(180.dp)
                    )
                } else {
                    // Fallback to library/calligraphy background
                    Image(
                        painter = painterResource(id = R.drawable.bg_library),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(180.dp)
                    )
                }

                // Dark gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.75f))
                            )
                        )
                )

                // Date badge
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color.Black.copy(alpha = 0.6f),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                ) {
                    Text(
                        text = post.publishedAt.take(10).ifBlank { "Darul Ifta" },
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = IslamicGoldLight,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            // Text content
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = post.localizedTitle(currentLanguage),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = IslamicNavyDark
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = post.localizedContent(currentLanguage),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = TextSecondary,
                        lineHeight = 21.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = IslamicGold.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = post.authorName,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = IslamicNavyMedium,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    IconButton(
                        onClick = {
                            val shareText = "${post.localizedTitle(currentLanguage)}\n\n${post.localizedContent(currentLanguage)}\n\n${post.originalPostUrl}"
                            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                putExtra(Intent.EXTRA_TEXT, shareText)
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(sendIntent, "Share Daily Post"))
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = IslamicNavyDark,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScholarCard(
    scholar: Scholar,
    currentLanguage: AppLanguage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, ParchmentBorder.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = IslamicNavyDark,
                modifier = Modifier.size(64.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_darulifta_emblem),
                    contentDescription = scholar.localizedName(currentLanguage),
                    modifier = Modifier.padding(6.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = IslamicGold.copy(alpha = 0.18f),
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(
                        text = scholar.localizedTitle(currentLanguage),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = IslamicNavyDark,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Text(
                    text = scholar.localizedName(currentLanguage),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                )

                Text(
                    text = scholar.organizationUr.ifBlank { scholar.organizationEn },
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = IslamicGoldBright,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun DonationCard(
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val ibanNumber = "PK56MEZN0001830100450333"

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = IslamicNavyDark),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(IslamicGoldBright, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = AppStrings.donationTitle(currentLanguage),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = IslamicGoldLight,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = AppStrings.donationDesc(currentLanguage),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextOnDark.copy(alpha = 0.85f),
                    lineHeight = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = IslamicNavyMedium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = AppStrings.bankName(currentLanguage),
                            style = MaterialTheme.typography.labelSmall.copy(color = IslamicGoldLight)
                        )
                        Text(
                            text = "Meezan Bank Ltd",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TextOnDark,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = AppStrings.accountHolder(currentLanguage),
                            style = MaterialTheme.typography.labelSmall.copy(color = IslamicGoldLight)
                        )
                        Text(
                            text = "FAISAL HAYAT",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TextOnDark,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    HorizontalDivider(color = IslamicGold.copy(alpha = 0.25f))

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = AppStrings.ibanNumber(currentLanguage),
                                style = MaterialTheme.typography.labelSmall.copy(color = IslamicGoldLight)
                            )
                            Text(
                                text = ibanNumber,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextOnDark,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        IconButton(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("IBAN", ibanNumber)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(context, AppStrings.textCopied(currentLanguage), Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier
                                .size(36.dp)
                                .background(IslamicGoldBright.copy(alpha = 0.2f), CircleShape)
                                .testTag("copy_iban_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy IBAN",
                                tint = IslamicGoldBright,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OfflineBanner(
    currentLanguage: AppLanguage,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = IslamicGoldSubtle,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .border(1.dp, IslamicGold.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.WifiOff,
                contentDescription = null,
                tint = IslamicGold,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = AppStrings.noInternetTitle(currentLanguage),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = IslamicNavyDark
                    )
                )
                Text(
                    text = AppStrings.noInternetDesc(currentLanguage),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                )
            }
            IconButton(onClick = onRetry, modifier = Modifier.size(32.dp)) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Retry",
                    tint = IslamicNavyDark
                )
            }
        }
    }
}
