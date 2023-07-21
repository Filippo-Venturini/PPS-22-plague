package model.powerUp

case class PowerUpInformation(val effect: String, val description: String)

object PowerUpsInformation:
  val coldResistanceIInformation: PowerUpInformation = PowerUpInformation("+10 cold regions infectivity", "Increase the effectiveness of the virus in the cold regions")
  val coldResistanceIIInformation: PowerUpInformation = PowerUpInformation("+20 cold regions infectivity", "Increase the effectiveness of the virus in the cold regions")
  val hotResistanceIInformation: PowerUpInformation = PowerUpInformation("+10 hot region infectivity", "Increase the effectiveness of the virus in the hot regions")
  val hotResistanceIIInformation: PowerUpInformation = PowerUpInformation("+20 hot region infectivity", "Increase the effectiveness of the virus in the hot regions")
  val bacterialResistanceInformation: PowerUpInformation = PowerUpInformation("+10 hot and cold regions infectivity", "Increase the effectiveness of the virus in both hot and cold regions")
  val airportEnablementInformation: PowerUpInformation = PowerUpInformation("Enable the airport", "Enable the virus to spread through airports")
  val portEnablementInformation: PowerUpInformation = PowerUpInformation("Enable the port", "Enable the virus to spread through ports")
  val infectedDrinkingWaterInformation: PowerUpInformation = PowerUpInformation("+20 poor regions infectivity", "The virus infects the drinking water, simplifying the infection in the poor regions")
  val infectionThroughAnimalsInformation: PowerUpInformation = PowerUpInformation("+20 low density regions infectivity", "The virus makes a spillover. Low density's regions are the most affected")
  val medicinesResistanceInformation: PowerUpInformation = PowerUpInformation("+20 rich regions infectivity", "Common medicines are not effective against virus. Rich regions are the most affected")
  val infectionThroughRespiratoryTractInformation: PowerUpInformation = PowerUpInformation("+20 high density regions infectivity", "The virus can infect people through the Respiratory System, facilitating the spread of the virus in big metropolises")
  val alphaMutationsInformation: PowerUpInformation = PowerUpInformation("+10 vaccine resistance", "The virus mutates its behaviour, making difficult the development of a vaccine")
  val betaMutationsInformation: PowerUpInformation = PowerUpInformation("+10 vaccine resistance", "The virus mutates its behaviour, making difficult the development of a vaccine")
  val gammaMutationsInformation: PowerUpInformation = PowerUpInformation("+10 vaccine resistance", "The virus mutates its behaviour, making difficult the development of a vaccine")
  val omegaMutationsInformation: PowerUpInformation = PowerUpInformation("+10 vaccine resistance", "The virus mutates its behaviour, making difficult the development of a vaccine")

