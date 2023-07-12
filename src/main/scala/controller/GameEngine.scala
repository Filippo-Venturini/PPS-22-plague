package controller

import model.GameModel
import model.world.RegionTypes.RegionConfiguration
import model.world.{BasicRegion, Region, World}
import model.world.Filters.{RegionFilter, infectedRegions, notInfectedRegions, given}
import model.configuration.Loader.ConfigurationsLoader.given
import model.configuration.Loader.{ConfigurationsLoader, RegionFile}
import model.configuration.Loader
import model.dnapoints.DnaPoints.DnaPointsHandler
import model.dnapoints.DnaPoints.Logic.BasicLogic
import model.infection.{ExternalInfectionLogic, InfectionHandler, Virus, VirusConfiguration}
import model.infection.InfectionLogics.given
import model.powerUp.PowerUpManager
import model.dnapoints.DnaPoints.DnaPointSpawnObserver
import view.menu.MenuView
//import model.infection.InfectionLogics.

class GameEngine(val gameModel: GameModel):
  private val refreshTime: Int = 150

  def addObserver(observer: DnaPointSpawnObserver): Unit =
    gameModel.dnaPointsHandler.addObserver(observer)
  def start(): Void =
    gameModel.world.getRegion("Balkans").get.infectedAmount = 2
    gameLoop()

  private def gameLoop(): Void =
    val startTime: Long = System.currentTimeMillis()
    gameModel.infectionHandler.computeInfection(gameModel.world.getRegions(using infectedRegions))
    gameModel.infectionHandler.computeInfection(gameModel.world.getRegions(using infectedRegions))(using new ExternalInfectionLogic())
    gameModel.dnaPointsHandler.computeDnaPointSpawn()
    //println(world.getRegion("Balkans").get.infectedAmount)
    //Compute Internal Infection
    //Compute External Infection
    //Compute Vaccine
    if (System.currentTimeMillis() - startTime) < refreshTime then Thread.sleep(refreshTime - (System.currentTimeMillis() - startTime))
    gameLoop()

  def getRegions(): List[Region] = this.gameModel.world.getRegions
  
  def loadMenu(): Unit = new MenuView(new MenuController(gameModel))
