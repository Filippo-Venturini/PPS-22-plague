package model.infection

import model.world.Region

trait Infection:
  def computeInfection(region: Iterable[Region])(using logic: InfectionLogic): Unit


class InfectionHandler(virus: Virus, region: Iterable[Region]) extends Infection:
  override def computeInfection(region: Iterable[Region])(using logic: InfectionLogic): Unit =
    region.foreach(region => logic.compute(region, virus))
