package it.twinsbrain.funkt.functions

typealias Function<A, B> = (A) -> B

object FunctionsExtensions {
  infix fun <A, B, C> Function<A, B>.andThen(g: Function<B, C>): Function<A, C> =
    { a -> g(this(a)) }
}