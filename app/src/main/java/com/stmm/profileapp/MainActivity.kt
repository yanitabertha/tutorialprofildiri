package com.stmm.profileapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var namaInput: String = ""
    var emailInput: String = ""
    var telpInput: String = ""
    var alamatInput: String = ""
    var genderInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            validasiInput()
        }

        setDataSpinnerGender()
    }

    private fun setDataSpinnerGender() {
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.gender_list, android.R.layout.simple_spinner_dropdown_item
        )

        spinnerGender.adapter = adapter
    }

    //buat dari resource dari Main Activity

    private fun validasiInput() {
        namaInput = edtName.text.toString()
        emailInput = edtEmail.text.toString()
        telpInput = edtTelp.text.toString()
        alamatInput = edtAddress.text.toString()
        genderInput = spinnerGender.selectedItem.toString()
//pakai toString karena apapun yang masuk akan dijadikan String

        when {

            namaInput.isEmpty() -> edtName.error = "Nama tidak boleh kosong"
            genderInput.equals("Pilih Kelamin") -> tampilToast("Kelamin harus dipilih")
            emailInput.isEmpty() -> edtEmail.error = "Email tidak boleh kosong"
            telpInput.isEmpty() -> edtTelp.error = "Telepon tidak boleh kosong"
            alamatInput.isEmpty() -> edtAddress.error = "Alamat tidak boleh kosong"

            else -> {
                tampilToast("Navigasi ke halaman profil diri")
                navigasiKeProfilDiri()

            }
        }
    }

    private fun navigasiKeProfilDiri() {
        val intent = Intent(this, ProfileActivity::class.java)
//untuk pindah activity pakai Intent
        //PACKAGE CONTEXT: activity sekarang dan PROFILE ACTIVITY: activity tujuan
        //bundle di bawah dipakai untuk mengirim activity sekarang ke selanjutnya (ketika klik akan
        // masuk ke halaman ProfileActivity
        val bundle = Bundle()
        bundle.putString("nama", namaInput)
        bundle.putString("gender", genderInput)
        bundle.putString("email", emailInput)
        bundle.putString("telp", telpInput)
        bundle.putString("alamat", alamatInput)

        intent.putExtras(bundle)
//putExtras mengambil data di atas dan kirim ke activity selanjutnya
        startActivity(intent)
    }

    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        // artinya context this adalah ditampilkan di sini, di MainActivity
        //Toast untuk tampilkan pesan melayang
    }
}
