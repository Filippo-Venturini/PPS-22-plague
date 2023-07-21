package view.launcher

import controller.{GameEngine, LauncherController}
import model.GameModel
import model.world.Region
import view.game.GameView

import java.awt.*
import java.awt.event.{ActionEvent, WindowEvent}
import javax.swing.border.EmptyBorder
import javax.swing.{BoxLayout, JButton, JComponent, JFrame, JLabel, JList, JPanel, JTextField}

class LauncherView(val launcherController: LauncherController):
  val frame: JFrame = new JFrame()
  frame.setBackground(new Color(255,255,255))
  val panel: JPanel = new JPanel
  frame.setTitle("Start Menu")
  panel.setLayout(new GridBagLayout())

  val constraints = new GridBagConstraints()
  constraints.fill = GridBagConstraints.HORIZONTAL

  constraints.insets = new Insets(30, 10, 0, 10)

  constraints.gridy = 0
  val msgWelcome: JLabel = new JLabel("Welcome to Plug.Scala!")
  panel.add(msgWelcome, constraints)

  constraints.gridy = constraints.gridy + 1
  val msgInsertVirusName: JLabel = new JLabel("Please choose the virus name:")
  panel.add(msgInsertVirusName, constraints)

  constraints.gridy = constraints.gridy + 1
  val txtVirusName: JTextField = new JTextField()
  panel.add(txtVirusName, constraints)

  constraints.gridy = constraints.gridy + 1
  val msgChooseStartRegion: JLabel = new JLabel("Choose the region where the virus starts spreading:")
  panel.add(msgChooseStartRegion, constraints)

  constraints.gridy = constraints.gridy + 1
  val lstRegions: JList[String] = new JList[String](launcherController.getAllRegions.map(region => region.name).toArray)
  lstRegions.setSelectedIndex(0)
  panel.add(lstRegions, constraints)

  constraints.gridy = constraints.gridy + 1
  val btnStartGame: JButton = new JButton("Start game!")
  btnStartGame.addActionListener((e: ActionEvent) => {
    val gameEngine: GameEngine = new GameEngine(this.launcherController.gameModel)
    val gameView: GameView = new GameView(gameEngine)
    gameEngine.setGameView(gameView)
    new Thread{
      override def run(): Unit =
        gameView.start()
        gameEngine.start(lstRegions.getSelectedValue)
    }.start()
    this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

  })
  panel.add(btnStartGame, constraints)



  frame.add(panel)
  frame.pack()
  frame.setSize(500,800)
  frame.setResizable(false)
  frame.setVisible(true)
