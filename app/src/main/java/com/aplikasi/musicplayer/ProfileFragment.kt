package com.aplikasi.musicplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private var userType: String? = null
    private var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userType = it.getString(ARG_USER_TYPE)
            userName = it.getString(ARG_USER_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Menampilkan sapaan sesuai dengan tipe pengguna
        val greetingTextView = view.findViewById<TextView>(R.id.tvGreeting)
        if (userType == "Pendengar") {
            greetingTextView.text = "Hai, Pendengar $userName!"
        } else if (userType == "Penyanyi") {
            greetingTextView.text = "Hai, Penyanyi $userName!"
        }

        return view
    }

    companion object {
        private const val ARG_USER_TYPE = "userType"
        private const val ARG_USER_NAME = "userName"

        @JvmStatic
        fun newInstance(userType: String, userName: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_TYPE, userType)
                    putString(ARG_USER_NAME, userName)
                }
            }
    }
}
