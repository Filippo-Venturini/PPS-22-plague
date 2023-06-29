package model.configuration

import model.configuration.Builders.VirusBuilder
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
  def testColdRegionsInfectivityIsUnsetByDefault: Unit = {
    assertEquals(None, virusBuilder.coldRegionsInfectivity)
  }

  @Test
  def testSetColdRegionsInfectivity: Unit = {
    virusBuilder = virusBuilder.setColdRegionInfectivity(configuration.coldRegionsInfectivity)
    assertEquals(Some(configuration.coldRegionsInfectivity), virusBuilder.coldRegionsInfectivity)
  }

  @Test
  def testWarmRegionsInfectivityIsUnsetByDefault: Unit = {
    assertEquals(None, virusBuilder.warmRegionsInfectivity)
  }

  @Test
  def testSetWarmRegionsInfectivity: Unit = {
    virusBuilder = virusBuilder.setWarmRegionInfectivity(configuration.warmRegionsInfectivity)
    assertEquals(Some(configuration.warmRegionsInfectivity), virusBuilder.warmRegionsInfectivity)
  }

  @Test
  def testLowDensityRegionsInfectivityIsUnsetByDefault: Unit = {
    assertEquals(None, virusBuilder.lowDensityRegionsInfectivity)
  }

  @Test
  def testSetLowDensityRegionsInfectivity: Unit = {
    virusBuilder = virusBuilder.setLowDensityRegionsInfectivity(configuration.lowDensityRegionInfectivity)
    assertEquals(Some(configuration.lowDensityRegionInfectivity), virusBuilder.lowDensityRegionsInfectivity)
  }

  @Test
  def testHighDensityRegionsInfectivityIsUnsetByDefault: Unit = {
    assertEquals(None, virusBuilder.highDensityRegionsInfectivity)
  }

  @Test
  def testSetHighDensityRegionsInfectivity: Unit = {
    virusBuilder = virusBuilder.setHighDensityRegionsInfectivity(configuration.highDensityRegionsInfectivity)
    assertEquals(Some(configuration.highDensityRegionsInfectivity), virusBuilder.highDensityRegionsInfectivity)
  }

  @Test
  def testRichRegionsInfectivityIsUnsetByDefault: Unit = {
    assertEquals(None, virusBuilder.richRegionsInfectivity)
  }

  @Test
  def testSetRichRegionsInfectivity: Unit = {
    virusBuilder = virusBuilder.setRichRegionsInfectivity(configuration.richRegionsInfectivity)
    assertEquals(Some(configuration.richRegionsInfectivity), virusBuilder.richRegionsInfectivity)
  }

  @Test
  def testPoorRegionsInfectivityIsUnsetByDefault: Unit = {
    assertEquals(None, virusBuilder.poorRegionsInfectivity)
  }

  @Test
  def testSetPoorRegionsInfectivity: Unit = {
    virusBuilder = virusBuilder.setPoorRegionsInfectivity(configuration.poorRegionsInfectivity)
    assertEquals(Some(configuration.poorRegionsInfectivity), virusBuilder.poorRegionsInfectivity)
  }

}
