import controller.{GameEngine, LauncherController}
import model.GameModel
import view.game.GameView
import view.launcher.LauncherView

object PlagueDotScala extends App:
  val gameModel: GameModel = new GameModel()
  val launcherController: LauncherController = new LauncherController(gameModel)
  val launcherView: LauncherView = new LauncherView(launcherController)
