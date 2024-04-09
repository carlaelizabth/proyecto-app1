package com.exo.inb1oo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.exo.inb1oo.databinding.ActivityMain2Binding
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val params = intent.extras

        var totalPayment = ""
        var numCard = ""
        var name = ""

        if (params != null) {
            name = params.getString("name", "")
            numCard = params.getString("numCard", "")
            totalPayment = params.getString("payment", "")
        }

        val approved = operationApproved()
        val subNumCard = numCard.substring(12)

        binding.tvName.text = name
        if (approved in 1..3) {
            binding.gifOperationValidate.setImageResource(R.drawable.operation_approved)
            binding.tvMessageOperation.text = getString(R.string.payApproved, totalPayment, getString(R.string.stCard)+subNumCard)
        } else {
            binding.gifOperationValidate.setImageResource(R.drawable.operation_denied)
            binding.tvMessageOperation.text = getString(R.string.payDenied, totalPayment, getString(R.string.stCard)+subNumCard)
        }

        binding.btnDone.setOnClickListener {
            val intent = Intent(this@MainActivity2, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun operationApproved(): Int{
        return Random.nextInt(1,5)
    }
}