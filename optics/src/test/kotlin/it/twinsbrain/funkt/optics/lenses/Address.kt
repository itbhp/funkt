package it.twinsbrain.funkt.optics.lenses

import it.twinsbrain.funkt.optics.Lens

data class Address(val streetName: String, val number: String, val city: String) {
    companion object {
        val streetName: Lens<Address, String> = object : Lens<Address, String> {
            override fun get(s: Address): String = s.streetName
            override fun set(s: Address, newT: String): Address = s.copy(streetName = newT)
        }

        val number: Lens<Address, String> = object : Lens<Address, String> {
            override fun get(s: Address): String = s.number
            override fun set(s: Address, newT: String): Address = s.copy(number = newT)
        }

        val city: Lens<Address, String> = object : Lens<Address, String> {
            override fun get(s: Address): String = s.city
            override fun set(s: Address, newT: String): Address = s.copy(city = newT)
        }
    }
}
