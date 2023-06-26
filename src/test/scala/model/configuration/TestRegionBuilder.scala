package model.configuration

import model.configuration.Builders.RegionBuilder
import model.world.BasicRegion
import model.world.RegionTypes.*
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.{Before, Test}

class TestRegionBuilder {

  private val configuration = RegionConfiguration("Central-Europe", 60_000_000, 0, 0 ,0, 0, 0)
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
    assertEquals(Some(configuration.name), regionBuilder.setName(configuration.name).name)
  }

  @Test
  def testPopulationIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.name)
  }

  @Test
  def testSetPopulation(): Unit = {
    assertEquals(Some(configuration.population), regionBuilder.setPopulation(configuration.population).population)
  }

  @Test
  def testRichnessIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.name)
  }

  @Test
  def testSetRichness(): Unit = {
    assertEquals(Some(configuration.richness), regionBuilder.setRichness(configuration.richness).richness)
  }

  @Test
  def testClimateIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.climate)
  }

  @Test
  def testSetClimate(): Unit = {
    assertEquals(Some(configuration.climate), regionBuilder.setClimate(configuration.climate).climate)
  }

  @Test
  def testBordersControlIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.bordersControl)
  }

  @Test
  def testSetBordersControl(): Unit = {
    assertEquals(Some(configuration.bordersControl), regionBuilder.setBordersControl(configuration.bordersControl).bordersControl)
  }

  @Test
  def testGlobalizationIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.globalization)
  }

  @Test
  def testSetGlobalization(): Unit = {
    assertEquals(Some(configuration.globalization), regionBuilder.setGlobalization(configuration.globalization).globalization)
  }

  @Test
  def testPopulationDensityIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.populationDensity)
  }

  @Test
  def testSetPopulationDensity(): Unit = {
    assertEquals(Some(configuration.populationDensity), regionBuilder.setPopulationDensity(configuration.populationDensity).populationDensity)
  }

  @Test
  def testBorderingRegionsIsEmptyByDefault(): Unit = {
    assertEquals(List(), regionBuilder.borderingRegionsIds)
  }

  @Test
  def testSetBorderingRegions(): Unit = {
    assertEquals(borderingRegionsIds, regionBuilder.setBorderingRegions(borderingRegionsIds).borderingRegionsIds)
  }

  @Test
  def testBuildReturnsNoneIfMandatoryFieldsAreMissing(): Unit = {
    assertEquals(None, regionBuilder.build())
  }

  @Test
  def testBuildReturnsBasicRegionIfMandatoryFieldsAreGiven(): Unit = {
    regionBuilder = regionBuilder
      .setName(configuration.name)
      .setClimate(configuration.climate)
      .setRichness(configuration.richness)
      .setBordersControl(configuration.bordersControl)
      .setPopulationDensity(configuration.populationDensity)
      .setPopulation(configuration.population)
      .setGlobalization(configuration.globalization)
    assertTrue(regionBuilder.build().get.isInstanceOf[BasicRegion])
  }

}

