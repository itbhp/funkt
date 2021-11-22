package it.twinsbrain.funkt.optics

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class IsoTest {
  private val underTest = IsoPointPair()

  @Test
  internal fun `should work either way`() {
    val point = Point(3, 4)
    val pair = 5 to 10
    assertThat(underTest.get(point)).isEqualTo(3 to 4)
    assertThat(underTest.reverseGet(pair)).isEqualTo(Point(5, 10))
  }
}

data class Point(val x: Int, val y: Int)

class IsoPointPair: Iso<Point, Pair<Int,Int>> {
  override fun get(source: Point): Pair<Int, Int> = source.x to source.y

  override fun reverseGet(target: Pair<Int, Int>): Point = Point(target.first, target.second)
}