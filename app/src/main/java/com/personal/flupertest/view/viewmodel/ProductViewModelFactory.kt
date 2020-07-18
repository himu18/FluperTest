package com.personal.flupertest.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.personal.flupertest.database.repository.ProductsRepositories

class ProductViewModelFactory (private val repository : ProductsRepositories): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)){
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}