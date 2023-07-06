package model.powerUp

import model.powerUp.Filters.PowerUpFilter

object Filters:
  type PowerUpFilter = PowerUp => Boolean
  given PowerUpFilter = _ => true
  val purchasablePowerUps: PowerUpFilter = powerUp => !powerUp.hasBeenBought && PowerUpManager.arePrerequisiteSatisfied(powerUp)

object PowerUpManager:
  private val powerUps: List[PowerUp] = PowerUpType.values.map(powerUpType => PowerUp(powerUpType)).toList
  def getPowerUps(using filter: PowerUpFilter): List[PowerUp] = powerUps.filter(filter)
  def arePrerequisiteSatisfied(powerUp: PowerUp): Boolean =
    powerUp.powerUpType.prerequisite.isEmpty ||
      powerUp.powerUpType.prerequisite.forall(prerequisite => powerUps.find(_.powerUpType == prerequisite).get.hasBeenBought)
  def buyPowerUp(powerUpType: PowerUpType): Unit =
    this.powerUps.find(powerUp => powerUp.powerUpType == powerUpType).get.hasBeenBought = true
