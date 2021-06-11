package com.h5190047.ilacinkapinda.data.service

import com.h5190047.ilacinkapinda.data.model.KullanicilarResponse
import com.h5190047.ilacinkapinda.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
//Github içerisine atılmış olan kullanıcı jsonunun alındığı interface türü kotlin sınıfıdır
interface KullaniciService {
    @GET("kullanicilar.json")
    //Kullanıcıların getirildiği kısıtlanmış fonksiyondur.
    suspend fun kullanicilariGetir(): Response<KullanicilarResponse>

    //statik nesne metodudur. içerisinde kullanıcı servisi oluşturulur.
    companion object {
        fun build(): KullaniciService {

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

            return retrofit.create(KullaniciService::class.java)
        }
    }
}