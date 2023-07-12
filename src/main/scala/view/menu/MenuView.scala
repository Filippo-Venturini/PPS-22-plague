package view.menu

import controller.{GameEngine, MenuController}

import java.awt.{BorderLayout, Color, Dimension, Frame, Toolkit}
import javax.swing.JFrame

class MenuView (val menuController: MenuController):
  val frame: JFrame = new JFrame()
  frame.setBackground(new Color(255,255,255))
  val virusPanel: VirusPanel = new VirusPanel(menuController.getVirusConfiguration())
  val powerUpDetailsPanel: PowerUpDetailsPanel = new PowerUpDetailsPanel
  val powerUpsGridPanel: PowerUpsGridPanel = new PowerUpsGridPanel(powerUpDetailsPanel)

  frame.setTitle("Menu")
  frame.add(virusPanel, BorderLayout.NORTH)
  frame.add(powerUpsGridPanel, BorderLayout.CENTER)
  frame.add(powerUpDetailsPanel, BorderLayout.SOUTH)
  frame.pack()
  frame.setExtendedState(Frame.MAXIMIZED_BOTH)
  frame.setVisible(true)
