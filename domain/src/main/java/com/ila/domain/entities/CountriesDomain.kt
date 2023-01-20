package com.ila.domain.entities

class CountriesDomain(
    var list: List<CountriesItemDomain>
)

data class CountriesItemDomain(
    val flag: String, val flags: FlagsDomain, val name: String, val languages: List<LanguageDomain>
)


data class FlagsDomain(
    val png: String
)
data class LanguageDomain(
    val name: String
)