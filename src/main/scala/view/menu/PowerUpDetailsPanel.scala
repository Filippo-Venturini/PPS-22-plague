package view.menu

import model.powerUp.PowerUp

import java.awt.{BorderLayout, Color, GridLayout}
import javax.swing.border.EmptyBorder
import javax.swing.{BoxLayout, JButton, JLabel, JPanel}

class PowerUpDetailsPanel extends JPanel:
  this.setBackground(new Color(217,217,217))
  this.setBorder(new EmptyBorder(20,300,20,10))
  this.setLayout(new BorderLayout())
  val powerUpNameLabel: JLabel = new JLabel("POWER UP NAME")
  val powerUpPriceLabel: JLabel = new JLabel("DNA Points Required: 5")
  val powerUpEffectLabel: JLabel = new JLabel("+10 cold regions infectivity")
  val powerUpDescriptionLabel: JLabel = new JLabel("Increase the resistance of the virus in regions with cold climate.")
  this.add(powerUpNameLabel, BorderLayout.NORTH)
  this.add(powerUpPriceLabel, BorderLayout.WEST)
  this.add(powerUpEffectLabel, BorderLayout.CENTER)
  this.add(powerUpDescriptionLabel, BorderLayout.SOUTH)
  this.add(new RoundButton(), BorderLayout.EAST)

  def refreshPowerUpInformations(powerUp: PowerUp): Unit =
    this.powerUpNameLabel.setText(powerUp.powerUpType.toString)
    this.powerUpPriceLabel.setText(powerUp.powerUpType.price.toString)


