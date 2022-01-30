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
import kotlinx.android.synthetic.main.activity_update_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
        toolbar.title = "Edit Parkir"
        toolbar.setTitleTextColor(Color.WHITE)

        val i = intent
        val idBarang = i.getStringExtra("IDBARANG")
        val jenis_kendaraan = i.getStringExtra("JENISKENDARAAN")
        val biaya_kendaraan = i.getStringExtra("BIAYAKENDARAAN")
        val tanggal_masuk = i.getStringExtra("TANGGALMASUK")

        et_jenis_kendaraan.setText(jenis_kendaraan)
        et_biaya_kendaraan.setText(biaya_kendaraan)
        et_tanggal_masuk.setText(tanggal_masuk)
        btn_submit.setOnClickListener {
            val jenis_kendaraan = et_jenis_kendaraan.text
            val biaya_kendaraan = et_biaya_kendaraan.text
            val tanggal_masuk = et_tanggal_masuk.text
            if (jenis_kendaraan.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Jenis Kendaraan Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            }else if (biaya_kendaraan.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Biaya Kendaraan Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            } else if (tanggal_masuk.isEmpty()) {
                Toast.makeText(
                    this@UpdateDataActivity,
                    "Kode Barang Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                actionData(idBarang.toString(), jenis_kendaraan.toString(), biaya_kendaraan.toString(), tanggal_masuk.toString())
            }
        }
        btn_back.setOnClickListener {
            finish()
        }
        getData()
    }
    fun actionData(id : String, jenis_kendaraan : String, biaya_kendaraan : String, tanggal_masuk: String){
        koneksi.service.updateBarang(id, jenis_kendaraan, biaya_kendaraan, tanggal_masuk).enqueue(object :
            Callback<ResponseActionBarang> {
            override fun onFailure(call: Call<ResponseActionBarang>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseActionBarang>,
                response: Response<ResponseActionBarang>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@UpdateDataActivity, "data berhasil diupdate", Toast.LENGTH_LONG).show()
                    getData()
                }
            }
        })
    }
    fun getData(){
        koneksi.service.getBarang().enqueue(object : Callback<ResponseBarang> {
            override fun onFailure(call: Call<ResponseBarang>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseBarang>,
                response: Response<ResponseBarang>
            ) {
                if (response.isSuccessful){
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@UpdateDataActivity,"UpdateDataActivity")


                    rv_data_barang.apply {
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@UpdateDataActivity)
                    }

                }
            }

        })
    }
}