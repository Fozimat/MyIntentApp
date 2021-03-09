package com.fozimat.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnMoveActivity: Button
    private lateinit var btnMoveWithDataActivity: Button
    private lateinit var btnDialPhone: Button
    private lateinit var btnMoveWithObject: Button
    private lateinit var btnMoveForResult: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMoveActivity = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)

        btnDialPhone = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        btnMoveWithObject = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        btnMoveForResult = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this, MoveActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_activity_data -> {
                val moveWithDataIntent = Intent(this, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Fozimat Amhas")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 20)
                startActivity(moveWithDataIntent)
            }
            R.id.btn_dial_number -> {
                val phoneNumber = "082124971970"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_move_activity_object -> {
                val person = Person(
                    "Fozimat Amhas",
                    20,
                    "fozimata@gmail.com",
                    "Tanjungpinang"
                )
                val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }
            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                startActivityForResult(moveForResultIntent, REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE) {
            if(resultCode == MoveForResultActivity.RESULT_CODE) {
                val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0)
                tvResult.text = "Hasil : $selectedValue"
            }
        }
    }

}