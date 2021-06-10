package com.h5190047.ilacinkapinda.data.repository

import com.h5190047.ilacinkapinda.data.datasource.KategoriDataSource
import com.h5190047.ilacinkapinda.data.datasource.RemoteKategoriDataSource
import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow

class KategoriRepository {
    private var kategeoriDataSource: KategoriDataSource? = null

    init {
        kategeoriDataSource = RemoteKategoriDataSource()
    }

    fun kategorileriGetir(): Flow<Resource<List<KategorilerUrunlerResponseItem>>>{
        return kategeoriDataSource!!.kategorileriGetir()
    }
}