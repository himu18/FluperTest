package com.personal.flupertest.view.products.show_product_image

import android.graphics.BitmapFactory
import android.os.Bundle
import com.personal.flupertest.R
import com.personal.flupertest.databinding.ShowProductImageFragmentBinding
import com.personal.flupertest.view.binding.BindingFragment
import com.personal.flupertest.view.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.show_product_image_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ShowProductImageFragment : BindingFragment<ShowProductImageFragmentBinding>() {

    companion object {
        fun newInstance() = ShowProductImageFragment()
    }

    private val showProductImageViewModel: ProductViewModel by sharedViewModel()

    override fun getLayoutResId() = R.layout.show_product_image_fragment



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.updateProductViewModel = showProductImageViewModel
        binding.setLifecycleOwner(this)
    }

    override fun onResume() {
        super.onResume()
        val stringImage = arguments?.getString("STRING_IMAGE")

        val imageBytes = android.util.Base64.decode(stringImage, 0)
        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        ivFullScreenImage.setImageBitmap(image)
    }

}
