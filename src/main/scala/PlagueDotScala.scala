import controller.GameEngine
import view.game.GameView

object PlagueDotScala extends App:
  val gameEngine: GameEngine = new GameEngine()
  val gameView: GameView = new GameView(gameEngine)
  gameEngine.addObserver(gameView)
  gameView.start()
  gameEngine.start()
