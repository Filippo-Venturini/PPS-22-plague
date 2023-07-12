package model

import model.configuration.Loader.ConfigurationsLoader
import model.dnapoints.DnaPoints.DnaPointsHandler
import model.dnapoints.DnaPoints.Logic.BasicLogic
import model.infection.{InfectionHandler, Virus}
import model.powerUp.PowerUpManager
import model.world.World
import model.world.Filters.given

class GameModel:
  val world: World = ConfigurationsLoader.loadWorld()
  val virus: Virus = ConfigurationsLoader.loadVirus().get
  val infectionHandler: InfectionHandler = new InfectionHandler(virus, world.getRegions)
  val dnaPointsHandler: DnaPointsHandler = DnaPointsHandler(BasicLogic(world, 60)) //TODO move 60 to a better place
  val powerUpManager: PowerUpManager = new PowerUpManager(virus, dnaPointsHandler)
