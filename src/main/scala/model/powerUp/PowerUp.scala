package model.powerUp


trait PowerUp:
  def powerUpType: PowerUpType
  var hasBeenBought: Boolean = false

object PowerUp:
  def apply(powerUpType: PowerUpType): PowerUp =
    PowerUpImpl(powerUpType)

  private class PowerUpImpl(override val powerUpType: PowerUpType) extends PowerUp
