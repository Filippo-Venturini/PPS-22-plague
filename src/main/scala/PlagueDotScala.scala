import controller.{GameEngine, LauncherController}
import model.GameModel
import view.game.GameView
import view.launcher.LauncherView

object PlagueDotScala extends App:
  val gameModel: GameModel = new GameModel()
  val gameEngine: GameEngine = new GameEngine(gameModel)
  val gameView: GameView = new GameView(gameEngine)
  gameEngine.setGameView(gameView)
  gameView.start()
  gameEngine.start()
  /*val launcherController: LauncherController = new LauncherController(gameModel)
  val launcherView: LauncherView = new LauncherView(launcherController)*/
