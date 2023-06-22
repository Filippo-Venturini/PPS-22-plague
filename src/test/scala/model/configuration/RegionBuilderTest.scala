package model.configuration

import model.configuration.Builders.{RegionBuilder}
import model.world.RegionConfiguration
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class RegionBuilderTest {

  private val regionConfiguration = RegionConfiguration("Europe", 60_000_000, 0, 0 ,0, 0, 0)
  private var regionBuilder: RegionBuilder = new RegionBuilder()

  @Before
  def beforeAll(): Unit ={
    regionBuilder = new RegionBuilder()
  }

  @Test
  def testCannotBuildWithoutSettingMandatoryFields(): Unit =
    assertEquals(None, regionBuilder.build())

  @Test
  def testSetNameReturnsTheBuilder(): Unit =
    assertEquals(regionBuilder, regionBuilder.setName(regionConfiguration.name))

  @Test
  def testSetPopulationReturnsTheBuilder(): Unit =
    assertEquals(regionBuilder, regionBuilder.setPopulation(regionConfiguration.population))



}

