package com.genthur.healthify

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Disease(
    var name: String,
    var description: String,
    var photo: String,
    var indication: String,
    var treatment: String
) : Parcelable
