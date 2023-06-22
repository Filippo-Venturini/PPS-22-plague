package model.configuration


import model.configuration.Builders.{RegionBuilder, SimpleRegionBuilder}
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class RegionBuilderTest {

  var regionBuilder: RegionBuilder = new SimpleRegionBuilder()

  /*@Test
  def testCannotBuildWithoutSettingMandatoryFields(): Unit =
    assertEquals(None, regionBuilder.build())*/

}

