package model.powerUp

case class PowerUpInformation(val effect: String, val description: String)

object PowerUpsInformation:
  val coldResistanceIInformation: PowerUpInformation = PowerUpInformation("+10 cold region infectivity", "This power up add more")
  val coldResistanceIIInformation: PowerUpInformation = PowerUpInformation("+10 cold region infectivity", "This power up add more")
  val hotResistanceIInformation: PowerUpInformation = PowerUpInformation("+10 hot region infectivity", "This power up add more")
  val hotResistanceIIInformation: PowerUpInformation = PowerUpInformation("+10 hot region infectivity", "This power up add more")
  val bacterialResistanceInformation: PowerUpInformation = PowerUpInformation("+10 cold and hot region infectivity", "This power up add more")
  val airportEnablementInformation: PowerUpInformation = PowerUpInformation("Enable the airport", "Virus can spread through the airport")
  val portEnablementInformation: PowerUpInformation = PowerUpInformation("Enable the port", "Virus can spread through the port")
  val infectedDrinkingWaterInformation: PowerUpInformation = PowerUpInformation("+10 poor region infectivity", "This power up add more")
  val infectionThroughAnimalsInformation: PowerUpInformation = PowerUpInformation("+10 low density region infectivity", "This power up add more")
  val medicinesResistanceInformation: PowerUpInformation = PowerUpInformation("+10 rich region infectivity", "This power up add more")
  val infectionThroughRespiratoryTractInformation: PowerUpInformation = PowerUpInformation("+10 high density region infectivity", "This power up add more")
  val alphaMutationsInformation: PowerUpInformation = PowerUpInformation("+10 vaccine resistance", "This power up add more")
  val betaMutationsInformation: PowerUpInformation = PowerUpInformation("+10 vaccine resistance", "This power up add more")
  val gammaMutationsInformation: PowerUpInformation = PowerUpInformation("+10 vaccine resistance", "This power up add more")
  val omegaMutationsInformation: PowerUpInformation = PowerUpInformation("+10 vaccine resistance", "This power up add more")



