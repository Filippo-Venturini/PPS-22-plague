package utils

import org.junit.Test
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.{Before, Test}
import utils.Iterables.*

class TestIterables {
  @Test
  def testGetRandomOnEmptyList(): Unit =
    val list: List[Int] = List()
    assertEquals(None, list.getRandomElement())

  @Test
  def testGetRandomWithOneElementList(): Unit =
    val list: List[Int] = List(10)
    assertEquals(Some(10), list.getRandomElement())

  @Test
  def testGetRandomWithMultipleElementsList(): Unit =
    val list: List[Int] = (0 to 100).toList
    assertTrue(list.contains(list.getRandomElement().get))

}
