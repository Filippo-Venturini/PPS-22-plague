package controller

import model.GameModel
import model.world.RegionParameters.RegionConfiguration
import model.world.{BasicRegion, Region, World}
import model.world.Filters.{RegionFilter, infectedRegions, notInfectedRegions, given}
import model.configuration.Loader.ConfigurationsLoader.given
import model.configuration.Loader.{ConfigurationsLoader}
import model.configuration.Loader
import model.dnapoints.DnaPoints.DnaPointsHandler
import model.dnapoints.DnaPoints.Logic.BasicLogic
import model.infection.{ExternalInfectionLogic, InfectionHandler, Virus, VirusConfiguration}
import model.infection.InfectionLogics.{externalInfectionLogic, given}
import model.powerUp.PowerUpManager
import model.dnapoints.DnaPoints.DnaPointSpawnObserver
import view.menu.MenuView
import view.game.GameView
import view.launcher.LauncherView

import scala.annotation.tailrec

/**
 * Class that represent the main controller of the application, it contains the main loop
 *
 * @param gameModel : the reference to the game model
 */
class GameEngine(val gameModel: GameModel):
  private val refreshTime: Int = 300
  private var gameView: GameView = _
  var days: Int = 1

  /**
   * It start the infection and make the game loop start
   */
  def start(startRegionName: String, virusName: String): Unit =
    gameModel.infectionHandler.startInfection(gameModel.world.getRegionByName(startRegionName).get)
    gameModel.virus.name = virusName
    gameLoop()

  /**
   * Game Loop that handle the progression of the game. It execute a step for internal and external infection,
   * for the vaccine research and also for the DNA Points spawn.
   */
  @tailrec
  private def gameLoop(): Unit =
    val startTime: Long = System.currentTimeMillis()

    gameModel.infectionHandler.computeInfection(gameModel.world.getRegions(using infectedRegions))
    gameModel.infectionHandler.computeInfection(gameModel.world.getRegions(using infectedRegions))(using externalInfectionLogic)
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

  /**
   * Add to the game engine a reference to the game view
   *
   * @param gameView the game view to be set
   */
  def setGameView(gameView: GameView): Unit =
    this.gameView = gameView
    gameModel.dnaPointsHandler.addObserver(gameView)

  /**
   * @return true if the vaccine progression is completed but the infection of the world not yet
   */
  def isLost: Boolean = this.getWorldInfectionPercentage < 100.0 && this.gameModel.vaccineHandler.vaccineProgression >= 100.0

  /**
   * @return true if the world is totally infected and the vaccine research is not completed
   */
  def isWon: Boolean = this.getWorldInfectionPercentage >= 100.0 && this.gameModel.vaccineHandler.vaccineProgression < 100.0

  /**
   * @return the list of all the world's regions
   */
  def getRegions: List[Region] = this.gameModel.world.getRegions

  /**
   * @param name : the name of the region to be returned
   * @return an Option filled if the region is present
   */
  def getRegion(name: String): Option[Region] = this.gameModel.world.getRegionByName(name)

  /**
   * Load the menu view that make possible to purchase power-ups
   */
  def loadMenu(): Unit = new MenuView(new MenuController(gameModel))
  def loadLauncher(): Unit = new LauncherView(new LauncherController(new GameModel))

  /**
   * @return the total population of the world
   */
  def getWorldPopulation: Long = this.gameModel.world.getRegions.foldRight(0L)((region, population) => population + region.population)

  /**
   * @return the total infected amount of the entire world
   */
  def getWorldInfectedAmount: Long = this.gameModel.world.getRegions(using infectedRegions).foldRight(0L)((region, infectedAmount) => infectedAmount + region.infectedAmount.toLong)

  /**
   * @return the percentage of the infected population of the entire world
   */
  def getWorldInfectionPercentage: Double = 100.0 * this.getWorldInfectedAmount / this.getWorldPopulation

  /**
   * @return the vaccine progression level in a value from 0% to 100%
   */
  def getVaccineProgression: Double = this.gameModel.vaccineHandler.vaccineProgression

  /**
   * @return true if port are enabled
   */
  def arePortsEnabled: Boolean = this.gameModel.virus.portEnabled

  /**
   * @return true if airport are enabled
   */
  def areAirportsEnabled: Boolean = this.gameModel.virus.airportEnabled
