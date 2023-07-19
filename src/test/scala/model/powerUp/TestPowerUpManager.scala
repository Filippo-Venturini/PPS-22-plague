package model.powerUp

import model.dnapoints.DnaPoints.DnaPointsHandler
import model.infection.{BasicVirus, ColdRegionsInfectivity, Virus, VirusConfiguration}
import model.world.World
import org.junit.{Before, Test}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import model.dnapoints.DnaPoints.Logic.EmptyLogic

class TestPowerUpManager {
  val world = new World(List())
  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 0, 0, 0, 0, 0, 0, 0, false, false)
  val virus: Virus = new BasicVirus(testVirusConfiguration)
  var dnaPointsHandler: DnaPointsHandler = DnaPointsHandler(EmptyLogic())
  var powerUpManager: PowerUpManager = new PowerUpManager(this.virus, dnaPointsHandler)

  @Before
  def init(): Unit =
    dnaPointsHandler = DnaPointsHandler(EmptyLogic())
    dnaPointsHandler.collectedPoints = 100
    this.powerUpManager = new PowerUpManager(this.virus, dnaPointsHandler)

  @Test
  def testGetAllPowerUps(): Unit =
    val numberOfPowerUps = PowerUpType.values.map(powerUpType => PowerUp(powerUpType)).length
    assertEquals(numberOfPowerUps, powerUpManager.getAllPowerUps.length)

  @Test
  def testGetPowerUp(): Unit =
    assertEquals(PowerUpType.HotResistanceI, powerUpManager.getPowerUp(PowerUpType.HotResistanceI).get.powerUpType)

  @Test
  def testGetPowerUpWithPrerequisiteSatisfied(): Unit =
    assertFalse(powerUpManager.getPrerequisiteSatisfiedPowerUps.map(p => p.powerUpType).contains(PowerUpType.BetaMutations))
    powerUpManager.purchasePowerUp(PowerUpType.AlphaMutations)
    assertTrue(powerUpManager.getPurchasablePowerUps.map(p => p.powerUpType).contains(PowerUpType.BetaMutations))

  @Test
  def testGetPurchasablePowerUpsWithoutPrerequisites(): Unit =
    assertEquals(PowerUpType.values.filter(p => p.prerequisite.isEmpty).toList, powerUpManager.getPurchasablePowerUps.map(p => p.powerUpType))

  @Test
  def testPurchaseAvailablePowerUp(): Unit =
    powerUpManager.purchasePowerUp(PowerUpType.ColdResistanceI)
    assertTrue(powerUpManager.getPowerUp(PowerUpType.ColdResistanceI).get.hasBeenBought)

  @Test
  def testGetPurchasedPowerUps(): Unit =
    powerUpManager.purchasePowerUp(PowerUpType.ColdResistanceI)
    powerUpManager.purchasePowerUp(PowerUpType.AirportEnablement)
    powerUpManager.purchasePowerUp(PowerUpType.PortEnablement)
    assertEquals(3, powerUpManager.getPurchasedPowerUps.size)

  @Test
  def testGetPurchasablePowerUpsWithPrerequisites(): Unit =
    assertFalse(powerUpManager.getPurchasablePowerUps.map(p => p.powerUpType).contains(PowerUpType.HotResistanceII))
    powerUpManager.purchasePowerUp(PowerUpType.HotResistanceI)
    assertTrue(powerUpManager.getPurchasablePowerUps.map(p => p.powerUpType).contains(PowerUpType.HotResistanceII))

  @Test
  def testApplyPowerUpsToVirusOnPurchase(): Unit =
    val initialColdRegionInfectivity: ColdRegionsInfectivity = this.virus.coldRegionsInfectivity
    powerUpManager.purchasePowerUp(PowerUpType.ColdResistanceI)
    assertEquals(initialColdRegionInfectivity + 5, this.virus.coldRegionsInfectivity)

  @Test
  def cantBuyPowerUpWithNotEnoughMoney(): Unit =
    dnaPointsHandler.collectedPoints = 0
    assertTrue(powerUpManager.getPurchasablePowerUps.isEmpty)

  @Test
  def canBuyPowerUpWithEnoughMoney(): Unit =
    dnaPointsHandler.collectedPoints = 100
    assertFalse(powerUpManager.getPurchasablePowerUps.isEmpty)

}
