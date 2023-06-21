package model.infection

import model.infection.VirusStructure.Virus
import model.world.Region

object InfectionHandler:

  trait Infection:
    def setVirus(virus: Virus): Unit
    def computeInfection(region: Iterable[Region])(using logic: InfectionLogic): Unit
