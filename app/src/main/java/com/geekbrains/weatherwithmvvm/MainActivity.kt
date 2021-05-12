package com.geekbrains.weatherwithmvvm

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.geekbrains.weatherwithmvvm.databinding.MainActivityBinding
import com.geekbrains.weatherwithmvvm.ui.details.DetailsFragment
import com.geekbrains.weatherwithmvvm.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dialog = AlertDialog.Builder(baseContext)
        dialog.setPositiveButton("Yes") {
            dialog, which -> //TODO("Not yet implemented")
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}