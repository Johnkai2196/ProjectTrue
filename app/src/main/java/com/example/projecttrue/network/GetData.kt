package com.example.projecttrue.network

import com.example.projecttrue.database.ParlamentMemberData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Name: Johnkai Cortez
// Student id: 2012960
// 6.10.2021
private const val BASE_URL = "https://users.metropolia.fi/"

//Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
//full Kotlin compatibility.
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
// object.
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


// A public interface that exposes the [getProperties] method
interface GetParlament {
    //get the json data from network
    @GET("~peterh/mps.json")
    suspend fun getProperties():
            List<ParlamentMemberData>
}

//A public Api object that exposes the lazy-initialized Retrofit service
object ParlamentApi {
    val retrofitService: GetParlament by lazy { retrofit.create(GetParlament::class.java) }
}


