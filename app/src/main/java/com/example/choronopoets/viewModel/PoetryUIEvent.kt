package com.example.choronopoets.viewModel

sealed class PoetryUIEvent {
    data object LoadCenturies: PoetryUIEvent()
    data class LoadPoets(val centuryId: Int): PoetryUIEvent()
    data class LoadPoetDetails(val poetId: Int): PoetryUIEvent()
    data class LoadPoems(val poetId: Int): PoetryUIEvent()
    data class LoadPoemById(val poemId: Int) : PoetryUIEvent()
    data object ToggleTheme: PoetryUIEvent()
}