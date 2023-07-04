package view

import controller.GameEngine

import java.awt.{BorderLayout, Dimension, GridBagLayout, Toolkit}
import javax.swing.{BoxLayout, JFrame, JPanel}

class GameView (val gameEngine: GameEngine):
  val frame = new JFrame()
  val worldMapPanel = new WorldMapPanel
  val allRegionsPanel = new AllRegionsPanel(gameEngine.getRegions())

  def start(): Unit =
    frame.add(worldMapPanel, BorderLayout.CENTER)
    frame.add(allRegionsPanel, BorderLayout.EAST)
    frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit.getScreenSize.width, Toolkit.getDefaultToolkit.getScreenSize.width))
    frame.setDefaultCloseOperation(3)
    frame.pack()
    frame.setVisible(true)
    renderLoop()

  def renderLoop(): Unit =
    new Thread{
      override def run(): Unit =
        while (true) {
          println(gameEngine.getRegions())
          allRegionsPanel.updateRegions(gameEngine.getRegions())
          Thread.sleep(1000)
        }
    }.start()
