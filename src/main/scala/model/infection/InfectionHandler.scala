package model.infection

import model.world.Region

object InfectionLogics:
  given InfectionLogic = new InternalInfectionLogic

class InfectionHandler(virus: Virus, region: Iterable[Region]):
  def computeInfection(region: Iterable[Region])(using logic: InfectionLogic): Unit =
    region.foreach(region => logic.compute(region, virus))
