package com.ila.data.entities


data class CountriesItemData(
    val flag: String, val flags: FlagsD, val name: String
)

data class FlagsD(
    val png: String, val svg: String
)