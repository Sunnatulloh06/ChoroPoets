package com.example.choronopoets.dependency_injection

import com.example.choronopoets.PoetryRepository
import com.example.choronopoets.viewModel.PoetryViewModel
import com.example.choronopoets.data.PoetryDatabase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { PoetryDatabase.getInstance(get()) }
    single { get<PoetryDatabase>().centuryDao() }
    single { get<PoetryDatabase>().poetDao() }
    single { get<PoetryDatabase>().poemDao() }
    single { PoetryRepository(get(), get(), get()) }
    viewModel { PoetryViewModel(get()) }
}

