package model.powerUp

import model.powerUp.Filters.PowerUpFilter

object Filters:
  type PowerUpFilter = PowerUp => Boolean

  given PowerUpFilter = _ => true

class PowerUpManager
  //private val powerUps: List[PowerUp] = PowerUpType.values.map(powerUpType => PowerUp(powerUpType)).toList
  //def getPowerUps(using filter: PowerUpFilter): List[PowerUp] = powerUps.filter(filter)
