package it.twinsbrain.funkt.lenses

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LensTest {

  private val person = Person(
    name = "Paolo",
    address = Address(streetName = "Carrer Diputacion", number = "42", city = "Barcelona")
  )

  @Test
  fun get() {
    assertThat(Person.name.get(person)).isEqualTo("Paolo")
  }

  @Test
  fun set() {
    assertThat(Person.name.set("Semplice", person)).isEqualTo(
      Person(
        name = "Semplice",
        address = Address(streetName = "Carrer Diputacion", number = "42", city = "Barcelona")
      )
    )
  }

  @Test
  fun `combined get`() {
    assertThat(Person.address.combine(Address.city).get(person)).isEqualTo("Barcelona")
  }

  @Test
  fun `combined set`() {
    assertThat(Person.address.combine(Address.city).set("Tenerife", person)).isEqualTo(
      Person(
        name = "Paolo",
        address = Address(streetName = "Carrer Diputacion", number = "42", city = "Tenerife")
      )
    )
  }
}