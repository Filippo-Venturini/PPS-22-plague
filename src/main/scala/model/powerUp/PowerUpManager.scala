package model.powerUp

import model.infection.Virus

class PowerUpManager(private val virus: Virus):
  private val powerUps: List[PowerUp] = PowerUpType.values.map(powerUpType => PowerUp(powerUpType)).toList
  def getAllPowerUps(): List[PowerUp] = this.powerUps
  def getPurchasablePowerUps(): List[PowerUp] = this.powerUps.filter(powerUp => !powerUp.hasBeenBought && this.arePrerequisiteSatisfied(powerUp))
  def getPurchasedPowerUps(): List[PowerUp] = this.powerUps.filter(powerUp => powerUp.hasBeenBought)
  def getPowerUp(powerUpType: PowerUpType): Option[PowerUp] = this.powerUps.find(powerUp => powerUp.powerUpType == powerUpType)
  def purchasePowerUp(powerUpType: PowerUpType): Unit =
    this.getPurchasablePowerUps().find(powerUp => powerUp.powerUpType == powerUpType).get.hasBeenBought = true
    this.virus.consumePowerUp(powerUpType.logic)

  private def arePrerequisiteSatisfied(powerUp: PowerUp): Boolean =
    powerUp.powerUpType.prerequisite.isEmpty ||
      powerUp.powerUpType.prerequisite.forall(prerequisite => powerUps.find(_.powerUpType == prerequisite).get.hasBeenBought)


