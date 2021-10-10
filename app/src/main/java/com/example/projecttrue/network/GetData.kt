package com.example.projecttrue.network
/*
import com.example.projecttrue.database.ParlamentMemberData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://users.metropolia.fi/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GetParlament {
    @GET("~peterh/mps.json")
    suspend fun getProperties():
            List<ParlamentMemberData>
}

object ParlamentApi {
    val retrofitService: GetParlament by lazy { retrofit.create(GetParlament::class.java) }
}


 */