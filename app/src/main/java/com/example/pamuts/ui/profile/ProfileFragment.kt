package com.example.pamuts.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pamuts.LoginActivity
import com.example.pamuts.databinding.FragmentProfileBinding
import com.google.firebase.database.DatabaseReference

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.logoutButton.setOnClickListener {
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                it.startActivity(intent)
            }
        }
        val root: View = binding.root
        val textUser: TextView = binding.textUser
        val textGit: TextView = binding.textGit
        val textNik: TextView = binding.textNik
        val textEmail: TextView = binding.textEmail

        profileViewModel.text.observe(viewLifecycleOwner) {
            textUser.text = "farhan04"
            textGit.text = "parhannn"
            textNik.text = "123456123568199"
            textEmail.text = "farhannn58@gmail.com"
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}