package it.twinsbrain.funkt.functions

typealias Function<A, B> = (A) -> B
typealias Function2<A, B, C> = (A, B) -> C

object FunctionsExtensions {
    infix fun <A, B, C> Function<A, B>.andThen(g: Function<B, C>): Function<A, C> =
        { a -> g(this(a)) }

    fun <A, B, C> Function2<A, B, C>.curried(): (A) -> (B) -> C = { a -> { b -> this(a, b) } }
}
