package com.h5190047.ilacinkapinda.data.datasource

import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow


interface KategoriDataSource {
    fun kategorileriGetir(): Flow<Resource<List<KategorilerUrunlerResponseItem>>>
}