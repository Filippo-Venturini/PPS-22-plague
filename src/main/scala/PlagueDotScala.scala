import controller.GameEngine
import view.GameView

object PlagueDotScala extends App:
  val gameEngine: GameEngine = new GameEngine()
  val gameView: GameView = new GameView(gameEngine)
  gameView.start()
  gameEngine.start()
