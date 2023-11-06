package com.example.randomduck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.example.randomduck.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val response = RetrofitInstance.api.getimage()
                    if (response.isSuccessful) {
                        val duck = response.body()
                        binding.message.text = duck?.message
                        Picasso.get().load(duck?.url).into(binding.image)
                    } else {
                        Log.e("MainActivity", "Error: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error: ${e.message}")
                }
            }
        }


    }
}