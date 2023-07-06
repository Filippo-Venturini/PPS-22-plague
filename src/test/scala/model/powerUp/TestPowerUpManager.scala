package model.powerUp

import org.junit.Test
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import model.powerUp.Filters.{purchasablePowerUps, given}

class TestPowerUpManager {

  @Test
  def testGetAllPowerUps(): Unit =
    val numberOfPowerUps = PowerUpSettings.values.map(powerUpType => PowerUp(powerUpType)).length
    assertEquals(numberOfPowerUps, PowerUpManager.getPowerUps.length)

  @Test
  def testGetPurchasablePowerUpsWithoutPrerequisites(): Unit =
    assertEquals(PowerUpSettings.values.filter(p => p.prerequisite.isEmpty).toList, PowerUpManager.getPowerUps(using purchasablePowerUps).map(p => p.powerUpSetting))

}
