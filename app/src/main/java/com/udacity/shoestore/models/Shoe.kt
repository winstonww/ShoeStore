package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(var name: String, var size: Double, var company: String, var description: String,
                val images: List<String> = mutableListOf()) : Parcelable {

    override fun toString() : String {
        return "Shoe name: $name \n size: $size \n company: $company\n description: $description \n\n"
    }
}