package it.twinsbrain.funkt.optics

interface Iso<A, B> {
  fun get(source: A): B
  fun reverseGet(target: B): A
}