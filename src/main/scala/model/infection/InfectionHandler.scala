package model.infection

import model.world.Region

object InfectionLogics:
  given InfectionLogic = new InternalInfectionLogic

/**
 * Class that represent the Infection Handler
 * @param virus represent the virus inside the game
 * @param regions contain all the regions inside the game
 */
class InfectionHandler(virus: Virus, regions: Iterable[Region]):
  /**
   *
   * @param regions represent all the regions inside the game
   * @param logic is the logic used for increment the infection inside the game.
   *              If this parameter is not present is used the given instance InternalInfectionLogic
   */
  def computeInfection(regions: Iterable[Region])(using logic: InfectionLogic): Unit =
    regions.foreach(region => logic.compute(region, virus))
