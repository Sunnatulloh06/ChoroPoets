package com.example.choronopoets.viewModel

import com.example.choronopoets.dataClass.Century
import com.example.choronopoets.dataClass.Poems
import com.example.choronopoets.dataClass.Poet

data class PoetryUIState(
    val isDarkMode: Boolean = false,
    val centuries: List<Century> = emptyList(),
    val poets: List<Poet> = emptyList(),
    val poems: List<Poems> = emptyList(),
    val selectedPoet: Poet? = null,
    val selectedPoem: Poems? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
