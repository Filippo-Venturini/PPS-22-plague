import controller.GameEngine
import model.GameModel
import view.game.GameView

object PlagueDotScala extends App:
  val gameModel: GameModel = new GameModel()
  val gameEngine: GameEngine = new GameEngine(gameModel)
  val gameView: GameView = new GameView(gameEngine)
  gameEngine.setGameView(gameView)
  gameView.start()
  gameEngine.start()
