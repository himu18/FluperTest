package com.personal.flupertest.view.products.product_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.personal.flupertest.R
import com.personal.flupertest.database.entitiy.Products
import com.personal.flupertest.databinding.ActivityMainBinding
import com.personal.flupertest.databinding.ProductListFragmentBinding
import com.personal.flupertest.view.binding.BindingFragment
import com.personal.flupertest.view.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.product_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProductListFragment : BindingFragment<ProductListFragmentBinding>(), OnProductItemImageClickedListener,View.OnClickListener{

    companion object {
        fun newInstance() =
            ProductListFragment()
    }
   override fun getLayoutResId() = R.layout.product_list_fragment;

    private  val productListViewModel: ProductViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.productListViewModel = productListViewModel
        binding.setLifecycleOwner(this)
        initRecyclerView()
        fabCreateProduct.setOnClickListener(this)
    }


    private fun initRecyclerView(){
        binding.rvProductList.layoutManager = LinearLayoutManager(context)
        displayProductList()
    }

    @SuppressLint("FragmentLiveDataObserve")
    fun displayProductList(){
        productListViewModel.products.observe(this, Observer {

            //binding.rvProductList.adapter = ProductListRecyclerViewAdapter(it,{selectedItem:Products->productListItemClicked(selectedItem)})

            binding.rvProductList.adapter = ProductListRecyclerViewAdapter(it,this)

        })
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun productListItemClicked(product: Products){

        productListViewModel.selectedProduct(product)
        val createProduct = Bundle().apply {
            putBoolean("IS_CREATE_PRODUCT", false)
        }
        activity?.findNavController(R.id.nav_host_fragment)?.navigate( R.id.action_ProductListFragment_to_UpdateProductFragment,createProduct)

    }

    override fun productItemImageClicked(stringImage: String) {
        val createProduct = Bundle().apply {
            putString("STRING_IMAGE", stringImage)
        }
        activity?.findNavController(R.id.nav_host_fragment)?.navigate( R.id.action_ProductListFragment_to_FullScreenImage,createProduct)

    }

    override fun productItemCLicked(product: Products) {
        productListViewModel.selectedProduct(product)
        val createProduct = Bundle().apply {
            putBoolean("IS_CREATE_PRODUCT", false)
        }
        activity?.findNavController(R.id.nav_host_fragment)?.navigate( R.id.action_ProductListFragment_to_UpdateProductFragment,createProduct)


    }

    override fun onClick(v: View?) {

        when (v?.getId()) {

            R.id.fabCreateProduct -> {
                val createProduct = Bundle().apply {
                    putBoolean("IS_CREATE_PRODUCT", true)
                }
                activity?.findNavController(R.id.nav_host_fragment)?.navigate( R.id.action_ProductListFragment_to_UpdateProductFragment,createProduct)
            }
        }
    }

}

