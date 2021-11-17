package it.twinsbrain.funkt

import it.twinsbrain.funkt.functions.FunctionsExtensions.andThen
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AndThenTest {

  private fun asString(n: Int): String = n.toString()
  private fun takeTwoChars(n: String): String = n.take(2)

  @Test
  fun `should work`() {
    val result = (::asString andThen ::takeTwoChars)(100)
    assertThat(result).isEqualTo("10")
    assertThat(result).isEqualTo(takeTwoChars(asString(100)))
  }
}