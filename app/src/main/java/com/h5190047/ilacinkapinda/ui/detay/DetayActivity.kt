package com.h5190047.ilacinkapinda.ui.detay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.h5190047.ilacinkapinda.R
import com.h5190047.ilacinkapinda.data.model.Product
import com.h5190047.ilacinkapinda.databinding.ActivityDetayBinding
import com.h5190047.ilacinkapinda.util.Constants
import com.h5190047.ilacinkapinda.util.GlideUtil
import com.h5190047.ilacinkapinda.util.ObjeUtil
//Detay sayfasının görüntülendiği sınıftır
class DetayActivity : AppCompatActivity() {
    //değişkenlerin ve bindingin tanımlandığı yerdir
    private lateinit var binding: ActivityDetayBinding
    var urun: Product? = null

    //detay sayfası açıldığında çalışacak olan metoddur
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //ürünlerin jsondan nesneye çevrildiği kısımdır.
        var urunString = intent.getStringExtra(Constants.TASINANIN_BASLIGI)

        urun = ObjeUtil.jsonStringToObje(urunString!!)

        //activity dosyası içerisine tanımlanan değişkenlerin json ile birleştirildiği kısımdır
        binding.apply {
            urun?.run {
                txtUrunBaslik.text = this.marka
                txtUrunDetay.text = this.Aciklama
                //ürün resmi glide util içerisinden çekilir
                GlideUtil.resmiIndiripGoster(applicationContext, this.urunResim, imgIlacFoto)

            }
        }

    }
}