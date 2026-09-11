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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.AppStrings
import com.example.model.AppLanguage
import com.example.ui.DarulIftaViewModel
import com.example.ui.components.FatwaCard
import com.example.ui.components.OfflineBanner
import com.example.ui.theme.CardWhite
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
fun FatwasScreen(
    viewModel: DarulIftaViewModel,
    onNavigateToFatwaDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val filteredFatwas by viewModel.filteredFatwas.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val bookmarkedSlugs by viewModel.bookmarkedSlugs.collectAsState()
    val isOnline by viewModel.isOnline.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val categories = listOf(
        null to AppStrings.allCategories(currentLanguage),
        "Inheritance & Wills" to AppStrings.categoryInheritance(currentLanguage),
        "Marriage & Divorce" to AppStrings.categoryMarriage(currentLanguage),
        "Transactions" to AppStrings.categoryTransactions(currentLanguage),
        "Worship" to AppStrings.categoryWorship(currentLanguage),
        "Belief & Conduct" to AppStrings.categoryBelief(currentLanguage)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ParchmentSurface.copy(alpha = 0.5f))
    ) {
        // Search & Filter header
        Surface(
            color = CardWhite,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)) {
                // Search Input Field
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.setSearchQuery(it) },
                    placeholder = {
                        Text(
                            text = AppStrings.searchFatwaHint(currentLanguage),
                            style = MaterialTheme.typography.bodyMedium.copy(color = TextMuted)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = IslamicGold
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear",
                                    tint = TextMuted
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = IslamicNavyDark,
                        unfocusedBorderColor = ParchmentBorder,
                        focusedContainerColor = CardWhite,
                        unfocusedContainerColor = CardWhite
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .testTag("fatwa_search_input")
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Category Chips Row
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { (catKey, catLabel) ->
                        val isSelected = selectedCategory == catKey
                        Surface(
                            onClick = { viewModel.setSelectedCategory(catKey) },
                            shape = RoundedCornerShape(20.dp),
                            color = if (isSelected) IslamicNavyDark else CardWhite,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) IslamicNavyDark else ParchmentBorder
                            ),
                            modifier = Modifier.testTag("category_chip_${catKey ?: "all"}")
                        ) {
                            Text(
                                text = catLabel,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = if (isSelected) IslamicGoldLight else TextPrimary,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                ),
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)
                            )
                        }
                    }
                }

                // Results count line
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp, end = 18.dp, top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (currentLanguage == AppLanguage.URDU)
                            "کل فتاویٰ: ${filteredFatwas.size}"
                        else if (currentLanguage == AppLanguage.ARABIC)
                            "عدد الفتاوى: ${filteredFatwas.size}"
                        else "Showing ${filteredFatwas.size} fatwas",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = TextSecondary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    if (searchQuery.isNotEmpty() || selectedCategory != null) {
                        Surface(
                            onClick = {
                                viewModel.setSearchQuery("")
                                viewModel.setSelectedCategory(null)
                            },
                            shape = RoundedCornerShape(8.dp),
                            color = IslamicGold.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = AppStrings.clearFilters(currentLanguage),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = IslamicNavyDark,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
            }
        }

        if (!isOnline) {
            OfflineBanner(
                currentLanguage = currentLanguage,
                onRetry = { viewModel.retryConnection() }
            )
        }

        // Fatwas List
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = IslamicNavyDark)
            }
        } else if (filteredFatwas.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = AppStrings.emptySearchResults(currentLanguage),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = TextSecondary,
                            textAlign = TextAlign.Center
                        )
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    OutlinedButton(
                        onClick = {
                            viewModel.setSearchQuery("")
                            viewModel.setSelectedCategory(null)
                        },
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(AppStrings.clearFilters(currentLanguage))
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp)
            ) {
                items(
                    items = filteredFatwas,
                    key = { it.slug }
                ) { fatwa ->
                    FatwaCard(
                        fatwa = fatwa,
                        currentLanguage = currentLanguage,
                        onClick = { onNavigateToFatwaDetail(fatwa.slug) },
                        isBookmarked = bookmarkedSlugs.contains(fatwa.slug),
                        onBookmarkToggle = { viewModel.toggleBookmark(fatwa.slug) }
                    )
                }
            }
        }
    }
}
