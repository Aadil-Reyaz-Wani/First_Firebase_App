package com.example.myfirebaseapp


import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        val welcomeText = view.findViewById<TextView>(R.id.welcome_text)

        val welcome = "Welcome"
        val spannableString = SpannableString(welcome)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FF0000")), 0, 5, 0)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#312222")), 5, welcome.length, 0)
        welcomeText.text = spannableString



        lifecycleScope.launch {
            delay(1000)

            val navOptions = NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_fragmentSignin, null, navOptions)
        }

        return view
    }
}