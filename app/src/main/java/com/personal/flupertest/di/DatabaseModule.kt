package com.personal.flupertest.di

import com.personal.flupertest.database.FluperTestDatabase
import com.personal.flupertest.database.repository.ProductsRepositories
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val roomModule = module {
    single { FluperTestDatabase.getInstance(androidApplication()) }
    single(createOnStart = false) { get<FluperTestDatabase>().getProductsDao() }
    single { ProductsRepositories(get()) }

}