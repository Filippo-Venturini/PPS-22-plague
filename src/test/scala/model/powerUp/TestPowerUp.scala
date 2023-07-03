package model.powerUp
import org.junit.{Before, Test}
import model.powerUp.PowerUp
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}

class TestPowerUp {
  import PowerUpType.*

  @Test
  def testGetPricePowerUp: Unit =
    val powerUp: PowerUp = PowerUp(PowerUpType.ColdResistanceI)
    assertEquals(2, powerUp.getPrice)

  @Test
  def testHas: Unit =
    val powerUp: PowerUp = PowerUp(PowerUpType.ColdResistanceII)
    assertFalse(powerUp.hasBeenBought)
    powerUp.hasBeenBought = true
    assertTrue(powerUp.hasBeenBought)
}
