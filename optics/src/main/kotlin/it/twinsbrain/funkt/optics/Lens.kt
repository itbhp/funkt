package it.twinsbrain.funkt.optics

import it.twinsbrain.funkt.functions.FunctionsExtensions.andThen
import it.twinsbrain.funkt.functions.FunctionsExtensions.curried

interface Lens<S, T> {

  fun get(s: S): T
  fun set(s: S, newT: T): S
  fun modify(s: S, f: (T) -> T): S {
    val setOnS = this::set.curried()(s)
    val applyTo = this::get andThen f andThen setOnS
    return applyTo(s)
  }

  fun <A> combine(l2: Lens<T, A>): Lens<S, A> {
    val self = this
    return object : Lens<S, A> {

      override fun get(s: S): A {
        val function: (s: S) -> A = self::get andThen l2::get
        return function(s)
      }

      override fun set(s: S, newT: A): S {
        val newT1 = l2.set(self.get(s), newT)
        return self.set(s, newT1)
      }
    }
  }
}
