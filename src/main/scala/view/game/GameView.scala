package view.game

import controller.GameEngine
import model.dnapoints.DnaPoints.DnaPointSpawnObserver
import view.menu.MenuView

import java.awt.event.{KeyAdapter, KeyEvent}
import java.awt.{BorderLayout, Dimension, Frame, GridBagLayout, Toolkit}
import javax.swing.{BoxLayout, JFrame, JPanel, JScrollPane}

class GameView (val gameEngine: GameEngine) extends DnaPointSpawnObserver:
  val frame = new JFrame()
  val worldMapPanel: WorldMapPanel = new WorldMapPanel(gameEngine)
  val allRegionsPanel: AllRegionsPanel = new AllRegionsPanel(gameEngine.getRegions())
  val regionsScrollPanel: JScrollPane = new JScrollPane()
  val keyListener: GameViewKeyListener = new GameViewKeyListener(gameEngine)

  def start(): Unit =
    regionsScrollPanel.setViewportView(allRegionsPanel)
    frame.setTitle("PlagueDotScala")
    frame.add(worldMapPanel, BorderLayout.CENTER)
    frame.add(regionsScrollPanel, BorderLayout.EAST)
    frame.setDefaultCloseOperation(3)
    frame.addKeyListener(keyListener)
    frame.pack()
    frame.setExtendedState(Frame.MAXIMIZED_BOTH)
    frame.setVisible(true)
    renderLoop()

  def renderLoop(): Unit =
    new Thread{
      override def run(): Unit =
        while (true) {
          allRegionsPanel.updateRegions(gameEngine.getRegions())
          Thread.sleep(100)
        }
    }.start()

  override def onDnaPointSpawn(regionName: String): Unit = println("Dna point spawned in " + regionName)

class GameViewKeyListener(val gameEngine: GameEngine) extends KeyAdapter:
  override def keyPressed(evt :KeyEvent): Unit = evt match
    case evt if evt.getKeyChar() == 'm' => new MenuView(gameEngine)
    case _ =>