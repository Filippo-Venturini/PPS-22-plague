package view.launcher

import controller.LauncherController

import java.awt.*
import javax.swing.{JFrame, JLabel, JPanel}

class LauncherView(val launcherController: LauncherController):
  val frame: JFrame = new JFrame()
  frame.setBackground(new Color(255,255,255))
  val panel: JPanel = new JPanel
  frame.setTitle("Start Menu")
  frame.add(panel)

  val msgWelcome: JLabel = new JLabel("Welcome to Plug.Scala! \nPlease choose the virus name")


  frame.pack()
  frame.setSize(1200,800)
  //frame.setExtendedState(Frame.MAXIMIZED_BOTH)
  frame.setResizable(false)
  frame.setVisible(true)
