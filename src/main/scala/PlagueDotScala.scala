import controller.GameEngine
import view.GameView

object PlagueDotScala extends App:
  val gameView: GameView = new GameView()
  val gameEngine: GameEngine = new GameEngine()
  gameView.start()
  gameEngine.start()
