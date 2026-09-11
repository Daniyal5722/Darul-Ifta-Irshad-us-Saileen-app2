package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.AppStrings
import com.example.model.AppLanguage
import com.example.ui.DarulIftaViewModel
import com.example.ui.components.DecorativeGoldDivider
import com.example.ui.components.DonationCard
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
fun ContactScreen(
    viewModel: DarulIftaViewModel,
    onNavigateToAskFatwa: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val context = LocalContext.current

    val phoneNumber = "+92 333 2617671"
    val emailAddress = "ask.darulifa.irshadussaaileen@gmail.com"
    val address = "Al Mujeeb Garden A31, Karachi, Pakistan"

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ParchmentSurface.copy(alpha = 0.5f))
    ) {
        // Top banner
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
                    text = AppStrings.getInTouch(currentLanguage),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextOnDark
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = AppStrings.contactDesc(currentLanguage),
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Ask Fatwa Banner
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = IslamicNavyMedium),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(IslamicGoldBright, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.QuestionAnswer,
                                    contentDescription = null,
                                    tint = IslamicNavyDark,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = AppStrings.askFatwaTitle(currentLanguage),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = TextOnDark,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = AppStrings.askFatwaDesc(currentLanguage),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextOnDark.copy(alpha = 0.85f)
                            )
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = onNavigateToAskFatwa,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = IslamicGoldBright,
                                contentColor = IslamicNavyDark
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("contact_ask_fatwa_button")
                        ) {
                            Text(
                                text = AppStrings.navAskFatwa(currentLanguage),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // WhatsApp Direct
            item {
                ContactActionCard(
                    icon = Icons.Default.Chat,
                    iconBg = Color(0xFF25D366),
                    title = AppStrings.whatsAppUs(currentLanguage),
                    value = phoneNumber,
                    actionLabel = "WhatsApp",
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/923332617671"))
                        context.startActivity(intent)
                    }
                )
            }

            // Phone Call
            item {
                ContactActionCard(
                    icon = Icons.Default.Call,
                    iconBg = IslamicBlue,
                    title = AppStrings.callUs(currentLanguage),
                    value = phoneNumber,
                    actionLabel = "Call",
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+923332617671"))
                        context.startActivity(intent)
                    }
                )
            }

            // Email
            item {
                ContactActionCard(
                    icon = Icons.Default.Email,
                    iconBg = IslamicNavyDark,
                    title = AppStrings.sendEmail(currentLanguage),
                    value = emailAddress,
                    actionLabel = "Email",
                    onClick = {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:$emailAddress")
                            putExtra(Intent.EXTRA_SUBJECT, "Question for Darul Ifta Irshad Us Saileen")
                        }
                        context.startActivity(intent)
                    }
                )
            }

            // YouTube
            item {
                ContactActionCard(
                    icon = Icons.Default.PlayCircle,
                    iconBg = Color(0xFFFF0000),
                    title = AppStrings.watchYouTube(currentLanguage),
                    value = "@MuftiFaisalHayat",
                    actionLabel = "YouTube",
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/@MuftiFaisalHayat"))
                        context.startActivity(intent)
                    }
                )
            }

            // Address
            item {
                ContactActionCard(
                    icon = Icons.Default.LocationOn,
                    iconBg = IslamicGold,
                    title = AppStrings.addressTitle(currentLanguage),
                    value = address,
                    actionLabel = AppStrings.openMaps(currentLanguage),
                    onClick = {
                        val uri = Uri.parse("geo:0,0?q=" + Uri.encode(address))
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
                )
            }

            // Bank Donations
            item {
                DonationCard(currentLanguage = currentLanguage)
            }
        }
    }
}

@Composable
fun ContactActionCard(
    icon: ImageVector,
    iconBg: Color,
    title: String,
    value: String,
    actionLabel: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, ParchmentBorder.copy(alpha = 0.6f), RoundedCornerShape(14.dp))
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(iconBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                )
            }
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = IslamicNavyDark.copy(alpha = 0.08f)
            ) {
                Text(
                    text = actionLabel,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = IslamicNavyDark,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}
