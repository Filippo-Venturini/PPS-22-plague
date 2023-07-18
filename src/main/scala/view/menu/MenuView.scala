package view.menu

import controller.{GameEngine, MenuController}

import java.awt.{BorderLayout, Color, Dimension, Frame, Toolkit}
import javax.swing.JFrame

class MenuView (val menuController: MenuController):
  val frame: JFrame = new JFrame()
  frame.setBackground(new Color(255,255,255))
  val virusPanel: VirusPanel = new VirusPanel(menuController.getVirusConfiguration())
  val powerUpDetailsPanel: PowerUpDetailsPanel = new PowerUpDetailsPanel(virusPanel, menuController)
  val powerUpsGridPanel: PowerUpsGridPanel = new PowerUpsGridPanel(powerUpDetailsPanel, menuController)
  
  powerUpDetailsPanel.setPowerUpsGridPanel(powerUpsGridPanel)

  frame.setTitle("Menu")
  frame.add(virusPanel, BorderLayout.NORTH)
  frame.add(powerUpsGridPanel, BorderLayout.CENTER)
  frame.add(powerUpDetailsPanel, BorderLayout.SOUTH)
  frame.pack()
  frame.setSize(1200,800)
  //frame.setExtendedState(Frame.MAXIMIZED_BOTH)
  frame.setResizable(false)
  frame.setVisible(true)
