package com.mycode.ticketbookingapp.homefragment.moviedescription

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycode.ticketbookingapp.network.MovieDetails
import com.mycode.ticketbookingapp.network.TMBDApi
import com.mycode.ticketbookingapp.network.TMBDConstants
import com.mycode.ticketbookingapp.network.genres
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await

class MovieDescriptionViewModel(id: String):ViewModel() {
    private val _selectedProperty = MutableLiveData<MovieDetails>()

    val selectedProperty: LiveData<MovieDetails>
        get() = _selectedProperty

    private val _selectedPropertyGenres = MutableLiveData<String>()

    val selectedPropertyGenres: LiveData<String>
        get() = _selectedPropertyGenres
    private val _selectedvid = MutableLiveData<String>()

    val selectedvid: LiveData<String>
        get() = _selectedvid
    private val _navigateToBookingTicketPage1 = MutableLiveData<Boolean>()
    val navigateToBookingTicketPage1: LiveData<Boolean>
        get() = _navigateToBookingTicketPage1

    fun navigateToBookingTicketPage1() {
        _navigateToBookingTicketPage1.value = true
    }


    var viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    var months: List<String> = listOf(
        "January",
        "February",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )

    init {
        getMovieDetails(id)
    }

    fun getMovieDetails(id:String){
        coroutineScope.launch {
            var getMovieDetails =
                TMBDApi.retrofitService.getMovieDetails(id, TMBDConstants.API_KEY)

            var getTrailerVideo =
                TMBDApi.retrofitService.getMovieTrailer(id,TMBDConstants.API_KEY)
            try {

                var listResult = getMovieDetails.await()
                var month = listResult.release_date.substring(5,7)
                var date:String

                when (month) {
                    "01" -> date=months[0]
                    "02" -> date=months[1]
                    "03" -> date=months[2]
                    "04" -> date=months[3]
                    "05" -> date=months[4]
                    "06" -> date=months[5]
                    "07" -> date=months[6]
                    "08" -> date=months[7]
                    "09" -> date=months[8]
                    "10" -> date=months[9]
                    "11" -> date=months[10]
                    "12" -> date=months[11]
                    else -> date=""
                }
                Log.d("ApiData", listResult.toString())
                date = listResult.release_date.substring(8,10) + " " + date + ", " + listResult.release_date.substring(0,4)
                listResult.release_date = date
                _selectedProperty.value=listResult


                val genre=getGenres(listResult.genres)
                _selectedPropertyGenres.value=genre.toString()



                var videoResult = getTrailerVideo.await()
                val local:MutableList<String> = mutableListOf()
                videoResult.results.forEach {
                     local.add(it.key)
                }

                val key=local[0]
                _selectedvid.value=key
                Log.d("Videokey",key)



            } catch (e: Exception) {
                Log.d("Exception", "${e}")
            }
        }
    }
    fun getGenres(genres: List<genres>):List<String> {
        val local: MutableList<String> = mutableListOf()
        var index = 0
        genres.forEach {
            if(index<3) {
                val str: String = it.name
                local.add(str)
                Log.d(
                    "Api Data",
                    it.name
                )
            }
            index=index+1

        }
     return local
    }


}
