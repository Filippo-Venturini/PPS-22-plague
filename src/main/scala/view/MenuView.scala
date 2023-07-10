package view

import controller.GameEngine

import java.awt.{Dimension, Toolkit}
import javax.swing.JFrame

class MenuView (val gameEngine: GameEngine):
  val frame = new JFrame()
  frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit.getScreenSize.width, Toolkit.getDefaultToolkit.getScreenSize.height))
  frame.pack()
  frame.setVisible(true)
