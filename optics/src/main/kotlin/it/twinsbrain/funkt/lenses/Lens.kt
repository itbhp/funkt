package it.twinsbrain.funkt.lenses

import it.twinsbrain.funkt.functions.FunctionsExtensions.andThen

interface Lens<S, T> {

  fun get(s: S): T
  fun set(newT: T, s: S): S

  fun <A> combine(l2: Lens<T, A>): Lens<S, A> {
    val self = this
    return object : Lens<S, A> {
      override fun get(s: S): A {
        val function: (s: S) -> A = self::get andThen l2::get
        return function(s)
      }

      override fun set(newA: A, s: S): S {
        val newT: T = l2.set(newA, self.get(s))
        return self.set(newT, s)
      }
    }
  }
}
