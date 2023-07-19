package model

import model.configuration.Loader.ConfigurationsLoader
import model.dnapoints.DnaPoints.DnaPointsHandler
import model.dnapoints.DnaPoints.Logic.BasicLogic
import model.infection.{InfectionHandler, Virus}
import model.powerUp.PowerUpManager
import model.vaccine.VaccineHandler
import model.world.World
import model.world.Filters.given

/**
 * Class that contains all the entities that are part of the Model of PlagueDotScala
 */
class GameModel:
  private val dnaPointSpawnTime: Int = 30
  val world: World = ConfigurationsLoader.loadWorld()
  val virus: Virus = ConfigurationsLoader.loadVirus().get
  val infectionHandler: InfectionHandler = new InfectionHandler(virus, world.getRegions)
  val vaccineHandler: VaccineHandler = new VaccineHandler
  val dnaPointsHandler: DnaPointsHandler = DnaPointsHandler(BasicLogic(world, dnaPointSpawnTime))
  val powerUpManager: PowerUpManager = new PowerUpManager(virus, dnaPointsHandler)
