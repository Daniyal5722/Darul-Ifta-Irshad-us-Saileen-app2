package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.TextDecrease
import androidx.compose.material.icons.filled.TextIncrease
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.AppStrings
import com.example.model.AppLanguage
import com.example.model.Fatwa
import com.example.ui.DarulIftaViewModel
import com.example.ui.components.DecorativeGoldDivider
import com.example.ui.theme.CardWhite
import com.example.ui.theme.IslamicBlue
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldBright
import com.example.ui.theme.IslamicGoldLight
import com.example.ui.theme.IslamicGoldSubtle
import com.example.ui.theme.IslamicNavyDark
import com.example.ui.theme.IslamicNavyMedium
import com.example.ui.theme.ParchmentBg
import com.example.ui.theme.ParchmentBorder
import com.example.ui.theme.ParchmentSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextOnDark
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FatwaDetailScreen(
    slug: String,
    viewModel: DarulIftaViewModel,
    onBack: () -> Unit,
    onViewPdf: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val fatwas by viewModel.fatwas.collectAsState()
    val bookmarkedSlugs by viewModel.bookmarkedSlugs.collectAsState()
    val fontScale by viewModel.readingFontScale.collectAsState()
    val context = LocalContext.current

    val fatwa: Fatwa? = fatwas.find { it.slug == slug } ?: viewModel.repository.getFatwaBySlug(slug)

    if (fatwa == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Fatwa not found")
        }
        return
    }

    val isBookmarked = bookmarkedSlugs.contains(fatwa.slug)
    val title = fatwa.localizedTitle(currentLanguage)
    val question = fatwa.localizedQuestion(currentLanguage)
    val answer = fatwa.localizedAnswer(currentLanguage)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = IslamicNavyDark,
                    titleContentColor = TextOnDark,
                    navigationIconContentColor = IslamicGoldBright,
                    actionIconContentColor = IslamicGoldBright
                ),
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("fatwa_detail_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = IslamicGoldBright
                        )
                    }
                },
                title = {
                    Text(
                        text = AppStrings.fatwaNumber(currentLanguage, fatwa.number),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextOnDark
                        )
                    )
                },
                actions = {
                    // Font Scale buttons
                    IconButton(
                        onClick = { viewModel.decreaseFontScale() },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.TextDecrease,
                            contentDescription = "Decrease text size",
                            tint = IslamicGoldLight,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    IconButton(
                        onClick = { viewModel.increaseFontScale() },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.TextIncrease,
                            contentDescription = "Increase text size",
                            tint = IslamicGoldLight,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Bookmark
                    IconButton(
                        onClick = { viewModel.toggleBookmark(fatwa.slug) },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) IslamicGoldBright else TextOnDark
                        )
                    }

                    // Share
                    IconButton(
                        onClick = {
                            val shareBody = buildString {
                                append("دارالافتاء ارشاد السائلین کراچی\n")
                                append("فتویٰ نمبر: ${fatwa.number}\n\n")
                                append("عنوان: $title\n\n")
                                append("سوال:\n$question\n\n")
                                append("الجواب وباللہ التوفیق:\n$answer\n\n")
                                if (fatwa.reference.isNotBlank()) {
                                    append("حوالہ: ${fatwa.reference}\n\n")
                                }
                                append("مستند شرعی رہنمائی: دارالافتاء ارشاد السائلین کراچی")
                            }
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                putExtra(Intent.EXTRA_TEXT, shareBody)
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(intent, "Share Fatwa"))
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = IslamicGoldBright
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(ParchmentBg)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Badges row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = IslamicNavyDark
                ) {
                    Text(
                        text = fatwa.number,
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = IslamicGoldLight,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = IslamicGold.copy(alpha = 0.18f)
                ) {
                    Text(
                        text = fatwa.category,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = IslamicNavyMedium,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }

                if (fatwa.date.isNotBlank()) {
                    Text(
                        text = fatwa.date,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = TextMuted
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = IslamicNavyDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = (20 * fontScale).sp,
                    lineHeight = (28 * fontScale).sp
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Question Box
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = ParchmentSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, ParchmentBorder, RoundedCornerShape(12.dp))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = AppStrings.questionLabel(currentLanguage),
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = IslamicNavyDark,
                                fontWeight = FontWeight.Bold,
                                fontSize = (16 * fontScale).sp
                            )
                        )

                        IconButton(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                clipboard.setPrimaryClip(ClipData.newPlainText("Question", question))
                                Toast.makeText(context, AppStrings.textCopied(currentLanguage), Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy Question",
                                tint = TextMuted,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = question,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = TextPrimary,
                            fontSize = (16 * fontScale).sp,
                            lineHeight = (25 * fontScale).sp
                        )
                    )
                }
            }

            DecorativeGoldDivider()

            // Answer Box
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, ParchmentBorder.copy(alpha = 0.8f), RoundedCornerShape(12.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = AppStrings.answerLabel(currentLanguage),
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = IslamicGoldBright,
                                fontWeight = FontWeight.Bold,
                                fontSize = (16 * fontScale).sp
                            )
                        )

                        IconButton(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                clipboard.setPrimaryClip(ClipData.newPlainText("Answer", answer))
                                Toast.makeText(context, AppStrings.textCopied(currentLanguage), Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy Answer",
                                tint = TextMuted,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = answer,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = TextPrimary,
                            fontSize = (16 * fontScale).sp,
                            lineHeight = (26 * fontScale).sp
                        )
                    )

                    // Reference if present
                    if (fatwa.reference.isNotBlank()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(color = IslamicGold.copy(alpha = 0.25f))
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = AppStrings.referenceLabel(currentLanguage),
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = IslamicNavyMedium,
                                fontWeight = FontWeight.Bold,
                                fontSize = (13 * fontScale).sp
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = fatwa.reference,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextSecondary,
                                fontSize = (13 * fontScale).sp,
                                lineHeight = (20 * fontScale).sp
                            )
                        )
                    }
                }
            }

            // PDF Action Button if available
            if (fatwa.pdfUrl != null) {
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { onViewPdf(fatwa.pdfUrl, fatwa.number) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = IslamicNavyDark,
                        contentColor = IslamicGoldBright
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("view_fatwa_pdf_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.PictureAsPdf,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = AppStrings.viewPdfDocument(currentLanguage),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Scholar signature / verification note
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = IslamicGoldSubtle,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(IslamicGoldBright, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = if (currentLanguage == AppLanguage.URDU)
                            "مصدقہ و جاری کردہ: دارالافتاء ارشاد السائلین کراچی"
                        else if (currentLanguage == AppLanguage.ARABIC)
                            "صادر ومعتمد من: دار الإفتاء إرشاد السائلين كراتشي"
                        else "Issued & Verified by: Darul Ifta Irshad Us Saileen Karachi",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = IslamicNavyDark,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
