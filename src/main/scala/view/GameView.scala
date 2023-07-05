package view

import controller.GameEngine

import java.awt.{BorderLayout, Dimension, GridBagLayout, Toolkit}
import javax.swing.{BoxLayout, JFrame, JPanel, JScrollPane}

class GameView (val gameEngine: GameEngine):
  val frame = new JFrame()
  val worldMapPanel: WorldMapPanel = new WorldMapPanel
  val allRegionsPanel: AllRegionsPanel = new AllRegionsPanel(gameEngine.getRegions())
  val regionsScrollPanel: JScrollPane = new JScrollPane()

  def start(): Unit =
    regionsScrollPanel.setViewportView(allRegionsPanel)
    frame.add(worldMapPanel, BorderLayout.CENTER)
    frame.add(regionsScrollPanel, BorderLayout.EAST)
    frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit.getScreenSize.width, Toolkit.getDefaultToolkit.getScreenSize.width))
    frame.setDefaultCloseOperation(3)
    frame.pack()
    frame.setVisible(true)
    renderLoop()

  def renderLoop(): Unit =
    new Thread{
      override def run(): Unit =
        while (true) {
          allRegionsPanel.updateRegions(gameEngine.getRegions())
          Thread.sleep(1000)
        }
    }.start()
