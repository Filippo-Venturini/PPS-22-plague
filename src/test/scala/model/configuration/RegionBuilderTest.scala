package model.configuration

import model.configuration.Builders.RegionBuilder
import model.world.BasicRegion
import model.world.RegionTypes.*
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class RegionBuilderTest {

  private val regionConfiguration = RegionConfiguration("Central-Europe", 60_000_000, 0, 0 ,0, 0, 0)
  private val borderingRegionsIds = List("Northern-Europe", "Balkans")
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

  @Test
  def testBordersControlIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.bordersControl)
  }

  @Test
  def testSetBordersControl(): Unit = {
    assertEquals(Some(regionConfiguration.bordersControl), regionBuilder.setBordersControl(regionConfiguration.bordersControl).bordersControl)
  }

  @Test
  def testGlobalizationIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.globalization)
  }

  @Test
  def testSetGlobalization(): Unit = {
    assertEquals(Some(regionConfiguration.globalization), regionBuilder.setGlobalization(regionConfiguration.globalization).globalization)
  }

  @Test
  def testPopulationDensityIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.populationDensity)
  }

  @Test
  def testSetPopulationDensity(): Unit = {
    assertEquals(Some(regionConfiguration.populationDensity), regionBuilder.setPopulationDensity(regionConfiguration.populationDensity).populationDensity)
  }

  @Test
  def testBorderingRegionsIsEmptyByDefault(): Unit = {
    assertEquals(List(), regionBuilder.borderingRegionsIds)
  }

  @Test
  def testSetBorderingRegions(): Unit = {
    assertEquals(borderingRegionsIds, regionBuilder.setBorderingRegions(borderingRegionsIds).borderingRegionsIds)
  }

}

