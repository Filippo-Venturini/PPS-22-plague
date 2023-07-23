package model.powerUp
import org.junit.{Before, Test}
import model.powerUp.PowerUp
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import model.infection
import model.infection.*

class TestPowerUp {
  import PowerUpType.*


  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 0, 0, 0, 0, 0, 0, 0, false, false)
  val virus: Virus = new BasicVirus(testVirusConfiguration)
  @Test
  def testGetPricePowerUp: Unit =
    val powerUp: PowerUp = PowerUp(PowerUpType.ColdResistanceI)
    println(powerUp.powerUpType)
    assertEquals(5, powerUp.powerUpType.price)

  @Test
  def testHasBought: Unit =
    val powerUp: PowerUp = PowerUp(PowerUpType.ColdResistanceII)
    assertFalse(powerUp.hasBeenBought)
    powerUp.hasBeenBought = true
    assertTrue(powerUp.hasBeenBought)

  @Test
  def testColdResistanceI: Unit =
    val startValueColdResistance = virus.coldRegionsInfectivity
    virus.consumePowerUp(PowerUpLogics.coldResistanceI)
    val actualValueColdResistance = virus.coldRegionsInfectivity
    assert(startValueColdResistance < actualValueColdResistance)

  @Test
  def testColdResistanceII: Unit =
    virus.consumePowerUp(PowerUpLogics.coldResistanceI)
    val valueColdResistanceI = virus.coldRegionsInfectivity
    virus.consumePowerUp(PowerUpLogics.coldResistanceII)
    val actualValueColdResistance = virus.coldRegionsInfectivity
    assert(valueColdResistanceI < actualValueColdResistance)

  @Test
  def testHotResistanceI: Unit =
    val startValueHotResistance = virus.hotRegionsInfectivity
    virus.consumePowerUp(PowerUpLogics.hotResistanceI)
    val actualValueHotResistance = virus.hotRegionsInfectivity
    assert(startValueHotResistance < actualValueHotResistance)

  @Test
  def testHotResistanceII: Unit =
    virus.consumePowerUp(PowerUpLogics.hotResistanceI)
    val valueHotResistanceI = virus.hotRegionsInfectivity
    virus.consumePowerUp(PowerUpLogics.hotResistanceII)
    val actualValueHotResistance = virus.hotRegionsInfectivity
    assert(valueHotResistanceI < actualValueHotResistance)

  @Test
  def testEnabledAirport: Unit =
    assertFalse(virus.airportEnabled)
    virus.consumePowerUp(PowerUpLogics.airportEnablement)
    assertTrue(virus.airportEnabled)

  @Test
  def testEnabledPort: Unit =
    assertFalse(virus.portEnabled)
    virus.consumePowerUp(PowerUpLogics.portEnablement)
    assertTrue(virus.portEnabled)

  @Test
  def testPresenceOfPrerequisites: Unit =
    assertTrue(PowerUpType.ColdResistanceI.prerequisite.isEmpty)
    assertTrue(PowerUpType.ColdResistanceII.prerequisite.nonEmpty)
    assertTrue(PowerUpType.HotResistanceI.prerequisite.isEmpty)
    assertTrue(PowerUpType.HotResistanceII.prerequisite.nonEmpty)
    assertTrue(PowerUpType.BacterialResistance.prerequisite.nonEmpty)
    assertTrue(PowerUpType.AirportEnablement.prerequisite.isEmpty)
    assertTrue(PowerUpType.PortEnablement.prerequisite.isEmpty)
    assertTrue(PowerUpType.InfectionThroughAnimals.prerequisite.nonEmpty)
    assertTrue(PowerUpType.InfectionThroughRespiratoryTract.prerequisite.nonEmpty)
    assertTrue(PowerUpType.MedicinesResistance.prerequisite.isEmpty)
    assertTrue(PowerUpType.InfectedDrinkingWater.prerequisite.isEmpty)
    assertTrue(PowerUpType.AlphaMutations.prerequisite.isEmpty)
    assertTrue(PowerUpType.BetaMutations.prerequisite.nonEmpty)
    assertTrue(PowerUpType.GammaMutations.prerequisite.nonEmpty)
    assertTrue(PowerUpType.OmegaMutations.prerequisite.nonEmpty)

}
