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
    assertEquals(2, powerUp.getPrice)

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
    val startValueWarmResistance = virus.warmRegionsInfectivity
    virus.consumePowerUp(PowerUpLogics.warmResistanceI)
    val actualValueWarmResistance = virus.warmRegionsInfectivity
    assert(startValueWarmResistance < actualValueWarmResistance)

  @Test
  def testHotResistanceII: Unit =
    virus.consumePowerUp(PowerUpLogics.warmResistanceI)
    val valueWarmResistanceI = virus.warmRegionsInfectivity
    virus.consumePowerUp(PowerUpLogics.warmResistanceII)
    val actualValueWarmResistance = virus.warmRegionsInfectivity
    assert(valueWarmResistanceI < actualValueWarmResistance)
}
