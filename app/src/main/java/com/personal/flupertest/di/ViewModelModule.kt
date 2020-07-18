package com.personal.flupertest.di

import com.personal.flupertest.view.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { ProductViewModel(get()) }
}