package controller

import model.world.RegionTypes.RegionConfiguration
import model.world.{BasicRegion, Region, World}
import model.world.Filters.{RegionFilter, notInfectedRegions, given}
import model.configuration.Loader.ConfigurationsLoader.given
import model.configuration.Loader.{ConfigurationsLoader, RegionFile}
import model.configuration.Loader
import model.infection.{InfectionHandler, Virus, VirusConfiguration}
import model.infection.InfectionLogics.given

class GameEngine():
  private val refreshTime: Int = 100
  private val world: World = ConfigurationsLoader.loadWorld()
  private val virus: Virus = ConfigurationsLoader.loadVirus().get
  private val infectionHandler = new InfectionHandler(virus, world.getRegions)

  def start(): Void =
    world.getRegion("Balkans").get.infectedAmount = 2
    gameLoop()

  private def gameLoop(): Void =
    val startTime: Long = System.currentTimeMillis()
    infectionHandler.computeInfection(world.getRegions)
    //Compute Internal Infection
    //Compute External Infection
    //Compute Vaccine
    Thread.sleep(refreshTime - (System.currentTimeMillis() - startTime))
    gameLoop()

  def getRegions(): List[Region] = this.world.getRegions
  def getVirusConfiguration(): VirusConfiguration = this.virus.getActualConfiguration
