package com.mycode.ticketbookingapp.homefragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentHomeBinding
import com.mycode.ticketbookingapp.network.TMBDConstants
import com.mycode.ticketbookingapp.signup.SignUpFragment
import com.mycode.ticketbookingapp.welcome.WelcomeFragmentDirections
import com.mycode.ticketbookingapp.welcome.WelcomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentHomeBinding=
            DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.homeViewModel=homeViewModel

        binding.lifecycleOwner=this


        val homeRecyclerViewAdapter=HomeRecyclerViewAdapter()

        binding.homeRecyclerView.adapter = homeRecyclerViewAdapter

        homeViewModel.homeFeed.observe(viewLifecycleOwner, Observer{
            it?.let{
                binding.loadingSpinnerH.visibility=View.GONE
                homeRecyclerViewAdapter.items=it
            }
        })
        return binding.root
    }


}