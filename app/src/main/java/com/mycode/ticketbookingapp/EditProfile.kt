package com.mycode.ticketbookingapp

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.editprofile.*
import kotlinx.android.synthetic.main.profile.*
import java.util.*


class EditProfile : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editprofile)

        findViewById<ProgressBar>(R.id.loading_spinner).visibility = View.GONE
        findViewById<EditText>(R.id.userdp).setShowSoftInputOnFocus(false)

      findViewById<TextView>(R.id.birthday).setOnClickListener {
            val today = Calendar.getInstance()
            val year = today.get(Calendar.YEAR)
            val month =today.get(Calendar.MONTH)
            val day = today.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog=DatePickerDialog(this@EditProfile, { view, year, monthOfYear, dayOfMonth ->
                findViewById<TextView>(R.id.birthday).setText("$dayOfMonth/$monthOfYear/$year")
            }, year, month, day)
                datePickerDialog.datePicker.maxDate=Date().time
                datePickerDialog.show()
            }

        database = FirebaseDatabase.getInstance()

        supportActionBar?.title = "Edit Profile"

           val uid=FirebaseAuth.getInstance().uid
            val ref1 = FirebaseDatabase.getInstance().getReference("/User/$uid")
            ref1.addListenerForSingleValueEvent(object : ValueEventListener {
                @RequiresApi(Build.VERSION_CODES.P)
                override fun onDataChange(p0: DataSnapshot) {
                    val user = p0.getValue(Users::class.java)
                    if (user != null) {
                        if (user.profilepic != "") {
                            findViewById<EditText>(R.id.name).setText(user.username)
                            findViewById<EditText>(R.id.email).setText(user.email)
                            Picasso.with(this@EditProfile).load(user.profilepic)
                                .into(findViewById<CircleImageView>(R.id.userdp1))
                            findViewById<EditText>(R.id.userdp).alpha = 0f
                        } else {
                            findViewById<EditText>(R.id.name).setText(user.username)
                            findViewById<EditText>(R.id.email).setText(user.email)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })


        findViewById<Button>(R.id.savechanges).setOnClickListener {
            savechanges()


    }
        findViewById<CircleImageView>(R.id.userdp1).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }

    var selectedPhotoUri: Uri? = null

    @RequiresApi(Build.VERSION_CODES.P)
    //If the image is selected to start the activity of gallery and assigning it to @selectedPhotoUri
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            Log.d("RegisterActivity", "Photo was selected")

            selectedPhotoUri = data.data

            Picasso.with(this@EditProfile).load(selectedPhotoUri).into(findViewById<CircleImageView>(R.id.userdp1)).toString()
            findViewById<EditText>(R.id.userdp).alpha = 0f //to no inflate image view

            uploadImageToFirebaseStorage()

        }
    }

    private fun uploadImageToFirebaseStorage() {
        findViewById<ProgressBar>(R.id.loading_spinner).visibility = View.VISIBLE

         val filename = UUID.randomUUID().toString()
         val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("EditProfile", "Successfully uploaded image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    Log.d("EditProfile", "File Location:$it")
                    savechanges1(it.toString())
                }
            }
    }

private fun savechanges1(profilepicture:String){
    val uid=FirebaseAuth.getInstance().uid
    val ref1 = FirebaseDatabase.getInstance().getReference("/User/$uid")

    ref1.addListenerForSingleValueEvent(object : ValueEventListener {
        @RequiresApi(Build.VERSION_CODES.P)
        override fun onDataChange(p0: DataSnapshot) {
            val user = p0.getValue(Users::class.java)
            if (user != null) {
                val fieldname = user.uid
                val ref2 = database.getReference()
                ref2.child("User/$fieldname/profilepic").setValue(profilepicture)

            findViewById<ProgressBar>(R.id.loading_spinner).visibility = View.GONE

            Toast.makeText(this@EditProfile, "Profile picture is updated", Toast.LENGTH_LONG).show()

           }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    })
}

    private fun savechanges() {
        findViewById<ProgressBar>(R.id.loading_spinner).visibility = View.VISIBLE

        val uid=FirebaseAuth.getInstance().uid
        val ref1 = FirebaseDatabase.getInstance().getReference("/User/$uid")

        ref1.addListenerForSingleValueEvent(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.P)
            override fun onDataChange(p0: DataSnapshot) {
                    val user = p0.getValue(Users::class.java)
                    if (user != null) {
                        val fieldname = user.uid
                        val ref2 = database.getReference()
                        val Name = findViewById<EditText>(R.id.name).text.toString()
                        val Email = findViewById<EditText>(R.id.email).text.toString()
                        val location = findViewById<EditText>(R.id.location).text.toString()
                        val mobileno = findViewById<EditText>(R.id.mobileno).text.toString()
                        val birthday = findViewById<TextView>(R.id.birthday).text.toString()
                        val gender = findViewById<EditText>(R.id.gender).text.toString()

                        if (Name != user.username) {
                            ref2.child("User/$fieldname/username").setValue(Name)
                        }

                        if (Email != user.email) {
                            ref2.child("User/$fieldname/email").setValue(Email)
                        }

                        if (location != "") {
                            ref2.child("User/$fieldname/location").setValue(location)
                        }

                        if (mobileno != "") {
                            ref2.child("User/$fieldname/mobile no").setValue(mobileno)
                        }

                        if (birthday != "") {
                            ref2.child("User/$fieldname/birthday").setValue(birthday)
                        }

                        if (gender != "") {
                            ref2.child("User/$fieldname/birthday").setValue(gender)
                        }
                    }

                findViewById<ProgressBar>(R.id.loading_spinner).visibility = View.GONE
                Toast.makeText(this@EditProfile,"Profile is updated successfully",Toast.LENGTH_LONG).show()
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}






