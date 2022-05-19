package com.example.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomButtonsView.setListener {
            when (it) {
                BottomButtonsAction.POSITIVE -> {
                    binding.bottomButtonsView.isProgressMode = true
                    Toast.makeText(this, "POSITIVE", Toast.LENGTH_SHORT).show()
                }
                BottomButtonsAction.NEGATIVE -> {
                    binding.bottomButtonsView.setButtonNegativeText("NEGATIVE")
                    binding.bottomButtonsView.setButtonPositiveText("POSITIVE")
                    Toast.makeText(this, "NEGATIVE", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}