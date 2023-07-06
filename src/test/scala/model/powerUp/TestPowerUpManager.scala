package model.powerUp

import org.junit.{Before, Test}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import model.powerUp.Filters.{purchasablePowerUps, purchasedPowerUps, given}

class TestPowerUpManager {
  var numberOfPurchasedPowerUps: Int = 0

  @Test
  def testGetAllPowerUps(): Unit =
    val numberOfPowerUps = PowerUpType.values.map(powerUpType => PowerUp(powerUpType)).length
    assertEquals(numberOfPowerUps, PowerUpManager.getPowerUps.length)

  @Test
  def testGetPurchasablePowerUpsWithoutPrerequisites(): Unit =
    assertEquals(PowerUpType.values.filter(p => p.prerequisite.isEmpty).toList, PowerUpManager.getPowerUps(using purchasablePowerUps).map(p => p.powerUpType))

  @Test
  def testPowerUpBuy: Unit =
    PowerUpManager.buyPowerUp(PowerUpType.ColdResistanceI)
    numberOfPurchasedPowerUps = numberOfPurchasedPowerUps + 1
    assertTrue(PowerUpManager.getPowerUps.find(p => p.powerUpType == PowerUpType.ColdResistanceI).get.hasBeenBought)

  @Test
  def testPurchasedPowerUps: Unit =
    assertEquals(numberOfPurchasedPowerUps, PowerUpManager.getPowerUps(using purchasedPowerUps).size)

}
