package com.mycode.ticketbookingapp.welcome


import android.app.Activity
import android.app.Application
import android.content.ContentValues
import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.mycode.ticketbookingapp.HomePage
import com.mycode.ticketbookingapp.MainActivity
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentWelcomeBinding
import com.mycode.ticketbookingapp.signup.SignUpViewModel
import com.mycode.ticketbookingapp.signup.SignUpViewModelFactory
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment() {

    companion object {
        private const val TAG="signIn"
        private const val RC_SIGN_IN = 78
    }
    private lateinit var welcomeViewModel: WelcomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

    val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome,container,false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        val application = requireNotNull(this.activity).application
        val viewModelFactory= WelcomeViewModelFactory(application)
        val activity: Activity = requireNotNull(this.activity)

        welcomeViewModel = ViewModelProvider(this,viewModelFactory).get(WelcomeViewModel::class.java)

        binding.welcomeViewModel=welcomeViewModel
        binding.lifecycleOwner=this

        welcomeViewModel.firebaseUser.observe(viewLifecycleOwner, Observer{
            if(it!=null){
                Toast.makeText(application,"Welcome!",Toast.LENGTH_LONG).show()
                val intent= Intent(application, HomePage::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

             }
        })


        welcomeViewModel.navigateTo.observe(viewLifecycleOwner, Observer {
             if(it) {
                 this.findNavController().navigate(WelcomeFragmentDirections.actionWelcomePageToSigninPage())
                 welcomeViewModel.alreadyHaveAccountDone()
             }

    })

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(activity, gso)

        binding.btngoogle.setOnClickListener{
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    return binding.root
}


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.w(ContentValues.TAG, "Signup" + requestCode)

        if (requestCode == RC_SIGN_IN) {

            // this task is responsible for getting ACCOUNT SELECTED
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
            val account = task.getResult(ApiException::class.java)!!
            //Toast.makeText(this, "processing", Toast.LENGTH_SHORT).show()
            welcomeViewModel.signInWithGoogle(account.idToken!!)

            Toast.makeText(activity, "Signed In Successfully", Toast.LENGTH_SHORT).show()

            } catch (e: ApiException) {
            // Log.i("MainActivity",e.localizedMessage)
            Toast.makeText(activity, e.localizedMessage, Toast.LENGTH_SHORT).show()
             }
        }
    }
}