package model.powerUp

import model.infection.{BasicVirus, ColdRegionsInfectivity, Virus, VirusConfiguration}
import org.junit.{Before, Test}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}

class TestPowerUpManager {
  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 0, 0, 0, 0, 0, 0, 0, false, false)
  val virus: Virus = new BasicVirus(testVirusConfiguration)
  var powerUpManager: PowerUpManager = new PowerUpManager(this.virus)

  @Before
  def init(): Unit =
    this.powerUpManager = new PowerUpManager(this.virus)

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
  def testPurchaseAvailablePowerUp: Unit =
    powerUpManager.purchasePowerUp(PowerUpType.ColdResistanceI)
    assertTrue(powerUpManager.getPowerUp(PowerUpType.ColdResistanceI).get.hasBeenBought)

  @Test
  def testGetPurchasedPowerUps: Unit =
    powerUpManager.purchasePowerUp(PowerUpType.ColdResistanceI)
    powerUpManager.purchasePowerUp(PowerUpType.AirportEnablement)
    powerUpManager.purchasePowerUp(PowerUpType.PortEnablement)
    assertEquals(3, powerUpManager.getPurchasedPowerUps().size)

  @Test
  def testGetPurchasablePowerUpsWithPrerequisites(): Unit =
    assertFalse(powerUpManager.getPurchasablePowerUps().map(p => p.powerUpType).contains(PowerUpType.HotResistanceII))
    powerUpManager.purchasePowerUp(PowerUpType.HotResistanceI)
    assertTrue(powerUpManager.getPurchasablePowerUps().map(p => p.powerUpType).contains(PowerUpType.HotResistanceII))

  @Test
  def testApplyPowerUpsToVirusOnPurchase(): Unit =
    val initialColdRegionInfectivity: ColdRegionsInfectivity = this.virus.coldRegionsInfectivity
    powerUpManager.purchasePowerUp(PowerUpType.ColdResistanceI)
    assertEquals(initialColdRegionInfectivity + 5, this.virus.coldRegionsInfectivity)

}
