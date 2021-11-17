package it.twinsbrain.funkt.lenses

data class Person(val name:String, val address: Address) {
  companion object {
    val name: Lens<Person, String> = object: Lens<Person, String> {
      override fun get(s: Person): String = s.name
      override fun set(newT: String, s: Person): Person = s.copy(name = newT)
    }
    val address: Lens<Person, Address> = object : Lens<Person, Address> {
      override fun get(s: Person): Address = s.address
      override fun set(newT: Address, s: Person): Person = s.copy(address = newT)
    }

  }
}