package com.example.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.School
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.localization.AppStrings
import com.example.model.AppLanguage
import com.example.model.Scholar
import com.example.ui.DarulIftaViewModel
import com.example.ui.components.DecorativeGoldDivider
import com.example.ui.theme.CardWhite
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldBright
import com.example.ui.theme.IslamicGoldLight
import com.example.ui.theme.IslamicGoldSubtle
import com.example.ui.theme.IslamicNavyDark
import com.example.ui.theme.IslamicNavyMedium
import com.example.ui.theme.ParchmentBg
import com.example.ui.theme.ParchmentBorder
import com.example.ui.theme.TextOnDark
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScholarDetailScreen(
    slug: String,
    viewModel: DarulIftaViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val scholars by viewModel.scholars.collectAsState()

    val scholar: Scholar? = scholars.find { it.slug == slug } ?: viewModel.repository.getScholarBySlug(slug)

    if (scholar == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Scholar not found")
        }
        return
    }

    val name = scholar.localizedName(currentLanguage)
    val title = scholar.localizedTitle(currentLanguage)
    val intro = scholar.localizedIntro(currentLanguage)
    val edu = scholar.localizedEdu(currentLanguage)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = IslamicNavyDark,
                    titleContentColor = TextOnDark,
                    navigationIconContentColor = IslamicGoldBright
                ),
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("scholar_detail_back_button")
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
                        text = name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextOnDark
                        )
                    )
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
        ) {
            // Header Profile Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(IslamicNavyDark, IslamicNavyMedium)
                        )
                    )
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.1f),
                        modifier = Modifier.size(90.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_darulifta_emblem),
                            contentDescription = name,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = IslamicGold.copy(alpha = 0.22f)
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = IslamicGoldBright,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = name,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = TextOnDark,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = scholar.organizationUr.ifBlank { scholar.organizationEn },
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = IslamicGoldLight
                        )
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                // Biography
                if (intro.isNotBlank()) {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = CardWhite),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, ParchmentBorder, RoundedCornerShape(14.dp))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = if (currentLanguage == AppLanguage.ENGLISH) "Biography & Introduction"
                                else if (currentLanguage == AppLanguage.ARABIC) "السيرة الذاتية والمقدمة"
                                else "سوانح و تعارف",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = IslamicNavyDark
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = intro,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = TextPrimary,
                                    lineHeight = 25.sp
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Educational Background
                if (edu.isNotBlank()) {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = CardWhite),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, ParchmentBorder, RoundedCornerShape(14.dp))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.School,
                                    contentDescription = null,
                                    tint = IslamicGold,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (currentLanguage == AppLanguage.ENGLISH) "Educational Background"
                                    else if (currentLanguage == AppLanguage.ARABIC) "المؤهلات العلمية"
                                    else "تعلیمی پس منظر",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = IslamicNavyDark
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = edu,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextPrimary,
                                    lineHeight = 23.sp
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Qualifications list
                if (scholar.qualifications.isNotEmpty()) {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = CardWhite),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, ParchmentBorder, RoundedCornerShape(14.dp))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = if (currentLanguage == AppLanguage.ENGLISH) "Islamic Qualifications"
                                else if (currentLanguage == AppLanguage.ARABIC) "الشهادات والإجازات الشرعية"
                                else "شرعی اسناد و اسنادِ افتاء",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = IslamicNavyDark
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            scholar.qualifications.forEach { q ->
                                Row(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = IslamicGoldBright,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = q.get(currentLanguage),
                                        style = MaterialTheme.typography.bodyMedium.copy(color = TextPrimary)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Areas of Expertise
                if (scholar.areasOfExpertise.isNotEmpty()) {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = CardWhite),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, ParchmentBorder, RoundedCornerShape(14.dp))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = if (currentLanguage == AppLanguage.ENGLISH) "Areas of Expertise"
                                else if (currentLanguage == AppLanguage.ARABIC) "مجالات الاختصاص والبحث"
                                else "علمی و فقہی شعبہ جات",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = IslamicNavyDark
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            scholar.areasOfExpertise.forEach { exp ->
                                Row(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(6.dp)
                                            .background(IslamicNavyDark, CircleShape)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = exp.get(currentLanguage),
                                        style = MaterialTheme.typography.bodyMedium.copy(color = TextPrimary)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
