package model.powerUp

case class PowerUpInformation(val effect: String, val description: String)

object PowerUpsInformation:
  val coldResistanceIInformation: PowerUpInformation = PowerUpInformation("+10 cold region infectivity", "This power up add more")
