package model.powerUp

import model.infection.{BasicVirus, VirusConfiguration}
import org.junit.{Before, Test}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}

class TestPowerUpManager {
  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 0, 0, 0, 0, 0, 0, 0, false, false)
  var powerUpManager: PowerUpManager = new PowerUpManager(new BasicVirus(testVirusConfiguration))

  @Before
  def init(): Unit =
    this.powerUpManager = new PowerUpManager(new BasicVirus(testVirusConfiguration))

  @Test
  def testGetAllPowerUps(): Unit =
    val numberOfPowerUps = PowerUpType.values.map(powerUpType => PowerUp(powerUpType)).length
    assertEquals(numberOfPowerUps, powerUpManager.getAllPowerUps().length)

  @Test
  def testGetPowerUp(): Unit =
    assertEquals(PowerUpType.HotResistanceI, powerUpManager.getPowerUp(PowerUpType.HotResistanceI).get.powerUpType)

  @Test
  def testGetPurchasablePowerUpsWithoutPrerequisites(): Unit =
    assertEquals(PowerUpType.values.filter(p => p.prerequisite.isEmpty).toList, powerUpManager.getPurchasablePowerUps().map(p => p.powerUpType))

  @Test
  def testPowerUpBuy: Unit =
    powerUpManager.purchasePowerUp(PowerUpType.ColdResistanceI)
    assertTrue(powerUpManager.getPowerUp(PowerUpType.ColdResistanceI).get.hasBeenBought)

  @Test
  def testPurchasedPowerUps: Unit =
    powerUpManager.purchasePowerUp(PowerUpType.ColdResistanceI)
    powerUpManager.purchasePowerUp(PowerUpType.AirportEnablement)
    powerUpManager.purchasePowerUp(PowerUpType.InfectionThroughAnimals)
    assertEquals(3, powerUpManager.getPurchasedPowerUps().size)


}
