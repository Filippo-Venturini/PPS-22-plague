package model.powerUp

import org.junit.{Before, Test}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import model.powerUp.Filters.{purchasablePowerUps, purchasedPowerUps, given}

class TestPowerUpManager {
  @Test
  def testGetAllPowerUps(): Unit =
    val numberOfPowerUps = PowerUpType.values.map(powerUpType => PowerUp(powerUpType)).length
    assertEquals(numberOfPowerUps, PowerUpManager.getPowerUps.length)

  @Test
  def testGetPowerUp(): Unit =
    assertEquals(PowerUpType.HotResistanceI, PowerUpManager.getPowerUp(PowerUpType.HotResistanceI).get.powerUpType)

  @Test
  def testGetPurchasablePowerUpsWithoutPrerequisites(): Unit =
    assertEquals(PowerUpType.values.filter(p => p.prerequisite.isEmpty).toList, PowerUpManager.getPowerUps(using purchasablePowerUps).map(p => p.powerUpType))

  @Test
  def testPowerUpBuy: Unit =
    PowerUpManager.buyPowerUp(PowerUpType.ColdResistanceI)
    assertTrue(PowerUpManager.getPowerUp(PowerUpType.ColdResistanceI).get.hasBeenBought)


  @Test
  def testPurchasedPowerUps: Unit =
    assertEquals(1, PowerUpManager.getPowerUps(using purchasedPowerUps).size)


}
