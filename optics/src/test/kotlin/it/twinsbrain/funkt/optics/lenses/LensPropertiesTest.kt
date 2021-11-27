package it.twinsbrain.funkt.optics.lenses

import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.flatMap
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.string
import io.kotest.property.forAll

class LensPropertiesTest : StringSpec({
    "modify" {
        forAll(personGenerator) { person ->
            val lens = Person.address.number
            val previousNumber = person.address.number
            val newPerson = lens.modify(person) { "43" }
            person.address.number == previousNumber &&
                newPerson.address.number == "43"
        }
    }
})

private val addressGenerator = arbitrary {
    val streetName = Arb.string(size = 30).bind()
    val city = Arb.string(size = 30).bind()
    val number = Arb.int(min = 1, max = 400).bind()
    Address(streetName, number.toString(), city)
}

private val personGenerator = addressGenerator.flatMap { address ->
    Arb.string(size = 30).map { name ->
        Person(name, address)
    }
}
