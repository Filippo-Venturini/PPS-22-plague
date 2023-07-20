package view.game

import controller.{GameEngine, MenuController}
import model.dnapoints.DnaPoints.{DnaPoint, DnaPointSpawnObserver}
import view.game.RegionsView.{RegionsPanel, WrapWithScrollBar}
import view.menu.MenuView

import java.awt.event.{KeyAdapter, KeyEvent}
import java.awt.{BorderLayout, Color, Dimension, Frame, GridBagLayout, Toolkit}
import javax.swing.{BoxLayout, JFrame, JOptionPane, JPanel, JScrollPane, ScrollPaneConstants}

class GameView (val gameEngine: GameEngine) extends DnaPointSpawnObserver:
  val frame = new JFrame()
  val regionsPanel: RegionsPanel = new RegionsPanel(gameEngine)
  val generalInfectionPanel: GeneralInfectionPanel = new GeneralInfectionPanel(gameEngine)
  val worldMapPanel: WorldMapPanel = new WorldMapPanel(gameEngine, regionsPanel)
  val keyListener: GameViewKeyListener = new GameViewKeyListener(gameEngine)

  def start(): Unit =
    frame.setTitle("PlagueDotScala")
    frame.add(worldMapPanel, BorderLayout.CENTER)
    frame.add(generalInfectionPanel, BorderLayout.SOUTH)
    frame.setDefaultCloseOperation(3)
    frame.addKeyListener(keyListener)
    frame.pack()
    frame.setExtendedState(Frame.MAXIMIZED_BOTH)
    frame.setResizable(true)
    frame.setVisible(true)
    frame.setMinimumSize(frame.getSize());
    frame.add(WrapWithScrollBar(regionsPanel), BorderLayout.EAST)
    renderLoop()

  def renderLoop(): Unit =
    new Thread{
      override def run(): Unit =
        while (true) {
          regionsPanel.refresh()
          generalInfectionPanel.refresh()
          Thread.sleep(100)
        }
    }.start()

  override def onDnaPointSpawn(dnaPoint: DnaPoint): Unit = worldMapPanel.showDnaPoint(dnaPoint)

  def showLostMessageDialog(): Unit =
    JOptionPane.showMessageDialog(frame, "The vaccine research is completed, you lost!", "You Lost", JOptionPane.WARNING_MESSAGE)

  def showWonMessageDialog(): Unit =
    JOptionPane.showMessageDialog(frame, "Your virus has infected the whole world, you won!", "You Won", JOptionPane.WARNING_MESSAGE)

class GameViewKeyListener(val gameEngine: GameEngine) extends KeyAdapter:
  override def keyPressed(evt :KeyEvent): Unit = evt match
    case evt if evt.getKeyChar() == 'm' => gameEngine.loadMenu()
    case evt if evt.getKeyChar() == 'i' => gameEngine.loadStartMenu()//TODO da eliminare
    case _ =>