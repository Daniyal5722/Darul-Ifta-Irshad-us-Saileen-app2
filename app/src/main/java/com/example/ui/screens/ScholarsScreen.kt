package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.AppStrings
import com.example.model.AppLanguage
import com.example.ui.DarulIftaViewModel
import com.example.ui.components.ScholarCard
import com.example.ui.theme.IslamicGoldLight
import com.example.ui.theme.IslamicNavyDark
import com.example.ui.theme.ParchmentSurface
import com.example.ui.theme.TextOnDark

@Composable
fun ScholarsScreen(
    viewModel: DarulIftaViewModel,
    onNavigateToScholarDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val scholars by viewModel.scholars.collectAsState()

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
                    text = AppStrings.navScholars(currentLanguage),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextOnDark
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (currentLanguage == AppLanguage.URDU)
                        "دارالافتاء کے معتمد مفتیانِ کرام، اساتذہ اور علمی سرپرستوں کا تعارف۔"
                    else if (currentLanguage == AppLanguage.ARABIC)
                        "التعريف بالمشايخ والعلماء المفتين والمشرفين على دار الإفتاء."
                    else "Distinguished scholars and Muftis providing authoritative guidance at Darul Ifta.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = IslamicGoldLight,
                        fontSize = 12.sp
                    )
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp)
        ) {
            items(
                items = scholars,
                key = { it.slug }
            ) { scholar ->
                ScholarCard(
                    scholar = scholar,
                    currentLanguage = currentLanguage,
                    onClick = { onNavigateToScholarDetail(scholar.slug) }
                )
            }
        }
    }
}
