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
import view.game.GameView
import view.startMenu.StartMenuView

class GameEngine(val gameModel: GameModel):
  private val refreshTime: Int = 5//300
  private var gameView: GameView = null
  var days: Int = 1

  def isLost: Boolean = this.getWorldInfectionPercentage < 100.0 && this.gameModel.vaccineHandler.vaccineProgression >= 100.0
  def isWon: Boolean = this.getWorldInfectionPercentage >= 100.0 && this.gameModel.vaccineHandler.vaccineProgression < 100.0

  def start(): Unit =
    gameModel.world.getRegion("Europe").get.numberOfInfected = 1
    gameLoop()

  private def gameLoop(): Unit =
    val startTime: Long = System.currentTimeMillis()

    gameModel.infectionHandler.computeInfection(gameModel.world.getRegions(using infectedRegions))
    gameModel.infectionHandler.computeInfection(gameModel.world.getRegions(using infectedRegions))(using new ExternalInfectionLogic())
    gameModel.dnaPointsHandler.computeDnaPointSpawn()
    gameModel.vaccineHandler.computeResearchStep(this.getWorldInfectionPercentage)
    days = days + 1

    if isLost then
      this.gameView.showLostMessageDialog()
    else if isWon then
      this.gameView.showWonMessageDialog()
    else
      if (System.currentTimeMillis() - startTime) < refreshTime then Thread.sleep(refreshTime - (System.currentTimeMillis() - startTime))
      gameLoop()

  def setGameView(gameView: GameView): Unit =
    this.gameView = gameView
    gameModel.dnaPointsHandler.addObserver(gameView)
  def getRegions: List[Region] = this.gameModel.world.getRegions
  def getRegion(name: String): Option[Region] = this.gameModel.world.getRegion(name)
  def loadMenu(): Unit = new MenuView(new MenuController(gameModel))
  def loadStartMenu(): Unit = new StartMenuView(new StartMenuController(gameModel))
  def getWorldPopulation: Long = this.gameModel.world.getRegions.foldRight(0L)((region, population) => population + region.population)
  def getWorldInfectedAmount: Long = this.gameModel.world.getRegions(using infectedRegions).foldRight(0L)((region, infectedAmount) => infectedAmount + region.numberOfInfected.toLong)
  def getWorldInfectionPercentage: Double = 100.0 * this.getWorldInfectedAmount / this.getWorldPopulation
  def getVaccineProgression: Double = this.gameModel.vaccineHandler.vaccineProgression
