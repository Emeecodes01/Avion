package com.mobigod.avin.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**Created by: Emmanuel Ozibo
//on: 08, 2020-02-08
//at: 11:15*/
object Tools {

    fun getDataFromString(dateTime: String): String {
        val correctdate = dateTime.replace("T", "")
        val sdf = SimpleDateFormat("yyyy-MM-ddHH:mm", Locale.ENGLISH)
        val dT = sdf.parse(correctdate)
        val calender = Calendar.getInstance()
        calender.time = dT
        val DOW = calender.get(Calendar.DAY_OF_WEEK)
        val dayOfWeek = getReadableDay(DOW)
        val monthOfYear = getShortMonth(calender.get(Calendar.MONTH))
        return "$dayOfWeek, $monthOfYear, ${calender.get(Calendar.DAY_OF_MONTH)}"
    }

    fun getFlightDuration(flightDuration: String): String {
        //remove pdthm
        return flightDuration.replace("PT", "")
    }

    fun getTimeFromString(dateTime: String): String {
        val correctTime = dateTime.replace("T", "")
        val sdf = SimpleDateFormat("yyyy-MM-ddHH:mm", Locale.ENGLISH)
        val dT = sdf.parse(correctTime)
        val calender = Calendar.getInstance()
        calender.time = dT
        return "${getAccurateTime(calender.get(Calendar.HOUR_OF_DAY))}:${getAccurateTime(calender.get(Calendar.MINUTE))}"
    }



    /**
     * This just adds zero to the beginning of the string if it's just a digit
     */
    private fun getAccurateTime(time: Int): String{
        if (time.toString().length == 1){
            return "0$time"
        }
        return "$time"
    }


    private fun getReadableDay(dayOfWeek: Int): String{
        return when(dayOfWeek){
            Calendar.SUNDAY -> "Sunday"
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            else -> ""
        }
    }


    private fun getShortMonth(month: Int) : String {
        return when(month) {
            Calendar.JANUARY -> "Jan"
            Calendar.FEBRUARY -> "Feb"
            Calendar.MARCH -> "Mar"
            Calendar.APRIL -> "Apr"
            Calendar.MAY -> "May"
            Calendar.JUNE -> "June"
            Calendar.JULY -> "July"
            Calendar.AUGUST -> "Aug"
            Calendar.SEPTEMBER -> "Sep"
            Calendar.OCTOBER -> "Oct"
            Calendar.NOVEMBER -> "Nov"
            Calendar.DECEMBER -> "Dec"
            else -> ""
        }
    }

}