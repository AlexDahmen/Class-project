package se.linerotech.module206.project1.country

import se.linerotech.module206.project1.common.CountryData

sealed class CountryUIState() {
    object Loading: CountryUIState()
    data class Loaded(val countries: List<CountryData>): CountryUIState()
    object Failure: CountryUIState()
}
