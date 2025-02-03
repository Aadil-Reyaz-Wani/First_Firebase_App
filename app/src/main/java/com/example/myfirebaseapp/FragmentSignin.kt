package com.example.myfirebaseapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FragmentSignin : Fragment() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_signin, container, false)
        val signup_button = view.findViewById<Button>(R.id.register_button)

        signup_button.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_fragmentSignin_to_signupFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Check if the user is loggedIn already
        val currentUser : FirebaseUser? = auth.currentUser
        Log.d("SignIn Fragment", "Current User: $currentUser") // Debug
        if (currentUser != null) {
            Log.d("SignIn Fragment", "Navigating to HomeFragment")  // Debug
            Navigation.findNavController(view).navigate(R.id.action_fragmentSignin_to_homeFragment)
        }



        // Id's of Buttons
        val signInButton = view.findViewById<Button>(R.id.signin_button)
        val signupButton = view.findViewById<Button>(R.id.register_button)

        // Id's of Text Fields
        val emailEditText = view.findViewById<EditText>(R.id.etEmail)
        val editTextPassword = view.findViewById<EditText>(R.id.etPassword)

        signupButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_fragmentSignin_to_signupFragment)
        }

        signInButton.setOnClickListener {
            val emailSignIn = emailEditText.text.toString()
            val passwordSignIn = editTextPassword.text.toString()

            if (emailSignIn.isEmpty() || passwordSignIn.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all the details", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(emailSignIn, passwordSignIn)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("SignIn Fragment.!", "User Signed in Successfully")
                            Toast.makeText(
                                requireContext(),
                                "Logged In Successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            Navigation.findNavController(view).navigate(R.id.action_fragmentSignin_to_homeFragment)

                        } else {
                            Log.e("SignIn Fragment", "Login Failed: ${task.exception?.message}")
                            Toast.makeText(
                                requireContext(),
                                "Invalid credentials ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

    }


}