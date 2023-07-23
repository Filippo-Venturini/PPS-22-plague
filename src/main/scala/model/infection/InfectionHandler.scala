package model.infection

import model.world.Region

object InfectionLogics:

  /**
   * @return the internal infection logic (default)
   */
  given InfectionLogic = new InternalInfectionLogic

  /**
   * Represent the game external infection logic 
   */
  val externalInfectionLogic: ExternalInfectionLogic = new ExternalInfectionLogic

/**
 * Class that represent the Infection Handler
 * @param virus represent the virus inside the game
 * @param regions contain all the regions inside the game
 */
class InfectionHandler(virus: Virus, regions: Iterable[Region]):

  /**
   * @param region is the region where virus start to spread
   */
  def startInfection(region: Region): Unit = region.infectedAmount = 1
  /**
   *
   * @param regions represent all the regions inside the game
   * @param logic is the logic used for increment the infection inside the game.
   *              If this parameter is not present is used the given instance InternalInfectionLogic
   */
  def computeInfection(regions: Iterable[Region])(using logic: InfectionLogic): Unit =
    regions.foreach(region => logic.compute(region, virus))
