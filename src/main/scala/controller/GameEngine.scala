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
import model.vaccine.VaccineLogics.given
//import model.infection.InfectionLogics.

class GameEngine(val gameModel: GameModel):
  private val refreshTime: Int = 5//300
  var days: Int = 1;

  def addObserver(observer: DnaPointSpawnObserver): Unit =
    gameModel.dnaPointsHandler.addObserver(observer)
  def start(): Unit =
    gameModel.world.getRegion("Europe").get.infectedAmount = 1
    gameLoop()

  private def gameLoop(): Unit =
    val startTime: Long = System.currentTimeMillis()

    gameModel.infectionHandler.computeInfection(gameModel.world.getRegions(using infectedRegions))
    gameModel.infectionHandler.computeInfection(gameModel.world.getRegions(using infectedRegions))(using new ExternalInfectionLogic())
    gameModel.dnaPointsHandler.computeDnaPointSpawn()
    gameModel.vaccineHandler.computeResearchStep(this.getWorldInfectionPercentage)
    days = days + 1

    if this.getWorldInfectionPercentage < 100.0 && this.gameModel.vaccineHandler.vaccineProgression >= 100.0 then
      println("PERSO")
    else if this.getWorldInfectionPercentage >= 100.0 && this.gameModel.vaccineHandler.vaccineProgression < 100.0 then
      println("VINTO")
    else
      if (System.currentTimeMillis() - startTime) < refreshTime then Thread.sleep(refreshTime - (System.currentTimeMillis() - startTime))
      gameLoop()

    //println(gameModel.world.getRegion("Balkans").get.infectedAmount)
    //Compute Internal Infection
    //Compute External Infection
    //Compute Vaccine


  def getRegions: List[Region] = this.gameModel.world.getRegions
  def getRegion(name: String): Option[Region] = this.gameModel.world.getRegion(name)
  def loadMenu(): Unit = new MenuView(new MenuController(gameModel))
  def getWorldPopulation: Long = this.gameModel.world.getRegions.foldRight(0L)((region, population) => population + region.population)
  def getWorldInfectedAmount: Long = this.gameModel.world.getRegions(using infectedRegions).foldRight(0L)((region, infectedAmount) => infectedAmount + region.infectedAmount.toLong)
  def getWorldInfectionPercentage: Double = 1.0 * this.getWorldInfectedAmount / this.getWorldPopulation
  def getVaccineProgression: Double = this.gameModel.vaccineHandler.vaccineProgression
