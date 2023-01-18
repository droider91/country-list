package com.ila.sample.mapper

import com.ila.domain.entities.CountriesDomain
import com.ila.sample.entities.CountriesItemP
import com.ila.sample.entities.CountriesP
import com.ila.sample.entities.FlagsP

/**
 * @author by devendra on 26/08/2022
 */
object CountryEntityMapper {

    fun toPresentation(countriesDomain: CountriesDomain): CountriesP {
        return CountriesP(countriesDomain.list.map {
            CountriesItemP(
                it.flag,
                FlagsP(it.flags.png),
                it.name
            )
        })
    }
}