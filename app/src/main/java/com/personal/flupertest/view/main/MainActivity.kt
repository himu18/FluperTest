package com.personal.flupertest.view.main

import android.os.Bundle
import android.view.animation.Animation
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.personal.flupertest.R
import com.personal.flupertest.databinding.ActivityMainBinding
import com.personal.flupertest.view.binding.BindingActivity
import com.personal.flupertest.view.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.custom_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module.applicationContext


class MainActivity : BindingActivity<ActivityMainBinding>() {
    private  val mainViewModel: ProductViewModel by viewModel()

    private var isFabOpen = false

    private var fab_open: Animation? = null
    private var fab_close: Animation? = null
    private var rotate_forward: Animation? = null
    private var rotate_backward: Animation? = null
    private var navController: NavController? = null
    private lateinit var navigationController: NavController
    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainViewModel = mainViewModel
        binding.setLifecycleOwner(this)
        setSupportActionBar(toolbar)
        navigationController = findNavController(R.id.nav_host_fragment)

        displayProductList()

    }

    fun displayProductList(){
        mainViewModel.products.observe(this, Observer {
            if(it.size == 0) {
                val data = mainViewModel.getAssetJsonData(applicationContext)
                if (data != null) {
                    mainViewModel.saveDataInfoLocalDataBase(data)
                }
            }else{
                val host = NavHostFragment.create(R.navigation.fragment_navigation)
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, host)
                    .setPrimaryNavigationFragment(host).commit()
            }
        })

    }
}
