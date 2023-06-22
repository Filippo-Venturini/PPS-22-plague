package model.infection

import model.world.Region

object InfectionHandler:

  trait Infection:
    def setVirus(virus: VirusStructure): Unit
    def computeInfection(region: Iterable[Region])(using logic: InfectionLogic): Unit
