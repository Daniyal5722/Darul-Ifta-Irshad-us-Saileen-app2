package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.School
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.AppStrings
import com.example.model.AppLanguage
import com.example.ui.DarulIftaViewModel
import com.example.ui.theme.CardWhite
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
fun ServicesScreen(
    viewModel: DarulIftaViewModel,
    onNavigateToAskFatwa: () -> Unit,
    onNavigateToFatwas: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val services = viewModel.repository.services

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ParchmentSurface.copy(alpha = 0.5f))
    ) {
        Surface(
            color = IslamicNavyDark,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                Text(
                    text = AppStrings.navServices(currentLanguage),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextOnDark
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (currentLanguage == AppLanguage.URDU)
                        "دارالافتاء کی جانب سے پیش کی جانے والی ہمہ جہت شرعی اور تحقیقی خدمات۔"
                    else if (currentLanguage == AppLanguage.ARABIC)
                        "الخدمات الفقهية والعلمية الشاملة التي تقدمها دار الإفتاء للمسلمين."
                    else "Comprehensive Shariah, advisory, and educational services offered by Darul Ifta.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = IslamicGoldLight,
                        fontSize = 12.sp
                    )
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(services) { s ->
                val icon = when (s.iconName) {
                    "fatwa" -> Icons.Default.QuestionAnswer
                    "education" -> Icons.Default.School
                    "publications" -> Icons.AutoMirrored.Filled.MenuBook
                    "consultation" -> Icons.Default.Groups
                    else -> Icons.Default.Balance
                }

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, ParchmentBorder.copy(alpha = 0.7f), RoundedCornerShape(16.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .background(IslamicNavyDark, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    tint = IslamicGoldBright,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Text(
                                text = s.title.get(currentLanguage),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = IslamicNavyDark
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = s.description.get(currentLanguage),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = TextPrimary,
                                lineHeight = 23.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            if (s.id == "fatwa_service") {
                                Button(
                                    onClick = onNavigateToAskFatwa,
                                    colors = ButtonDefaults.buttonColors(containerColor = IslamicNavyDark),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.testTag("services_ask_fatwa_button")
                                ) {
                                    Text(
                                        text = AppStrings.navAskFatwa(currentLanguage),
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            color = IslamicGoldLight,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            } else {
                                OutlinedButton(
                                    onClick = onNavigateToAskFatwa,
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(
                                        text = if (currentLanguage == AppLanguage.ENGLISH) "Consult Now"
                                        else if (currentLanguage == AppLanguage.ARABIC) "استشر الآن"
                                        else "مشورہ طلب کریں",
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            color = IslamicNavyDark,
                                            fontWeight = FontWeight.Bold
                                        )
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
