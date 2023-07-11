package view.menu

import controller.GameEngine

import java.awt.{BorderLayout, Color, Dimension, Toolkit}
import javax.swing.JFrame

class MenuView (val gameEngine: GameEngine):
  val frame: JFrame = new JFrame()
  frame.setBackground(new Color(255,255,255))
  val virusPanel: VirusPanel = new VirusPanel(gameEngine.getVirusConfiguration())
  val powerUpDetailsPanel: PowerUpDetailsPanel = new PowerUpDetailsPanel
  val powerUpsGridPanel: PowerUpsGridPanel = new PowerUpsGridPanel(powerUpDetailsPanel)

  frame.add(virusPanel, BorderLayout.NORTH)
  frame.add(powerUpsGridPanel, BorderLayout.CENTER)
  frame.add(powerUpDetailsPanel, BorderLayout.SOUTH)

  frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit.getScreenSize.width, Toolkit.getDefaultToolkit.getScreenSize.height))
  frame.pack()
  frame.setVisible(true)
