package com.personal.flupertest.database.repository

import com.personal.flupertest.database.dao.ProductsDao
import com.personal.flupertest.database.entitiy.Products


class ProductsRepositories(private val dao: ProductsDao) {

    val products = dao.getAllProducts()


    suspend fun insetProducts(product: Products){
        dao.insertProduct(product)
    }

    fun  getProductUsingID(id:Int){
        dao.getProductUsingID(id)
    }

    suspend fun updateProducts(product: Products){
        dao.updateProducts(product)
    }

    suspend fun deleteProducts(product: Products){
        dao.deleteProducts(product)
    }

    suspend fun deleteAllProducts(){
        dao.deleteAllProducts()
    }

    suspend fun insertProductListFromJson(productList:List<Products>){
        dao.insertProductListFromJson(productList)
    }

}