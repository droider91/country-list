package com.ila.data.entities


data class CountriesItemData(
    val flag: String, val flags: FlagsD, val name: String, val languages: ArrayList<Language>
)

data class FlagsD(
    val png: String, val svg: String
)

data class Language(
    val name: String
)