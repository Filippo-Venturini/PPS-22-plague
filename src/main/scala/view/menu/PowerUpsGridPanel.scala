package view.menu

import java.awt.Color
import javax.swing.{JLabel, JPanel, JButton}
import model.powerUp.PowerUpType




class PowerUpsGridPanel extends JPanel:

  val powerUps: List[PowerUpType] = List.empty[PowerUpType]

  //PowerUpType.values.foreach(powerUpType => powerUps )

  this.setBackground(new Color(0,0,255))
  this.add(new JLabel("GRID"))
  println(powerUps.length)
  //powerUps.appendedAll(PowerUpType.values)
  powerUps.foreach(p => println(p))
  //powerUps.foreach(p => this.add(new JButton("ciao")))


