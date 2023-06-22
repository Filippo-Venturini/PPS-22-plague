package model.configuration

import model.configuration.Builders.{RegionBuilder}
import model.world.RegionConfiguration
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class RegionBuilderTest {

  private val regionConfiguration = RegionConfiguration("Europe", 60_000_000, 0, 0 ,0, 0, 0)
  private var regionBuilder: RegionBuilder = RegionBuilder()

  @Before
  def beforeAll(): Unit ={
    regionBuilder = RegionBuilder()
  }

  @Test
  def testNameIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.name)
  }

  @Test
  def testSetName(): Unit = {
    assertEquals(Some(regionConfiguration.name), regionBuilder.setName(regionConfiguration.name).name)
  }

  @Test
  def testPopulationIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.name)
  }

  @Test
  def testSetPopulation(): Unit = {
    assertEquals(Some(regionConfiguration.population), regionBuilder.setPopulation(regionConfiguration.population).population)
  }

  @Test
  def testRichnessIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.name)
  }

  @Test
  def testSetRichness(): Unit = {
    assertEquals(Some(regionConfiguration.richness), regionBuilder.setRichness(regionConfiguration.richness).richness)
  }

  @Test
  def testClimateIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.climate)
  }

  @Test
  def testSetClimate(): Unit = {
    assertEquals(Some(regionConfiguration.climate), regionBuilder.setClimate(regionConfiguration.climate).climate)
  }





}

