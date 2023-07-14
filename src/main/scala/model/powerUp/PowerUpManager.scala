package model.powerUp

import model.infection.Virus
import model.dnapoints.DnaPoints.DnaPointsHandler

/**
 * Class that represent a manager for handling the PowerUps.
 * It offer the possibility to get the available PowerUps or purchase some of them basing on the hierarchy.
 *
 * @param virus the virus that is infecting the world
 */
class PowerUpManager(private val virus: Virus, private val dnaPointsHandler: DnaPointsHandler):
  private val powerUps: List[PowerUp] = PowerUpType.values.map(powerUpType => PowerUp(powerUpType)).toList

  /**
   * @return all the PowerUps of the game
   */
  def getAllPowerUps(): List[PowerUp] = this.powerUps

  /**
   * @return only the purchasable PowerUps basing on the hierarchy and the DNAPoint collected.
   */
  def getPurchasablePowerUps(): List[PowerUp] =
    this.powerUps
      .filter(!_.hasBeenBought)
      .filter(this.arePrerequisiteSatisfied)
      .filter(_.powerUpType.price <= dnaPointsHandler.collectedPoints)

  /**
   * @return only the PowerUps with prerequisite satisfied basing on the hierarchy.
   */
  def getPrerequisiteSatisfiedPowerUps(): List[PowerUp] =
    this.powerUps
      .filter(!_.hasBeenBought)
      .filter(this.arePrerequisiteSatisfied)

  /**
   * @return the list of the all the purchased PowerUps.
   */
  def getPurchasedPowerUps(): List[PowerUp] = this.powerUps.filter(powerUp => powerUp.hasBeenBought)

  /**
   * @param powerUpType the type of the PowerUp requested.
   * @return the corresponding PowerUp based on the type specified as input.
   */
  def getPowerUp(powerUpType: PowerUpType): Option[PowerUp] = this.powerUps.find(powerUp => powerUp.powerUpType == powerUpType)

  /**
   * It purchase the PowerUp specified and make the virus automatically consume it.
   * @param powerUpType the type of the PowerUp that is requested to buy.
   */
  def purchasePowerUp(powerUpType: PowerUpType): Unit =
    this.getPurchasablePowerUps().find(powerUp => powerUp.powerUpType == powerUpType).get.hasBeenBought = true
    this.dnaPointsHandler.collectedPoints = this.dnaPointsHandler.collectedPoints - powerUpType.price
    this.virus.consumePowerUp(powerUpType.logic)

  /**
   * @param powerUp the PowerUp that must be checked.
   * @return true if all the prerequisite of the PowerUp are satisfied
   */
  private def arePrerequisiteSatisfied(powerUp: PowerUp): Boolean =
    powerUp.powerUpType.prerequisite.isEmpty ||
      powerUp.powerUpType.prerequisite.forall(prerequisite => powerUps.find(_.powerUpType == prerequisite).get.hasBeenBought)