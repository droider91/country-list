package com.ila.sample.entities

class CountriesP(
    var list: List<CountriesItemP>
)

data class CountriesItemP(
    val flag: String,val flagsP: FlagsP, val name: String
)


data class FlagsP(
    val png: String
)