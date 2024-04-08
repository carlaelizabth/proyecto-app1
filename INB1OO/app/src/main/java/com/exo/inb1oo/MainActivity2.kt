package com.exo.inb1oo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val params = intent.extras

        if (params!=null){
            val name = params.getString("name","")
            val numCard = params.getInt("numCard",0)
            val totalPayment = params.getInt("payment",0)
            val email = params.getString("email","")
        }

        val approved = operationApproved()

        if (approved in 1 .. 3){

        }
    }

    private fun operationApproved(): Int{
        return Random.nextInt(1,5)
    }
}