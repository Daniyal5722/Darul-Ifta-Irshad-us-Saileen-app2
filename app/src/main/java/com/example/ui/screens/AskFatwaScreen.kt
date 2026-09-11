package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.ui.DarulIftaViewModel
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
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AskFatwaScreen(
    viewModel: DarulIftaViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var question by remember { mutableStateOf("") }

    val categories = listOf(
        AppStrings.categoryWorship(currentLanguage),
        AppStrings.categoryTransactions(currentLanguage),
        AppStrings.categoryMarriage(currentLanguage),
        AppStrings.categoryInheritance(currentLanguage),
        AppStrings.categoryBelief(currentLanguage)
    )
    var selectedCategory by remember { mutableStateOf(categories[0]) }
    var isCategoryExpanded by remember { mutableStateOf(false) }

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
                        modifier = Modifier.testTag("ask_fatwa_back_button")
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
                        text = AppStrings.askFatwaTitle(currentLanguage),
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
                .padding(16.dp)
        ) {
            // Instructions Card
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = IslamicGoldSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, IslamicGold.copy(alpha = 0.35f), RoundedCornerShape(12.dp))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = AppStrings.formInstructions(currentLanguage),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = IslamicNavyDark,
                            lineHeight = 20.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(AppStrings.fieldName(currentLanguage)) },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardWhite,
                    unfocusedContainerColor = CardWhite,
                    focusedBorderColor = IslamicNavyDark,
                    unfocusedBorderColor = ParchmentBorder
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ask_fatwa_name_input")
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Phone / WhatsApp
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text(AppStrings.fieldPhone(currentLanguage)) },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardWhite,
                    unfocusedContainerColor = CardWhite,
                    focusedBorderColor = IslamicNavyDark,
                    unfocusedBorderColor = ParchmentBorder
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ask_fatwa_phone_input")
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(AppStrings.fieldEmail(currentLanguage)) },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardWhite,
                    unfocusedContainerColor = CardWhite,
                    focusedBorderColor = IslamicNavyDark,
                    unfocusedBorderColor = ParchmentBorder
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ask_fatwa_email_input")
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Category Selector
            ExposedDropdownMenuBox(
                expanded = isCategoryExpanded,
                onExpandedChange = { isCategoryExpanded = !isCategoryExpanded }
            ) {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(AppStrings.fieldCategory(currentLanguage)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCategoryExpanded) },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = CardWhite,
                        unfocusedContainerColor = CardWhite,
                        focusedBorderColor = IslamicNavyDark,
                        unfocusedBorderColor = ParchmentBorder
                    ),
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = isCategoryExpanded,
                    onDismissRequest = { isCategoryExpanded = false }
                ) {
                    categories.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat) },
                            onClick = {
                                selectedCategory = cat
                                isCategoryExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Title
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(AppStrings.fieldSubject(currentLanguage)) },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardWhite,
                    unfocusedContainerColor = CardWhite,
                    focusedBorderColor = IslamicNavyDark,
                    unfocusedBorderColor = ParchmentBorder
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ask_fatwa_subject_input")
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Detailed Question
            OutlinedTextField(
                value = question,
                onValueChange = { question = it },
                label = { Text(AppStrings.fieldQuestion(currentLanguage)) },
                minLines = 5,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardWhite,
                    unfocusedContainerColor = CardWhite,
                    focusedBorderColor = IslamicNavyDark,
                    unfocusedBorderColor = ParchmentBorder
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ask_fatwa_question_input")
            )

            Spacer(modifier = Modifier.height(20.dp))

            // WhatsApp Submit Button
            Button(
                onClick = {
                    if (question.isBlank()) {
                        Toast.makeText(context, "براہ کرم اپنا سوال درج فرمائیں۔", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    val msg = buildString {
                        append("السلام علیکم ورحمۃ اللہ وبرکاتہ\n")
                        append("دارالافتاء ارشاد السائلین کراچی کے لیے استفسار:\n\n")
                        if (name.isNotBlank()) append("نام: $name\n")
                        if (phone.isNotBlank()) append("فون: $phone\n")
                        if (selectedCategory.isNotBlank()) append("زمرہ: $selectedCategory\n")
                        if (title.isNotBlank()) append("عنوان: $title\n\n")
                        append("سوال:\n$question")
                    }
                    val encoded = URLEncoder.encode(msg, "UTF-8")
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/923332617671?text=$encoded"))
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF25D366),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("submit_whatsapp_button")
            ) {
                Icon(imageVector = Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = AppStrings.submitViaWhatsApp(currentLanguage),
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Email Submit Button
            OutlinedButton(
                onClick = {
                    if (question.isBlank()) {
                        Toast.makeText(context, "Please enter your question", Toast.LENGTH_SHORT).show()
                        return@OutlinedButton
                    }
                    val emailSubject = "Fatwa Question: ${title.ifBlank { "Query from App" }}"
                    val emailBody = buildString {
                        append("Bismillahir Rahmanir Raheem\n\n")
                        if (name.isNotBlank()) append("Name: $name\n")
                        if (phone.isNotBlank()) append("Phone: $phone\n")
                        append("Category: $selectedCategory\n\n")
                        append("Question:\n$question\n\n")
                        append("Submitted via Darul Ifta Irshad Us Saileen App")
                    }
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:ask.darulifa.irshadussaaileen@gmail.com")
                        putExtra(Intent.EXTRA_SUBJECT, emailSubject)
                        putExtra(Intent.EXTRA_TEXT, emailBody)
                    }
                    context.startActivity(intent)
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("submit_email_button")
            ) {
                Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = IslamicNavyDark)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = AppStrings.submitViaEmail(currentLanguage),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = IslamicNavyDark
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
