package com.ila.data.mapper

import com.ila.data.entities.CountriesItemData
import com.ila.domain.entities.CountriesDomain
import com.ila.domain.entities.CountriesItemDomain
import com.ila.domain.entities.FlagsDomain
import com.ila.domain.entities.LanguageDomain

/**
 * Created by devendra on 17/01/2023
 **/
object CountryDataMapper {
    fun toDomain(list: ArrayList<CountriesItemData>): CountriesDomain {
        return CountriesDomain(list.map { CountriesItemDomain(it.flag, FlagsDomain(it.flags.png), it.name, it.languages.map { LanguageDomain(it.name) }) })
    }
}