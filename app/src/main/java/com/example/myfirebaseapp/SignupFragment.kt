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


class SignupFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize firebase Auth
        auth = FirebaseAuth.getInstance()

        // Id's of SignIn and Register Buttons of SignUp Fragment
        val signInButton = view.findViewById<Button>(R.id.signin_button)
        val registerButton = view.findViewById<Button>(R.id.register_button)

        //Id's of EditText fields of SignUp Fragment
        val editTextEmail = view.findViewById<EditText>(R.id.editTextEmail)
        val editTextUsername = view.findViewById<EditText>(R.id.editTextUsername)
        val editTextPassword = view.findViewById<EditText>(R.id.editTextPassword)
        val editTextRepeatPassword = view.findViewById<EditText>(R.id.editTextRepeatPassword)

        // Navigating on button from SignUp page to SignIn page
        signInButton.setOnClickListener {
//            val navOptions = NavOptions.Builder().setPopUpTo(R.id.signupFragment, true).build()
            Navigation.findNavController(view)
                .navigate(R.id.action_signupFragment_to_fragmentSignin)
        }


        registerButton.setOnClickListener {
            // Converting the id's to String
            val email = editTextEmail.text.toString()
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()
            val rePassword = editTextRepeatPassword.text.toString()

            // Check if any field is blank or passwords are identical
            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                Toast.makeText(requireContext(), "All Fields must be filled", Toast.LENGTH_SHORT).show()
            } else if (password != rePassword) {
                Toast.makeText(requireContext(), "Password and Repeat passwords must be same",  Toast.LENGTH_SHORT).show()
            }else {


                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {

                            Log.d("SignupFragment", "User registered successfully!")  // Debug

                            Toast.makeText(requireContext(), "Registration Successful", Toast.LENGTH_SHORT).show()

                            auth.signOut()

                            Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_fragmentSignin)

                        } else {

                            Log.e("SignupFragment", "Signup Failed: ${task.exception?.message}")

                            Toast.makeText(requireContext(), "Registration Failed ${task.exception?.message}", Toast.LENGTH_SHORT).show()

                        }
                    }



            }
        }









    }
}