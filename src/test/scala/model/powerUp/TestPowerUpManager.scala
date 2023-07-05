package model.powerUp

import org.junit.Test
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import model.powerUp.Filters.given

class TestPowerUpManager {
  val powerUpManager: PowerUpManager = new PowerUpManager

  @Test
  def testGetAllPowerUps(): Unit =
    val numberOfPowerUps = PowerUpType.values.map(powerUpType => PowerUp(powerUpType)).length
    assertEquals(numberOfPowerUps, powerUpManager.getPowerUps.length)

}
