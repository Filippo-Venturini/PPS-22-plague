package model.configuration


import model.configuration.Builders.{RegionBuilder, SimpleRegionBuilder}
import model.world.RegionConfiguration
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class RegionBuilderTest {

  private val regionConfiguration = RegionConfiguration("Europe", 60_000_000, 0, 0 ,0, 0, 0)
  private var regionBuilder: RegionBuilder = new SimpleRegionBuilder()

  @Before
  def beforeAll(): Unit ={
    regionBuilder = new SimpleRegionBuilder()
  }

  @Test
  def testCannotBuildWithoutSettingMandatoryFields(): Unit =
    assertEquals(None, regionBuilder.build())



}

