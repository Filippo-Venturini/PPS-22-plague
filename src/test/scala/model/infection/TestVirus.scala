package model.infection

import model.infection.{InfectionLogic, InternalInfectionLogic}
import model.powerUp.PowerUpLogics
import model.world.{BasicRegion, Region}
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class TestVirus {
  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 0, 0, 0, 0, 0, 0, 0, false, false)
  val virus: Virus = new BasicVirus(testVirusConfiguration)

  //TODO aggiungere test per modificare i parametri del virus
  @Test
  def testVirusName: Unit =
    assertEquals(testVirusConfiguration.name, virus.name)

  @Test
  def testVirusColdRegionsInfectivity: Unit =
    assertEquals(testVirusConfiguration.coldRegionsInfectivity, virus.coldRegionsInfectivity)


  @Test
  def testVirusHotRegionsInfectivity: Unit =
    assertEquals(testVirusConfiguration.hotRegionsInfectivity, virus.hotRegionsInfectivity)

  @Test
  def testVirusLowDensityRegionInfectivity: Unit =
    assertEquals(testVirusConfiguration.lowDensityRegionInfectivity, virus.lowDensityRegionInfectivity)

  @Test
  def testVirusHighDensityRegionsInfectivity: Unit =
    assertEquals(testVirusConfiguration.highDensityRegionsInfectivity, virus.highDensityRegionsInfectivity)

  @Test
  def testVirusRichRegionsInfectivity: Unit =
    assertEquals(testVirusConfiguration.richRegionsInfectivity, virus.richRegionsInfectivity)

  @Test
  def testVirusPoorRegionsInfectivity: Unit =
    assertEquals(testVirusConfiguration.poorRegionsInfectivity, virus.poorRegionsInfectivity)

  @Test
  def testVirusVaccineResistance: Unit =
    assertEquals(testVirusConfiguration.vaccineResistance, virus.vaccineResistance)

  @Test
  def testVirusAirportEnabled: Unit =
    assertEquals(testVirusConfiguration.airportEnabled, virus.airportEnabled)

  @Test
  def testVirusPortEnabled: Unit =
    assertEquals(testVirusConfiguration.portEnabled, virus.portEnabled)

  @Test
  def testGetVirusConfiguration: Unit =
    assertEquals(testVirusConfiguration.name, virus.getActualConfiguration.name)
    assertEquals(testVirusConfiguration.coldRegionsInfectivity, virus.getActualConfiguration.coldRegionsInfectivity)
    assertEquals(testVirusConfiguration.hotRegionsInfectivity, virus.getActualConfiguration.hotRegionsInfectivity)
    assertEquals(testVirusConfiguration.lowDensityRegionInfectivity, virus.getActualConfiguration.lowDensityRegionInfectivity)
    assertEquals(testVirusConfiguration.highDensityRegionsInfectivity, virus.getActualConfiguration.highDensityRegionsInfectivity)
    assertEquals(testVirusConfiguration.richRegionsInfectivity, virus.getActualConfiguration.richRegionsInfectivity)
    assertEquals(testVirusConfiguration.poorRegionsInfectivity, virus.getActualConfiguration.poorRegionsInfectivity)
    assertEquals(testVirusConfiguration.vaccineResistance, virus.getActualConfiguration.vaccineResistance)
    assertEquals(testVirusConfiguration.airportEnabled, virus.getActualConfiguration.airportEnabled)
    assertEquals(testVirusConfiguration.portEnabled, virus.getActualConfiguration.portEnabled)

  
}