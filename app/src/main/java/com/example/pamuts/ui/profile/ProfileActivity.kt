package com.example.pamuts.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pamuts.databinding.FragmentProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}