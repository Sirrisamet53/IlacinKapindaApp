package com.h5190047.ilacinkapinda.data.repository

import com.h5190047.ilacinkapinda.data.datasource.KategoriDataSource
import com.h5190047.ilacinkapinda.data.datasource.remote.RemoteKategoriDataSource
import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow

class KategoriRepository {
    //Kategori verilerinin çekildiği kotlin sınıfıdır.
    private var kategeoriDataSource: KategoriDataSource? = null

    init {
        kategeoriDataSource = RemoteKategoriDataSource()
    }
    //kategorilerin getirileceği fonksiyondur.Flow coroutines Kategori ve ürünlerin resource kaynağından kategori responsuna erişiliyor.
    fun kategorileriGetir(): Flow<Resource<List<KategorilerUrunlerResponseItem>>>{
        return kategeoriDataSource!!.kategorileriGetir()
    }
}