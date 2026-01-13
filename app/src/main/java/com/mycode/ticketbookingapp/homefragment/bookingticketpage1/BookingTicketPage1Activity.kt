package com.mycode.ticketbookingapp.homefragment.bookingticketpage1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycode.ticketbookingapp.databinding.ActivityBookingTicketPage1Binding

class BookingTicketPage1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingTicketPage1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookingTicketPage1Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}