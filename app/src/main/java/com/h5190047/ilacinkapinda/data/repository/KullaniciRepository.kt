package com.h5190047.ilacinkapinda.data.repository

import com.h5190047.ilacinkapinda.data.datasource.KategoriDataSource
import com.h5190047.ilacinkapinda.data.datasource.RemoteKategoriDataSource
import com.h5190047.ilacinkapinda.data.datasource.RemoteKullaniciDataSource
import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.data.model.Kullanicilar
import com.h5190047.ilacinkapinda.data.model.KullanicilarResponse
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow

class KullaniciRepository {
    private var kullaniciDataSource: RemoteKullaniciDataSource? = null

    init {
        kullaniciDataSource = RemoteKullaniciDataSource()
    }
    //kullanıcıların getirileceği fonksiyondur.Flow coroutines in resource kaynağından kategori responsuna erişiliyor.

    fun kullanicilariGetir(): Flow<Resource<KullanicilarResponse>> {
        return kullaniciDataSource!!.kullanicilariGetir()
    }
}