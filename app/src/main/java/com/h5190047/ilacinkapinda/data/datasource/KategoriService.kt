package com.h5190047.ilacinkapinda.data.datasource

import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.data.model.KullanicilarResponse
import com.h5190047.ilacinkapinda.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
//Github içerisine atılmış olan kullanıcı jsonunun alındığı interface türü kotlin sınıfıdır
interface KategoriService {
    @GET("kullanicilar.json")
    suspend fun kullanicilariGetir(): Response<KullanicilarResponse>

    @GET("kategoriler_urunler.json")
    suspend fun kategorileriGetir(): Response<List<KategorilerUrunlerResponseItem>>

    companion object {
        fun build(): KategoriService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .build()

            return retrofit.create(KategoriService::class.java)
        }
    }
}