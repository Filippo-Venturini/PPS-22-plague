package view.menu

import controller.{GameEngine, MenuController}

import java.awt.{BorderLayout, Color, Dimension, Frame, Toolkit}
import javax.swing.JFrame

/**
 * Class that represent the menu that contains: the power up' hierarchy, the virus information and the power up details
 *
 * @param menuController : the controller for handling the menu
 */
class MenuView (val menuController: MenuController):
  private val frame: JFrame = new JFrame()
  private val virusPanel: VirusPanel = new VirusPanel(menuController.getVirusConfiguration)
  private val powerUpDetailsPanel: PowerUpDetailsPanel = new PowerUpDetailsPanel(virusPanel, menuController)
  private val powerUpsGridPanel: PowerUpsGridPanel = new PowerUpsGridPanel(powerUpDetailsPanel, menuController)
  
  powerUpDetailsPanel.setPowerUpsGridPanel(powerUpsGridPanel)

  frame.setTitle("Menu")
  frame.setBackground(new Color(255,255,255))
  frame.add(virusPanel, BorderLayout.NORTH)
  frame.add(powerUpsGridPanel, BorderLayout.CENTER)
  frame.add(powerUpDetailsPanel, BorderLayout.SOUTH)
  frame.pack()
  frame.setSize(1200,800)
  frame.setResizable(false)
  frame.setVisible(true)
