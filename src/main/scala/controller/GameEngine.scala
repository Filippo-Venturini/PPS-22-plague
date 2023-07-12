package controller

import model.world.RegionTypes.RegionConfiguration
import model.world.{BasicRegion, Region, World}
import model.world.Filters.{RegionFilter, infectedRegions, notInfectedRegions, given}
import model.configuration.Loader.ConfigurationsLoader.given
import model.configuration.Loader.{ConfigurationsLoader, RegionFile}
import model.configuration.Loader
import model.dnapoints.DnaPoints.DnaPointsHandler
import model.dnapoints.DnaPoints.Logic.BasicLogic
import model.infection.{InfectionHandler, Virus, VirusConfiguration}
import model.infection.InfectionLogics.given
import model.powerUp.PowerUpManager
import model.dnapoints.DnaPoints.DnaPointSpawnObserver

class GameEngine():
  private val refreshTime: Int = 500
  private val world: World = ConfigurationsLoader.loadWorld()
  private val virus: Virus = ConfigurationsLoader.loadVirus().get
  private val infectionHandler = new InfectionHandler(virus, world.getRegions)
  private val dnaPointsHandler = DnaPointsHandler(BasicLogic(world, 60))//TODO move 60 to a better place
  private val powerUpManager = new PowerUpManager(virus, dnaPointsHandler)

  def addObserver(observer: DnaPointSpawnObserver): Unit =
    dnaPointsHandler.addObserver(observer)
  def start(): Void =
    world.getRegion("Balkans").get.infectedAmount = 2
    gameLoop()

  private def gameLoop(): Void =
    val startTime: Long = System.currentTimeMillis()
    infectionHandler.computeInfection(world.getRegions(using infectedRegions))
    //infectionHandler.computeInfection(world.getRegions(using infectedRegions))(using new ExternalInfectionLogic())
    dnaPointsHandler.computeDnaPointSpawn()
    //println(world.getRegion("Balkans").get.infectedAmount)
    //Compute Internal Infection
    //Compute External Infection
    //Compute Vaccine
    Thread.sleep(refreshTime - (System.currentTimeMillis() - startTime))
    gameLoop()

  def getRegions(): List[Region] = this.world.getRegions
  def getVirusConfiguration(): VirusConfiguration = this.virus.getActualConfiguration
