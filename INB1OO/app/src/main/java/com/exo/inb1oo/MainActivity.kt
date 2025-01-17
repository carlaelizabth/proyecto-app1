package com.exo.inb1oo

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.exo.inb1oo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat
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
        val payment = randomPrice()
        binding.tvTotalPayment.text=getString(R.string.totalPay, payment)

        val typeCardsList = listOf(R.drawable.not_selection,R.drawable.amexpress_logo,R.drawable.mastercard_logo,R.drawable.visa_logo)

        val adapter = TypeCardAdapter(this, typeCardsList)
        binding.spTypeCard.adapter = adapter

        var isSelectedItem = false

        binding.spTypeCard.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                isSelectedItem = false
                val idSelectedItem = adapter.getItemId(position)
                if(idSelectedItem in 1..3){
                    isSelectedItem = true
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity, "No item selected", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPay.setOnClickListener{
            if(areValidData(isSelectedItem)) {
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                val parameters = bundleOf(
                    "name" to binding.etName.text.toString(),
                    "numCard" to binding.etNumCard.text.toString(),
                    "payment" to payment
                )
                intent.apply{
                    putExtras(parameters)
                }
                startActivity(intent)
            }
        }



    }

    private fun randomPrice(): String {
        val randomPrice = Random.nextDouble(100.0, 501.0)
        val priceFormat = DecimalFormat("###.##")
        return priceFormat.format(randomPrice)
    }

    private fun isValidEmail(email: String): Boolean{
        val emailRegex = Regex("[A-Z0-9a-z_%+-]+@[A-Za-z0-9-]+\\.[A-Za-z]{1,64}")
        if (emailRegex.matches(email))
            return true
        else
            binding.etMail.error = getString(R.string.enterMail)
        return false
    }

    private fun isValidNumCard():Boolean {
        if (binding.etNumCard.text.isNotEmpty() && binding.etNumCard.text.length == 16) {
            return true
        } else
            binding.etNumCard.error = getString(R.string.enterNumCard)
        return false
    }

    private fun isValidName():Boolean{
        val nameRegex = Regex("^[A-Z][a-z]+(?:\\s[A-Z][a-z]*)*")
        if (binding.etName.text.isNotEmpty() && nameRegex.matches(binding.etName.text))
            return true
        else
            binding.etName.error = getString(R.string.enterName)
        return false
    }

    private fun isValidDate():Boolean{
        val currentDateS = Calendar.getInstance()
        val currentYearS = currentDateS.get(Calendar.YEAR)%100
        val currentMonthS = currentDateS.get(Calendar.MONTH)+1

        if(binding.etExpiryY.text.isNotEmpty() && binding.etExpiryM.text.isNotEmpty() ) {
            val year = binding.etExpiryY.text.toString().toInt()
            val month = binding.etExpiryM.text.toString().toInt()
            if (month in 1..12) {
                if (currentYearS < year) {
                    return true
                } else if (currentYearS == year) {
                    if (currentMonthS < month)
                        return true
                     else {
                        binding.etExpiryM.error = getString(R.string.enterExMonth)
                    }
                    return false
                }
                binding.etExpiryY.error = getString(R.string.enterExYear)
                return false
            }
            binding.etExpiryM.error = getString(R.string.enterExMonth)
            return false
        }
        binding.etExpiryM.error = getString(R.string.enterExMonth)
        binding.etExpiryY.error = getString(R.string.enterExYear)
        return false
    }

    private fun isValidCVV(): Boolean{
        if (binding.etCvv.text.isNotEmpty() && binding.etCvv.text.length in 3..4 )
            return true
        else
            binding.etCvv.error = getString(R.string.enterCVV)
        return false
    }

    private fun isSpSelected(isSelected: Boolean): Boolean{
        if (isSelected)
            return true
        else
            Snackbar.make(binding.root, getString(R.string.spError), Snackbar.LENGTH_SHORT).show()
        return false
    }

    private fun areValidData(isSelected: Boolean): Boolean{
        if(isValidName() and isValidNumCard() and isValidDate() and isValidCVV() and isValidEmail(binding.etMail.text.toString()) and isSpSelected(isSelected)) {
            return true
        } else
            Toast.makeText(this,resources.getString(R.string.correctData),Toast.LENGTH_LONG).show()
        return false

    }

}