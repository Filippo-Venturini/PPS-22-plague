package view

import java.awt.{BorderLayout, Dimension, GridBagLayout, Toolkit}
import javax.swing.{BoxLayout, JFrame, JPanel}

class GameView:
  val frame = new JFrame()
  val worldMapPanel = new WorldMapPanel
  val allRegionsPanel = new AllRegionsPanel

  def start(): Unit =
    frame.add(worldMapPanel, BorderLayout.CENTER)
    frame.add(allRegionsPanel, BorderLayout.EAST)
    frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit.getScreenSize.width, Toolkit.getDefaultToolkit.getScreenSize.width))
    frame.setDefaultCloseOperation(3)
    frame.pack()
    frame.setVisible(true)
