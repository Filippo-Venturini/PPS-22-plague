package model.configuration

import model.configuration.builders.VirusBuilder
import model.infection.VirusConfiguration
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.{Before, Test}

class TestVirusBuilder {
  val configuration: VirusConfiguration = VirusConfiguration("DHT11", 0, 1, 2, 3, 4, 5, 6, true, true)
  private var virusBuilder = VirusBuilder()

  @Before
  def before(): Unit = {
    virusBuilder = VirusBuilder()
  }

  @Test
  def testColdRegionsInfectivityIsUnsetByDefault(): Unit = {
    assertEquals(None, virusBuilder.coldRegionsInfectivity)
  }

  @Test
  def testSetColdRegionsInfectivity(): Unit = {
    virusBuilder = virusBuilder.setColdRegionInfectivity(configuration.coldRegionsInfectivity)
    assertEquals(Some(configuration.coldRegionsInfectivity), virusBuilder.coldRegionsInfectivity)
  }

  @Test
  def testHotRegionsInfectivityIsUnsetByDefault(): Unit = {
    assertEquals(None, virusBuilder.hotRegionsInfectivity)
  }

  @Test
  def testSetWarmRegionsInfectivity(): Unit = {
    virusBuilder = virusBuilder.setHotRegionInfectivity(configuration.hotRegionsInfectivity)
    assertEquals(Some(configuration.hotRegionsInfectivity), virusBuilder.hotRegionsInfectivity)
  }

  @Test
  def testLowDensityRegionsInfectivityIsUnsetByDefault(): Unit = {
    assertEquals(None, virusBuilder.lowDensityRegionsInfectivity)
  }

  @Test
  def testSetLowDensityRegionsInfectivity(): Unit = {
    virusBuilder = virusBuilder.setLowDensityRegionsInfectivity(configuration.lowDensityRegionInfectivity)
    assertEquals(Some(configuration.lowDensityRegionInfectivity), virusBuilder.lowDensityRegionsInfectivity)
  }

  @Test
  def testHighDensityRegionsInfectivityIsUnsetByDefault(): Unit = {
    assertEquals(None, virusBuilder.highDensityRegionsInfectivity)
  }

  @Test
  def testSetHighDensityRegionsInfectivity(): Unit = {
    virusBuilder = virusBuilder.setHighDensityRegionsInfectivity(configuration.highDensityRegionsInfectivity)
    assertEquals(Some(configuration.highDensityRegionsInfectivity), virusBuilder.highDensityRegionsInfectivity)
  }

  @Test
  def testRichRegionsInfectivityIsUnsetByDefault(): Unit = {
    assertEquals(None, virusBuilder.richRegionsInfectivity)
  }

  @Test
  def testSetRichRegionsInfectivity(): Unit = {
    virusBuilder = virusBuilder.setRichRegionsInfectivity(configuration.richRegionsInfectivity)
    assertEquals(Some(configuration.richRegionsInfectivity), virusBuilder.richRegionsInfectivity)
  }

  @Test
  def testPoorRegionsInfectivityIsUnsetByDefault(): Unit = {
    assertEquals(None, virusBuilder.poorRegionsInfectivity)
  }

  @Test
  def testSetPoorRegionsInfectivity(): Unit = {
    virusBuilder = virusBuilder.setPoorRegionsInfectivity(configuration.poorRegionsInfectivity)
    assertEquals(Some(configuration.poorRegionsInfectivity), virusBuilder.poorRegionsInfectivity)
  }

  @Test
  def testVaccineResistanceIsUnsetByDefault(): Unit = {
    assertEquals(None, virusBuilder.vaccineResistance)
  }

  @Test
  def testSetVaccineResistance(): Unit = {
    virusBuilder = virusBuilder.setVaccineResistance(configuration.vaccineResistance)
    assertEquals(Some(configuration.vaccineResistance), virusBuilder.vaccineResistance)
  }

  @Test
  def testAirportEnabledIsUnsetByDefault(): Unit = {
    assertEquals(None, virusBuilder.airportEnabled)
  }

  @Test
  def testAirportEnabled(): Unit = {
    virusBuilder = virusBuilder.setAirportEnabled(configuration.airportEnabled)
    assertEquals(Some(configuration.airportEnabled), virusBuilder.airportEnabled)
  }

  @Test
  def testPortEnabledIsUnsetByDefault(): Unit = {
    assertEquals(None, virusBuilder.portEnabled)
  }

  @Test
  def testPortEnabled(): Unit = {
    virusBuilder = virusBuilder.setPortEnabled(configuration.portEnabled)
    assertEquals(Some(configuration.portEnabled), virusBuilder.portEnabled)
  }

  @Test
  def testBuildReturnsNoneIfMandatoryFieldsAreMissing(): Unit = {
    assertEquals(None, virusBuilder.build())
  }

  @Test
  def testVirusBuilderBuild(): Unit = {
    virusBuilder = virusBuilder
      .setPortEnabled(configuration.portEnabled)
      .setAirportEnabled(configuration.airportEnabled)
      .setVaccineResistance(configuration.vaccineResistance)
      .setRichRegionsInfectivity(configuration.richRegionsInfectivity)
      .setPoorRegionsInfectivity(configuration.poorRegionsInfectivity)
      .setHighDensityRegionsInfectivity(configuration.highDensityRegionsInfectivity)
      .setLowDensityRegionsInfectivity(configuration.lowDensityRegionInfectivity)
      .setHotRegionInfectivity(configuration.hotRegionsInfectivity)
      .setColdRegionInfectivity(configuration.coldRegionsInfectivity)
    assertTrue(virusBuilder.build().isDefined)
  }

}
