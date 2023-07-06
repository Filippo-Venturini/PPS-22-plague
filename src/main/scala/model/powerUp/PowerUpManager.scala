package model.powerUp

import model.powerUp.Filters.PowerUpFilter

object Filters:
  type PowerUpFilter = PowerUp => Boolean
  given PowerUpFilter = _ => true
  val purchasablePowerUps: PowerUpFilter = powerUp => !powerUp.hasBeenBought && PowerUpManager.arePrerequisiteSatisfied(powerUp)

object PowerUpManager:
  private val powerUps: List[PowerUp] = PowerUpSettings.values.map(powerUpSettings => PowerUp(powerUpSettings)).toList
  def getPowerUps(using filter: PowerUpFilter): List[PowerUp] = powerUps.filter(filter)
  def arePrerequisiteSatisfied(powerUp: PowerUp): Boolean =
    powerUp.powerUpSetting.prerequisite.isEmpty ||
      !powerUp.powerUpSetting.prerequisite.forall(prerequisite => powerUps.find(_.powerUpSetting == prerequisite).get.hasBeenBought)
