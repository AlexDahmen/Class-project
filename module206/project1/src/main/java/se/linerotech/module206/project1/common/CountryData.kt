package se.linerotech.module206.project1.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryData(
    val country: String,
    val language: String,
    val flagUrl: String,
) : Parcelable
