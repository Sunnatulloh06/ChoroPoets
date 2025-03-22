package com.example.choronopoets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.choronopoets.dataClass.Century
import com.example.choronopoets.dataClass.Poems
import com.example.choronopoets.dataClass.Poet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PoetryViewModel(private val repository: PoetryRepository) : ViewModel() {

    private val _backgroundView = MutableStateFlow(false)
    val backgroundView: StateFlow<Boolean> = _backgroundView

    private val _centuries = MutableStateFlow<List<Century>>(emptyList())
    val centuries: StateFlow<List<Century>> = _centuries.asStateFlow()

    private val _poets = MutableStateFlow<List<Poet>>(emptyList())
    val poets: StateFlow<List<Poet>> = _poets.asStateFlow()

    private val _poems = MutableStateFlow<List<Poems>>(emptyList())
    val poems: StateFlow<List<Poems>> = _poems.asStateFlow()

    private val _selectedPoet = MutableStateFlow<Poet?>(null)
    val selectedPoet: StateFlow<Poet?> = _selectedPoet.asStateFlow()

    init {
        loadCenturies()
    }

    private fun loadCenturies() {
        viewModelScope.launch {
            repository.getAllCenturies().collect { centuriesList ->
                _centuries.value = centuriesList
            }
        }
    }

    fun loadPoets(centuryId: Int) {
        viewModelScope.launch {
            repository.getPoetsByCentury(centuryId).collect { poetsList ->
                _poets.value = poetsList
            }
        }
    }

    fun loadPoetDetails(poetId: Int) {
        viewModelScope.launch {
            repository.getPoetById(poetId).collect { poet ->
                _selectedPoet.value = poet
            }
        }
    }

    fun toggleTheme() {
        _backgroundView.value = !_backgroundView.value
    }

    fun loadPoems(poetId: Int) {
        viewModelScope.launch {
            repository.getPoemsByPoet(poetId).collect { poemsList ->
                _poems.value = poemsList
            }
        }
    }
}
