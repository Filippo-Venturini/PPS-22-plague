package controller

import model.GameModel
import model.infection.VirusConfiguration
import model.powerUp.{PowerUp, PowerUpType}
import model.world.Filters.given
import model.world.Region
import view.game.GameView

/**
 * Class that represent the controller that bind the Game Model with the Launcher View
 * @param gameModel the model of the game
 */
class LauncherController(val gameModel: GameModel):
  /**
   * @return all the regions inside the game
   */
  def getAllRegions: List[Region] = gameModel.world.getRegions

  /**
   * Start a game session
   * @param startRegionName is the start region name
   * @param virusName is the game virus name
   */
  def startGame(startRegionName: String, virusName: String): Unit =
    val gameEngine: GameEngine = new GameEngine(gameModel)
    val gameView: GameView = new GameView(gameEngine)
    gameEngine.setGameView(gameView)
    new Thread {
      override def run(): Unit =
        gameView.start()
        gameEngine.start(startRegionName, virusName)
    }.start()