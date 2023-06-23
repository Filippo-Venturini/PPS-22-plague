package model.configuration

import model.configuration.Builders.RegionBuilder
import model.world.RegionTypes.*
import org.junit.Assert.{assertEquals, assertTrue, assertFalse}
import org.junit.{Before, Test}

object TestReaders {

  class TestTypeChecker {

    import model.configuration.Readers.TypeChecker.*

    @Test
    def testCorrectCast(): Unit =
      val castingOperations: List[String => Any] = List(_.toIntOption, _.toFloatOption, _.toBooleanOption)
      assertTrue(checkTypes(List("1", "2.5", "true"), castingOperations))

    @Test
    def testWrongIntCast(): Unit =
      val castingOperations: List[String => Any] = List(_.toIntOption, _.toFloatOption, _.toBooleanOption)
      assertFalse(checkTypes(List("1a", "2.5", "true"), castingOperations))

    @Test
    def testWrongBooleanCast(): Unit =
      val castingOperations: List[String => Any] = List(_.toIntOption, _.toFloatOption, _.toBooleanOption)
      assertFalse(checkTypes(List("1", "2.5", "truee"), castingOperations))

    @Test
    def testWrongFloatCast(): Unit =
      val castingOperations: List[String => Any] = List(_.toIntOption, _.toFloatOption, _.toBooleanOption)
      assertFalse(checkTypes(List("1", "2.5a", "true"), castingOperations))


  }

}
