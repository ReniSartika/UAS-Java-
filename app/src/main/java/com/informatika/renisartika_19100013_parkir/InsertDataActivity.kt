package com.informatika.renisartika_19100013_parkir

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.informatika.renisartika_19100013_parkir.model.ResponseActionBarang
import com.informatika.renisartika_19100013_parkir.model.ResponseBarang
import kotlinx.android.synthetic.main.activity_insert_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        toolbar.title = "Tambahkan Parkir"
        toolbar.setTitleTextColor(Color.WHITE)

        btn_submit.setOnClickListener {
            val jenis_kendaraan = et_jenis_kendaraan.text
            val biaya_kendaraan = et_biaya_kendaraan.text
            val tanggal_masuk = et_tanggal_masuk.text
            if (jenis_kendaraan.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Jumlah Barang Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else if (biaya_kendaraan.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Nama Barang Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else if (tanggal_masuk.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Kode Barang Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                actionData(jenis_kendaraan.toString(), biaya_kendaraan.toString(), tanggal_masuk.toString())
            }
        }

        btn_clean.setOnClickListener {
            formClear()
        }
        getData()
    }

    fun formClear() {
        et_jenis_kendaraan.text.clear()
        et_biaya_kendaraan.text.clear()
        et_tanggal_masuk.text.clear()

    }

    fun actionData(jenis_kendaraan: String, biaya_kendaraan: String, tanggal_masuk: String) {
        koneksi.service.insertBarang(jenis_kendaraan, biaya_kendaraan, tanggal_masuk)
            .enqueue(object : Callback<ResponseActionBarang> {
                override fun onFailure(call: Call<ResponseActionBarang>, t: Throwable) {
                    Log.d("pesan1", t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseActionBarang>,
                    response: Response<ResponseActionBarang>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@InsertDataActivity,
                            "data berhasil disimpan",
                            Toast.LENGTH_LONG
                        ).show()
                        formClear()
                        getData()
                    }
                }
            })
    }

    fun getData() {
        koneksi.service.getBarang().enqueue(object : Callback<ResponseBarang> {
            override fun onFailure(call: Call<ResponseBarang>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseBarang>,
                response: Response<ResponseBarang>
            ) {
                if (response.isSuccessful) {
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@InsertDataActivity, "InsertDataActivity")

                    rv_data_barang.apply {
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@InsertDataActivity)
                    }
                }
            }
        })
    }
}

