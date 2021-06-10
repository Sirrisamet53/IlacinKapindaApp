package com.h5190047.ilacinkapinda.ui.detay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.h5190047.ilacinkapinda.R
import com.h5190047.ilacinkapinda.data.model.Product
import com.h5190047.ilacinkapinda.databinding.ActivityDetayBinding
import com.h5190047.ilacinkapinda.util.Constants
import com.h5190047.ilacinkapinda.util.GlideUtil
import com.h5190047.ilacinkapinda.util.ObjeUtil

class DetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetayBinding
    var urun: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var urunString = intent.getStringExtra(Constants.TASINANIN_BASLIGI)

        urun = ObjeUtil.jsonStringToObje(urunString!!)

        binding.apply {
            urun?.run {
                txtUrunBaslik.text = this.marka
                txtUrunDetay.text = this.Aciklama
                GlideUtil.resmiIndiripGoster(applicationContext, this.urunResim, imgIlacFoto)

            }
        }

    }
}