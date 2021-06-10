package com.h5190047.ilacinkapinda.data.datasource

import com.h5190047.ilacinkapinda.data.model.KullanicilarResponse
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteKullaniciDataSource: KullaniciDataSource {
    override fun kullanicilariGetir(): Flow<Resource<KullanicilarResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = KullaniciService.build().kullanicilariGetir()

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