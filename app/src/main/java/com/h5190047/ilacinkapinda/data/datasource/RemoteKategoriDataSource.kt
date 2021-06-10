package com.h5190047.ilacinkapinda.data.datasource

import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteKategoriDataSource: KategoriDataSource {
    override fun kategorileriGetir(): Flow<Resource<List<KategorilerUrunlerResponseItem>>> = flow {
        try {
            emit(Resource.Loading())

            val response = KategoriService.build().kategorileriGetir()

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }

        } catch (ex: Exception) {
            emit(Resource.Error(ex))
            ex.printStackTrace()
        }
    }

}