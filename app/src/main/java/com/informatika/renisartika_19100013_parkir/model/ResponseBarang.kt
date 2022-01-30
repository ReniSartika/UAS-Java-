package com.informatika.renisartika_19100013_parkir.model

import com.google.gson.annotations.SerializedName

data class ResponseBarang(

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataItem(

	@field:SerializedName("tanggal_masuk")
	val tanggalMasuk: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("jenis_kendaraan")
	val jenisKendaraan: String? = null,

	@field:SerializedName("biaya_kendaraan")
	val biayaKendaraan: String? = null
)
