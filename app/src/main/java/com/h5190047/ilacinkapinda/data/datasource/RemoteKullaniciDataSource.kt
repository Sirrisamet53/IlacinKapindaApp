package com.h5190047.ilacinkapinda.data.datasource

import com.h5190047.ilacinkapinda.data.model.KullanicilarResponse
import com.h5190047.ilacinkapinda.data.service.KullaniciService
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
//Modellenen kullanıcıların kaynak dosyasından çekilen bilgiler ile birleştirildiği classtır
class RemoteKullaniciDataSource: KullaniciDataSource {
    //kullanıcıların responstan çekildiği metoddur.
    override fun kullanicilariGetir(): Flow<Resource<KullanicilarResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = KullaniciService.build().kullanicilariGetir()
            //eğer response başarılı gelir ise resource başarılı döner
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