package it.twinsbrain.funkt.lenses

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private val person = Person(
  name = "Paolo",
  address = Address(streetName = "Gran Via", number = "42", city = "Barcelona")
)

class PersonNameLensTest {

  @Test
  fun get() {
    assertThat(Person.name.get(person)).isEqualTo("Paolo")
  }

  @Test
  fun set() {
    assertThat(Person.name.set("Semplice", person)).isEqualTo(
      Person(
        name = "Semplice",
        address = Address(streetName = "Gran Via", number = "42", city = "Barcelona")
      )
    )
  }
}

class AddressNumberTest {
  @Test
  fun `combined get`() {
    assertThat(Person.address.number.get(person)).isEqualTo("42")
  }

  @Test
  fun `combined set`() {
    assertThat(Person.address.number.set("34", person)).isEqualTo(
      Person(
        name = "Paolo",
        address = Address(streetName = "Gran Via", number = "34", city = "Barcelona")
      )
    )
  }
}

class AddressStreetNameTest {
  @Test
  fun `combined get`() {
    assertThat(Person.address.streetName.get(person)).isEqualTo("Gran Via")
  }

  @Test
  fun `combined set`() {
    assertThat(Person.address.streetName.set("Carrer Villamari", person)).isEqualTo(
      Person(
        name = "Paolo",
        address = Address(streetName = "Carrer Villamari", number = "42", city = "Barcelona")
      )
    )
  }
}

class CityTest {
  @Test
  fun `combined get`() {
    assertThat(Person.address.city.get(person)).isEqualTo("Barcelona")
  }

  @Test
  fun `combined set`() {
    assertThat(Person.address.city.set("Tenerife", person)).isEqualTo(
      Person(
        name = "Paolo",
        address = Address(streetName = "Gran Via", number = "42", city = "Tenerife")
      )
    )
  }
}