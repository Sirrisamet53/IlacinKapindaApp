package com.h5190047.ilacinkapinda.ui.urun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.data.model.Product
import com.h5190047.ilacinkapinda.databinding.ActivityUrunBinding
import com.h5190047.ilacinkapinda.ui.detay.DetayActivity
import com.h5190047.ilacinkapinda.util.Constants
import com.h5190047.ilacinkapinda.util.ObjeUtil
import com.h5190047.ilacinkapinda.util.OnItemClickListener
import com.h5190047.ilacinkapinda.util.RCV_LAYOUT_TIPLERI

class UrunActivity : AppCompatActivity() {

    var secilenKategori: KategorilerUrunlerResponseItem? = null
    var urunAdaptor: UrunAdaptor? = null
    var listeTuru: RCV_LAYOUT_TIPLERI? = RCV_LAYOUT_TIPLERI.GRID_LAYOUT


    private lateinit var binding: ActivityUrunBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityUrunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var jsonString = intent.getStringExtra(Constants.TASINANIN_BASLIGI)
        secilenKategori = ObjeUtil.jsonStringToObje(jsonString!!)

        initRecycleView(secilenKategori!!.Products!!)

        initButonlar()
    }

    private fun initButonlar() {
        binding.apply {
            btnIzgaraYap.setOnClickListener {
                listelemeyiDegistir()
            }
            btnListeYap.setOnClickListener {
                listelemeyiDegistir()
            }
        }
    }

    fun listelemeyiDegistir(){
        binding.apply {
            if(listeTuru == RCV_LAYOUT_TIPLERI.LISTE_LAYOUT){
                rcvUrunler.layoutManager = GridLayoutManager(applicationContext, Constants.GRID_KOLON_SAYISI)
                listeTuru = RCV_LAYOUT_TIPLERI.GRID_LAYOUT
                btnIzgaraYap.isEnabled = false
                btnListeYap.isEnabled = true
            }else {
                rcvUrunler.layoutManager = LinearLayoutManager(applicationContext)
                listeTuru = RCV_LAYOUT_TIPLERI.LISTE_LAYOUT
                btnIzgaraYap.isEnabled = true
                btnListeYap.isEnabled = false
            }
        }
    }

    private fun initRecycleView(urunler: List<Product>) {
        binding.apply {
            urunAdaptor = UrunAdaptor(urunler, object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(
                        applicationContext,
                        urunler.get(position).marka,
                        Toast.LENGTH_SHORT
                    ).show()

                    var urunDetayString = ObjeUtil.objeToJsonString(urunler.get(position))
                    val intent = Intent(this@UrunActivity, DetayActivity::class.java)
                    intent.putExtra(Constants.TASINANIN_BASLIGI, urunDetayString)
                    startActivity(intent)
                }
            })

            rcvUrunler.adapter = urunAdaptor
            rcvUrunler.layoutManager =
                GridLayoutManager(applicationContext, Constants.GRID_KOLON_SAYISI)
        }
    }

}