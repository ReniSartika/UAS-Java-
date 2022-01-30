package com.informatika.renisartika_19100013_parkir

import com.informatika.renisartika_19100013_parkir.model.ResponseActionBarang
import com.informatika.renisartika_19100013_parkir.model.ResponseAdmin
import com.informatika.renisartika_19100013_parkir.model.ResponseBarang
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("read.php")
    fun getBarang(): Call<ResponseBarang>

    @FormUrlEncoded
    @POST("create.php")
    fun insertBarang(
        @Field("jenis_kendaraan") jenis_kendaraan: String?,
        @Field("biaya_kendaraan") biaya_kendaraan: String?,
        @Field("tanggal_masuk") tanggal_masuk: String?,
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("update.php")
    fun updateBarang(
        @Field("id") id: String?,
        @Field("jenis_kendaraan") jenis_kendaraan: String?,
        @Field("biaya_kendaraan") biaya_kendaraan: String?,
        @Field("tanggal_masuk") tanggal_masuk: String?,
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteBarang(
        @Field("id") id: String?
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<ResponseAdmin>

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<ResponseAdmin>


}