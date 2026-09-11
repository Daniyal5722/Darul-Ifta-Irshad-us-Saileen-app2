package com.example.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.DarulIftaRepository
import com.example.model.AppLanguage
import com.example.model.DailyPost
import com.example.model.Fatwa
import com.example.model.Scholar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DarulIftaViewModel(application: Application) : AndroidViewModel(application) {

    val repository = DarulIftaRepository(application)
    private val prefs = application.getSharedPreferences("darul_ifta_prefs", Context.MODE_PRIVATE)

    private val _currentLanguage = MutableStateFlow(
        AppLanguage.fromCode(prefs.getString("selected_lang", "ur") ?: "ur")
    )
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    private val _readingFontScale = MutableStateFlow(
        prefs.getFloat("font_scale", 1.0f)
    )
    val readingFontScale: StateFlow<Float> = _readingFontScale.asStateFlow()

    private val _bookmarkedSlugs = MutableStateFlow(
        prefs.getStringSet("bookmarked_slugs", emptySet()) ?: emptySet()
    )
    val bookmarkedSlugs: StateFlow<Set<String>> = _bookmarkedSlugs.asStateFlow()

    val fatwas: StateFlow<List<Fatwa>> = repository.fatwas
    val scholars: StateFlow<List<Scholar>> = repository.scholars
    val dailyPosts: StateFlow<List<DailyPost>> = repository.dailyPosts
    val isOnline: StateFlow<Boolean> = repository.isOnline
    val isLoading: StateFlow<Boolean> = repository.isLoading

    val filteredFatwas: StateFlow<List<Fatwa>> = combine(
        repository.fatwas,
        _searchQuery,
        _selectedCategory
    ) { allFatwas, query, category ->
        var list = allFatwas
        if (!category.isNullOrBlank()) {
            list = list.filter { it.category.equals(category, ignoreCase = true) }
        }
        val trimmed = query.trim()
        if (trimmed.isNotBlank()) {
            val lower = trimmed.lowercase()
            list = list.filter { f ->
                f.number.lowercase().contains(lower) ||
                f.titleUr.contains(trimmed) ||
                f.titleEn.lowercase().contains(lower) ||
                f.questionUr.contains(trimmed) ||
                f.questionEn.lowercase().contains(lower) ||
                f.answerUr.contains(trimmed) ||
                f.answerEn.lowercase().contains(lower) ||
                f.topics.any { it.contains(trimmed, ignoreCase = true) }
            }
        }
        list
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            repository.loadInitialData()
        }
    }

    fun setLanguage(lang: AppLanguage) {
        _currentLanguage.value = lang
        prefs.edit().putString("selected_lang", lang.code).apply()
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSelectedCategory(cat: String?) {
        _selectedCategory.value = cat
    }

    fun increaseFontScale() {
        val current = _readingFontScale.value
        if (current < 1.4f) {
            val updated = current + 0.1f
            _readingFontScale.value = updated
            prefs.edit().putFloat("font_scale", updated).apply()
        }
    }

    fun decreaseFontScale() {
        val current = _readingFontScale.value
        if (current > 0.85f) {
            val updated = current - 0.1f
            _readingFontScale.value = updated
            prefs.edit().putFloat("font_scale", updated).apply()
        }
    }

    fun toggleBookmark(slug: String) {
        val current = _bookmarkedSlugs.value.toMutableSet()
        if (current.contains(slug)) {
            current.remove(slug)
        } else {
            current.add(slug)
        }
        _bookmarkedSlugs.value = current
        prefs.edit().putStringSet("bookmarked_slugs", current).apply()
    }

    fun retryConnection() {
        viewModelScope.launch {
            repository.loadInitialData()
        }
    }
}
