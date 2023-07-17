package view.startMenu

import controller.StartMenuController

import java.awt.*
import javax.swing.{JFrame, JPanel}

class StartMenuView(val startMenuController: StartMenuController):
  val frame: JFrame = new JFrame()
  frame.setBackground(new Color(255,255,255))
  val panel: JPanel = new JPanel

  frame.setTitle("Start Menu")

  frame.add(panel)

  frame.pack()
  frame.setSize(1200,800)
  //frame.setExtendedState(Frame.MAXIMIZED_BOTH)
  frame.setResizable(false)
  frame.setVisible(true)
