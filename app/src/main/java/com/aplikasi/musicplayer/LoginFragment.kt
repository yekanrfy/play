package com.aplikasi.musicplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aplikasi.musicplayer.R
import com.aplikasi.musicplayer.SharedPrefManager
import com.aplikasi.musicplayer.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // Predefined usernames and passwords for multiple roles
    private val users = mapOf(
        "listener" to "12345", // Listener role
        "singer" to "54321"   // Singer role
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle login button click
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validate input fields
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check username and password
            if (users.containsKey(username) && users[username] == password) {
                // Save login details in SharedPreferences, including isLoggedIn parameter
                SharedPrefManager.saveUser(requireContext(), username, password, isLoggedIn = true)
                Toast.makeText(requireContext(), "Login Successful as $username", Toast.LENGTH_SHORT).show()

                // Navigate to the appropriate fragment based on the role
                if (username == "listener") {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else if (username == "singer") {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            } else {
                Toast.makeText(requireContext(), "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
