package it.twinsbrain.funkt.lenses

data class Person(val name:String, val address: Address) {
  companion object {
    val name: Lens<Person, String> = object: Lens<Person, String> {
      override fun get(s: Person): String = s.name
      override fun set(newT: String, s: Person): Person = s.copy(name = newT)
    }
    val address: AddressLens = AddressLens()

    class AddressLens : Lens<Person, Address> {
      override fun get(s: Person): Address = s.address
      override fun set(newT: Address, s: Person): Person = s.copy(address = newT)

      val streetName: Lens<Person, String> = this.combine(Address.streetName)
      val number: Lens<Person, String> = this.combine(Address.number)
      val city: Lens<Person, String> = this.combine(Address.city)
    }

  }
}