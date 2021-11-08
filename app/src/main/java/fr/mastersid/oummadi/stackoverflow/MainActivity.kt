package fr.mastersid.oummadi.stackoverflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.oummadi.stackoverflow.databinding.ActivityMainBinding
import java.lang.Exception


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}