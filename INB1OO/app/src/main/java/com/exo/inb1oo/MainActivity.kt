package com.exo.inb1oo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.exo.inb1oo.databinding.ActivityMainBinding
import java.text.DecimalFormat
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var payment = randomPrice()
        binding.tvTotalPayment.text=getString(R.string.totalPay, payment)
        binding.btnCobro.setOnClickListener{
           /* if(areCompleteData) {
                //to do in another screen
            }*/

        }

        val typeCardsList = listOf(R.drawable.amexpress_logo,R.drawable.mastercard_logo,R.drawable.visa_logo)

        val adapter = TypeCardAdapter(this, typeCardsList)
        binding.spTypeCard.adapter = adapter

        binding.spTypeCard.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = typeCardsList[position]
                Toast.makeText(this@MainActivity,"Selected: $selectedItem",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }




    private fun randomPrice(): String {
        val randomPrice = Random.nextDouble(100.0, 501.0)
        val priceFormat = DecimalFormat("###.##")
        return priceFormat.format(randomPrice)
    }

}