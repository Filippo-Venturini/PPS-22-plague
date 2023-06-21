package view

import java.awt.{Dimension, Toolkit}
import javax.swing.{JFrame, JPanel}

class GameView:
  val frame = new JFrame()
  val panel = new WorldMapPanel

  def start(): Unit =
    frame.add(panel)
    frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit.getScreenSize.width, Toolkit.getDefaultToolkit.getScreenSize.width))
    frame.setDefaultCloseOperation(3)
    frame.pack()
    frame.setVisible(true)