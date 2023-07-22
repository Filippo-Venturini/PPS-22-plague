package controller

import model.GameModel
import model.infection.VirusConfiguration
import model.powerUp.{PowerUp, PowerUpType}
import model.world.Filters.given
import model.world.Region

/**
 * Class that represent the controller that bind the Game Model with the Launcher View
 * @param gameModel the model of the game
 */
class LauncherController(val gameModel: GameModel):
  /**
   * @return all the regions inside the game
   */
  def getAllRegions: List[Region] = gameModel.world.getRegions


