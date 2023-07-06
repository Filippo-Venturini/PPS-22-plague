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
  val coldResistanceI: PowerUpLogic = new PowerUpLogic:
    /**
     * Modify the characteristics of the virus according to the power up coldResistanceI
     * @param virus is the virus inside the game
     */
    override def applyTo(virus: Virus): Unit =
      virus.coldRegionsInfectivity = virus.coldRegionsInfectivity + 5

  val coldResistanceII: PowerUpLogic = new PowerUpLogic:
    /**
     * Modify the characteristics of the virus according to the power up coldResistanceII
     *
     * @param virus is the virus inside the game
     */
    override def applyTo(virus: Virus): Unit =
      virus.coldRegionsInfectivity = virus.coldRegionsInfectivity + 3

  val warmResistanceI: PowerUpLogic = new PowerUpLogic:
    /**
     * Modify the characteristics of the virus according to the power up warmResistanceI
     *
     * @param virus is the virus inside the game
     */
    override def applyTo(virus: Virus): Unit =
      virus.warmRegionsInfectivity = virus.warmRegionsInfectivity + 5

  val warmResistanceII: PowerUpLogic = new PowerUpLogic:
    /**
     * Modify the characteristics of the virus according to the power up warmResistanceII
     *
     * @param virus is the virus inside the game
     */
    override def applyTo(virus: Virus): Unit =
      virus.warmRegionsInfectivity = virus.warmRegionsInfectivity + 3

  val bacterialResistance: PowerUpLogic = new PowerUpLogic:
    /**
     * Modify the characteristics of the virus according to the power up bacterialResistance
     *
     * @param virus is the virus inside the game
     */
    override def applyTo(virus: Virus): Unit =
      virus.richRegionsInfectivity = virus.richRegionsInfectivity + 3
      virus.highDensityRegionsInfectivity = virus.highDensityRegionsInfectivity + 1

  val airportEnablement: PowerUpLogic = new PowerUpLogic:
    /**
     * Modify the characteristics of the virus according to the power up airportEnablement
     *
     * @param virus is the virus inside the game
     */
    override def applyTo(virus: Virus): Unit =
      virus.airportEnabled = true

  val portEnablement: PowerUpLogic = new PowerUpLogic:
    /**
     * Modify the characteristics of the virus according to the power up portEnablement
     *
     * @param virus is the virus inside the game
     */
    override def applyTo(virus: Virus): Unit =
      virus.portEnabled = true

  val infectionThroughAnimals: PowerUpLogic = new PowerUpLogic:
    /**
     * Modify the characteristics of the virus according to the power up infectionThroughAnimals
     *
     * @param virus is the virus inside the game
     */
    override def applyTo(virus: Virus): Unit =
      virus.warmRegionsInfectivity = virus.warmRegionsInfectivity + 1
      virus.poorRegionsInfectivity = virus.poorRegionsInfectivity + 3
      virus.highDensityRegionsInfectivity = virus.highDensityRegionsInfectivity + 1

  val infectionThroughRespiratoryTract: PowerUpLogic = new PowerUpLogic:
    /**
     * Modify the characteristics of the virus according to the power up infectionThroughRespiratoryTract
     *
     * @param virus is the virus inside the game
     */
    override def applyTo(virus: Virus): Unit =
      virus.poorRegionsInfectivity = virus.poorRegionsInfectivity + 3
      virus.richRegionsInfectivity = virus.richRegionsInfectivity + 2

  val medicinesResistance: PowerUpLogic = new PowerUpLogic:
    /**
     * Modify the characteristics of the virus according to the power up medicinesResistance
     *
     * @param virus is the virus inside the game
     */
    override def applyTo(virus: Virus): Unit =
      virus.highDensityRegionsInfectivity = virus.highDensityRegionsInfectivity + 1
      virus.richRegionsInfectivity = virus.richRegionsInfectivity + 4
      virus.vaccineResistance = virus.vaccineResistance + 2

  val infectedDrinkingWater: PowerUpLogic = new PowerUpLogic:
    /**
     * Modify the characteristics of the virus according to the power up infectedDrinkingWater
     *
     * @param virus is the virus inside the game
     */
    override def applyTo(virus: Virus): Unit =
      virus.richRegionsInfectivity = virus.poorRegionsInfectivity + 2
      virus.poorRegionsInfectivity = virus.poorRegionsInfectivity + 1

  val spontaneousMutations: PowerUpLogic = new PowerUpLogic:
    /**
     * Modify the characteristics of the virus according to the power up spontaneousMutations
     *
     * @param virus is the virus inside the game
     */
    override def applyTo(virus: Virus): Unit =
      virus.vaccineResistance = virus.vaccineResistance + 3
      virus.poorRegionsInfectivity = virus.poorRegionsInfectivity + 1
      virus.richRegionsInfectivity = virus.poorRegionsInfectivity + 1
