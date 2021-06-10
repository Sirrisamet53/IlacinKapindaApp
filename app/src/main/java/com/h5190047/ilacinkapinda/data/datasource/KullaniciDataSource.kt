package com.h5190047.ilacinkapinda.data.datasource

import com.h5190047.ilacinkapinda.data.model.Kullanicilar
import com.h5190047.ilacinkapinda.data.model.KullanicilarResponse
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow

interface KullaniciDataSource {
    fun kullanicilariGetir(): Flow<Resource<KullanicilarResponse>>
}