package com.example.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.localization.AppStrings
import com.example.model.AppLanguage
import com.example.ui.theme.IslamicGoldBright
import com.example.ui.theme.IslamicGoldLight
import com.example.ui.theme.IslamicNavyDark
import com.example.ui.theme.IslamicNavyMedium
import com.example.ui.theme.TextOnDark
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    currentLanguage: AppLanguage,
    onSplashFinished: () -> Unit
) {
    val scale = remember { Animatable(0.8f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1.0f,
            animationSpec = tween(durationMillis = 900, easing = FastOutSlowInEasing)
        )
        alpha.animateTo(
            targetValue = 1.0f,
            animationSpec = tween(durationMillis = 800)
        )
        delay(1200)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(IslamicNavyDark, IslamicNavyMedium, Color(0xFF041224))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(24.dp)
                .scale(scale.value)
                .alpha(alpha.value)
        ) {
            Surface(
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.08f),
                modifier = Modifier.size(130.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_darulifta_logo),
                    contentDescription = "Darul Ifta Logo",
                    modifier = Modifier.padding(14.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = AppStrings.bismillah(currentLanguage),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = IslamicGoldLight,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "دارالافتاء ارشاد السائلین",
                style = MaterialTheme.typography.displayMedium.copy(
                    color = TextOnDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Darul Ifta Irshad Us Saileen Karachi",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = IslamicGoldBright,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "کراچی - پاکستان",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextOnDark.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
