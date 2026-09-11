package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.ui.DarulIftaViewModel
import com.example.ui.components.DailyPostCard
import com.example.ui.components.OfflineBanner
import com.example.ui.theme.CardWhite
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldBright
import com.example.ui.theme.IslamicGoldLight
import com.example.ui.theme.IslamicNavyDark
import com.example.ui.theme.IslamicNavyMedium
import com.example.ui.theme.ParchmentSurface
import com.example.ui.theme.TextOnDark
import com.example.ui.theme.TextSecondary

@Composable
fun DailyPostsScreen(
    viewModel: DarulIftaViewModel,
    onNavigateToPostDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val dailyPosts by viewModel.dailyPosts.collectAsState()
    val isOnline by viewModel.isOnline.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ParchmentSurface.copy(alpha = 0.5f))
    ) {
        // WhatsApp Channel Banner
        Surface(
            color = IslamicNavyDark,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = AppStrings.navDailyPosts(currentLanguage),
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = TextOnDark,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = AppStrings.dailyGuidanceDesc(currentLanguage),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = IslamicGoldLight,
                            fontSize = 11.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://whatsapp.com/channel/0029VaCc5dK7T8bclVGHu20Q")
                        )
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF25D366),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("join_whatsapp_channel_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Chat,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = AppStrings.openWhatsAppChannel(currentLanguage),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

        if (!isOnline) {
            OfflineBanner(
                currentLanguage = currentLanguage,
                onRetry = { viewModel.retryConnection() }
            )
        }

        if (isLoading && dailyPosts.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = IslamicNavyDark)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp)
            ) {
                items(
                    items = dailyPosts,
                    key = { it.slug }
                ) { post ->
                    DailyPostCard(
                        post = post,
                        currentLanguage = currentLanguage,
                        onClick = { onNavigateToPostDetail(post.slug) }
                    )
                }
            }
        }
    }
}
