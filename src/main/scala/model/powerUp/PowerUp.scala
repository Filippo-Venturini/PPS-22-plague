package model.powerUp


trait PowerUp:
  def powerUpSetting: PowerUpSettings
  var hasBeenBought: Boolean = false

object PowerUp:
  def apply(powerUpSetting: PowerUpSettings): PowerUp =
    PowerUpImpl(powerUpSetting)

  private class PowerUpImpl(override val powerUpSetting: PowerUpSettings) extends PowerUp
