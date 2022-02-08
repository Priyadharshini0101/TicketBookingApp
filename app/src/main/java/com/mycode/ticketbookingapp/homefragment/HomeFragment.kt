package com.mycode.ticketbookingapp.homefragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.signup.SignUpFragment


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootview= inflater.inflate(R.layout.fragment_home, container, false)

        return rootview
    }


}