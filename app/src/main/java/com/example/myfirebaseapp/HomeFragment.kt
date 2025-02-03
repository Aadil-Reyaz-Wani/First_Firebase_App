package com.example.myfirebaseapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the Firebase Auth
        auth = FirebaseAuth.getInstance()


        val buttonLogout = view.findViewById<Button>(R.id.buttonLogout)

        // Signing out from the app
        buttonLogout.setOnClickListener {
            auth.signOut()
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_fragmentSignin)
            Toast.makeText(requireContext(), "Successfully Signed Out", Toast.LENGTH_SHORT).show()
        }
    }
}