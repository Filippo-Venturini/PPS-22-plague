package model.configuration

import model.configuration.builders.Builders.RegionBuilder
import model.world.{BasicRegion, RegionWithAirport, RegionWithAirportAndPort, RegionWithPort}
import model.world.RegionParameters.*
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.{Before, Test}

class TestRegionBuilder {

  private val configuration = RegionConfiguration("Central-Europe", 60_000_000, 1, 1 ,1, 1, 1)
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
  def testNameCantContainDigits(): Unit = {
    assertEquals(None, regionBuilder.setName("foo1").name)
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
  def testHasPortIsFalseByDefault(): Unit = {
    assertFalse(regionBuilder.hasPort)
  }

  @Test
  def testHasAirportIsFalseByDefault(): Unit = {
    assertFalse(regionBuilder.hasAirport)
  }

  @Test
  def testSetHasPort(): Unit = {
    assertTrue(regionBuilder.addPort.hasPort)
  }

  @Test
  def testSetHasAirport(): Unit = {
    assertTrue(regionBuilder.addAirport.hasAirport)
  }

  @Test
  def testBuildReturnsNoneIfMandatoryFieldsAreMissing(): Unit = {
    assertEquals(None, regionBuilder.build())
  }

  private def setMandatoryParameters(regionBuilder: RegionBuilder): RegionBuilder = {
    regionBuilder
      .setName(configuration.name)
      .setClimate(configuration.climate)
      .setRichness(configuration.richness)
      .setBordersControl(configuration.bordersControl)
      .setPopulationDensity(configuration.populationDensity)
      .setPopulation(configuration.population)
      .setGlobalization(configuration.globalization)
  }

  @Test
  def testBuildBasicRegion(): Unit = {
    regionBuilder = setMandatoryParameters(regionBuilder)
    assertTrue(regionBuilder.build().get.isInstanceOf[BasicRegion])
  }

  @Test
  def testBuildRegionWithPort(): Unit = {
    regionBuilder = setMandatoryParameters(regionBuilder).addPort
    assertTrue(regionBuilder.build().get.isInstanceOf[RegionWithPort])
  }

  @Test
  def testBuildRegionWithAirport(): Unit = {
    regionBuilder = setMandatoryParameters(regionBuilder).addAirport
    assertTrue(regionBuilder.build().get.isInstanceOf[RegionWithAirport])
  }

  @Test
  def testBuildRegionWithAirportAndPort(): Unit = {
    regionBuilder = setMandatoryParameters(regionBuilder).addPort.addAirport
    assertTrue(regionBuilder.build().get.isInstanceOf[RegionWithAirportAndPort])
  }

  @Test
  def testRichnessOverMax(): Unit = {
    assertEquals(None, regionBuilder.setRichness(maxRichnessValue+1).richness)
  }

  @Test
  def testClimateOverMax(): Unit = {
    assertEquals(None, regionBuilder.setClimate(maxClimateValue + 1).climate)
  }

  @Test
  def testPopulationDensityOverMax(): Unit = {
    assertEquals(None, regionBuilder.setPopulationDensity(maxPopulationDensityValue + 1).populationDensity)
  }

  @Test
  def testBordersControlOverMax(): Unit = {
    assertEquals(None, regionBuilder.setBordersControl(maxBorderControlValue + 1).bordersControl)
  }

  @Test
  def testGlobalizationOverMax(): Unit = {
    assertEquals(None, regionBuilder.setGlobalization(maxGlobalizationValue + 1).globalization)
  }

}

