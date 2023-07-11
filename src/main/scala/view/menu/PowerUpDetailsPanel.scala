package view.menu

import java.awt.{BorderLayout, Color, GridLayout}
import javax.swing.border.EmptyBorder
import javax.swing.{BoxLayout, JButton, JLabel, JPanel}

class PowerUpDetailsPanel extends JPanel:
  this.setBackground(new Color(217,217,217))
  this.setBorder(new EmptyBorder(20,300,20,10))
  this.setLayout(new BorderLayout())
  this.add(new JLabel("POWER UP NAME"), BorderLayout.NORTH)
  this.add(new JLabel("+10 cold regions infectivity"), BorderLayout.CENTER)
  this.add(new JLabel("Increase the resistance of the virus in regions with cold climate."), BorderLayout.SOUTH)
  this.add(new RoundButton("B1"), BorderLayout.EAST)

