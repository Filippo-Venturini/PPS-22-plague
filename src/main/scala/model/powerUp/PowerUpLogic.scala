package model.powerUp
import model.infection.Virus

/**
 * Contain a specific Power Up Logic for each type
 */
trait PowerUpLogic:
  /**
   * It takes a virus and applies the Power Up to change its characteristics
   * @param virus is the virus inside the game
   */
  def applyTo(virus: Virus): Unit

object PowerUpLogics:
  /**
   * Modify the characteristics of the virus according to the power up coldResistanceI.
   * Increment the infectivity in the cold regions
   */
  val coldResistanceI: PowerUpLogic = (virus: Virus) => virus.coldRegionsInfectivity = virus.coldRegionsInfectivity + 20

  /**
   * Modify the characteristics of the virus according to the power up coldResistanceII.
   * Increment the infectivity in the cold regions
   */
  val coldResistanceII: PowerUpLogic = (virus: Virus) => virus.coldRegionsInfectivity = virus.coldRegionsInfectivity + 20

  /**
   * Modify the characteristics of the virus according to the power up hotResistanceI.
   * Increment the infectivity in the hot regions
   */
  val hotResistanceI: PowerUpLogic = (virus: Virus) => virus.hotRegionsInfectivity = virus.hotRegionsInfectivity + 10

  /**
   * Modify the characteristics of the virus according to the power up hotResistanceII.
   * Increment the infectivity in the hot regions
   */
  val hotResistanceII: PowerUpLogic = (virus: Virus) => virus.hotRegionsInfectivity = virus.hotRegionsInfectivity + 20

  /**
   * Modify the characteristics of the virus according to the power up bacterialResistance.
   * Increment the infectivity in the hot and cold regions
   */
  val bacterialResistance: PowerUpLogic = (virus: Virus) =>
    virus.hotRegionsInfectivity = virus.hotRegionsInfectivity + 10
      virus.coldRegionsInfectivity = virus.coldRegionsInfectivity + 10

  /**
   * Modify the characteristics of the virus, enable the airport.
   */
  val airportEnablement: PowerUpLogic = (virus: Virus) => virus.airportEnabled = true

  /**
   * Modify the characteristics of the virus, enable the port
   */
  val portEnablement: PowerUpLogic = (virus: Virus) => virus.portEnabled = true

  /**
   * Modify the characteristics of the virus according to the power up infectionThroughAnimals.
   * Increment the infectivity in the population low density regions
   */
  val infectionThroughAnimals: PowerUpLogic = (virus: Virus) => virus.lowDensityRegionInfectivity = virus.lowDensityRegionInfectivity + 20

  /**
   * Modify the characteristics of the virus according to the power up infectionThroughRespiratoryTract.
   * Increment the infectivity in the population high density regions
   */
  val infectionThroughRespiratoryTract: PowerUpLogic = (virus: Virus) => virus.highDensityRegionsInfectivity = virus.highDensityRegionsInfectivity + 20

  /**
   * Modify the characteristics of the virus according to the power up medicinesResistance.
   * Increment the infectivity in the rich regions
   */
  val medicinesResistance: PowerUpLogic = (virus: Virus) => virus.richRegionsInfectivity = virus.richRegionsInfectivity + 20

  /**
   * Modify the characteristics of the virus according to the power up infectedDrinkingWater.
   * Increment the infectivity in the poor regions
   */
  val infectedDrinkingWater: PowerUpLogic = (virus: Virus) => virus.poorRegionsInfectivity = virus.poorRegionsInfectivity + 20

  /**
   * Modify the characteristics of the virus according to the power up alphaMutations.
   * Add more vaccine resistance
   */
  val alphaMutations: PowerUpLogic = (virus: Virus) => virus.vaccineResistance = virus.vaccineResistance + 10

  /**
   * Modify the characteristics of the virus according to the power up betaMutations.
   * Add more vaccine resistance
   */
  val betaMutations: PowerUpLogic = (virus: Virus) => virus.vaccineResistance = virus.vaccineResistance + 10


  /**
   * Modify the characteristics of the virus according to the power up gammaMutations.
   * Add more vaccine resistance
   */
  val gammaMutations: PowerUpLogic = (virus: Virus) => virus.vaccineResistance = virus.vaccineResistance + 10

  /**
   * Modify the characteristics of the virus according to the power up omegaMutations.
   * Add more vaccine resistance
   */
  val omegaMutations: PowerUpLogic = (virus: Virus) => virus.vaccineResistance = virus.vaccineResistance + 10