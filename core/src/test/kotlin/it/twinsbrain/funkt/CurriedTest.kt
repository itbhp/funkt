package it.twinsbrain.funkt

import it.twinsbrain.funkt.functions.FunctionsExtensions.curried
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CurriedTest {
    private fun sum(x: Int, y: Int): Int = x + y

    @Test
    internal fun `curried working`() {
        val sumCurried: (Int) -> (Int) -> Int = ::sum.curried()

        assertThat(sum(2, 5)).isEqualTo(7)
        assertThat(sumCurried(2)(5)).isEqualTo(7)
    }
}
