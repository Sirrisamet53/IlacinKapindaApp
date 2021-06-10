package com.h5190047.ilacinkapinda.ui.kategori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.h5190047.ilacinkapinda.R
import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.databinding.ActivityKategoriBinding
import com.h5190047.ilacinkapinda.ui.urun.UrunActivity
import com.h5190047.ilacinkapinda.util.*

class KategoriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKategoriBinding
    var kategoriViewModel: KategoriViewModel = KategoriViewModel()
    var kategoriListe: List<KategorilerUrunlerResponseItem>? = null

    var kategoriAdaptor: KategoriAdaptor? = null

    override fun onBackPressed() {
        AlertUtil.alertGoster(
            this,
            getString(R.string.cikis_uyari_baslik),
            getString(R.string.cikis_uyari_mesaj),
            UYARI_TIPLERI.CIKIS_UYARI
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = ActivityKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSearchView()
        initViewModel()
    }

    private fun initSearchView() {
        binding.apply {
            searchView.apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String?): Boolean {
                        kategorileriFiltrele(newText)
                        return false
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }
                })
            }
        }
    }

    private fun initViewModel() {
        kategoriViewModel.apply {
            tumKategorilerLiveData.observe(this@KategoriActivity, androidx.lifecycle.Observer {
                Log.e("SAMTO", "observe: " + it.toString())
                kategoriListe = it
                initRecycleView(kategoriListe!!)
            })

            error?.observe(this@KategoriActivity, androidx.lifecycle.Observer {
                Log.e("SAMTO", "error:")
            })

            loading?.observe(this@KategoriActivity, androidx.lifecycle.Observer {
                Log.e("SAMTO", "loading:")

            })
        }
    }

    fun kategorileriFiltrele(arananKategori: String?) {
        arananKategori?.let {
            kategoriListe?.let {
                var filtrelenmişKategoriListe = it.filter { it.categoryName!!.toUpperCase().contains(arananKategori.toUpperCase()) }
                initRecycleView(filtrelenmişKategoriListe)
            }
        }
    }

    private fun initRecycleView(kategoriler: List<KategorilerUrunlerResponseItem>) {
        binding.apply {
            kategoriAdaptor = KategoriAdaptor(kategoriler, object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val urunString = ObjeUtil.objeToJsonString(kategoriler[position])

                    val intent = Intent(this@KategoriActivity, UrunActivity::class.java)
                    intent.putExtra(Constants.TASINANIN_BASLIGI, urunString)

                    startActivity(intent)
                }
            })

            rcvKategoriler.adapter = kategoriAdaptor
            rcvKategoriler.layoutManager = GridLayoutManager(applicationContext, Constants.GRID_KOLON_SAYISI)
        }
    }
}