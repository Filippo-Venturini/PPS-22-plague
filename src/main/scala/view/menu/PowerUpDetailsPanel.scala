package view.menu

import model.powerUp.PowerUp

import java.awt.{BorderLayout, Color, Component, GridBagConstraints, GridBagLayout, GridLayout, Image}
import javax.imageio.ImageIO
import javax.swing.border.EmptyBorder
import javax.swing.{BoxLayout, ImageIcon, JButton, JComponent, JLabel, JPanel}

class PowerUpDetailsPanel extends JPanel:
  this.setBackground(new Color(217,217,217))
  this.setBorder(new EmptyBorder(20,300,20,300))
  this.setLayout(new GridBagLayout())
  val constraint: GridBagConstraints = new GridBagConstraints()
  this.constraint.fill = GridBagConstraints.HORIZONTAL
  val powerUpNameLabel: JLabel = new JLabel("POWER UP NAME")
  val powerUpPriceLabel: JLabel = new JLabel("Price: 5")
  val DNAPointsLabel: JLabel = new JLabel("DNA Points: 5")
  val powerUpEffectLabel: JLabel = new JLabel("+10 cold regions infectivity")
  val powerUpDescriptionLabel: JLabel = new JLabel("Increase the resistance of the virus in regions with cold climate.")
  val buyImage: Image = ImageIO.read(getClass.getResource("/buyPowerUp.png"))
  val buyButton: JButton = new JButton()
  buyButton.setIcon(new ImageIcon(buyImage))
  buyButton.setSize(50,50)
  buyButton.setBackground(new Color(255,255,255))
  buyButton.setFocusPainted(false)
  /*this.addComponents(powerUpNameLabel, new JLabel(), powerUpPriceLabel, DNAPointsLabel, powerUpEffectLabel, new JLabel(), powerUpDescriptionLabel,
     new JLabel(), new JLabel(), buyButton)*/

  this.constraint.gridx = 0
  this.constraint.gridy = 0
  this.constraint.gridwidth = 2
  this.add(powerUpNameLabel, this.constraint)
  this.constraint.gridx = 0
  this.constraint.gridy = 1
  this.constraint.gridwidth = 1
  this.add(powerUpPriceLabel, this.constraint)
  this.constraint.gridx = 1
  this.constraint.gridy = 1
  this.constraint.anchor = GridBagConstraints.PAGE_END
  this.add(DNAPointsLabel, this.constraint)
  this.constraint.gridx = 0
  this.constraint.gridy = 2
  this.add(powerUpEffectLabel, this.constraint)
  this.constraint.gridx = 0
  this.constraint.gridy = 3
  this.constraint.gridwidth = 2
  this.add(powerUpDescriptionLabel, this.constraint)
  this.constraint.gridx = 2
  this.constraint.gridy = 4
  this.add(buyButton, this.constraint)

  def addComponents(components: JComponent*): Unit =
    components.foreach(c => {this.add(c, constraint)})

  def refreshPowerUpInformations(powerUp: PowerUp): Unit =
    this.powerUpNameLabel.setText(powerUp.powerUpType.toString)
    this.powerUpPriceLabel.setText(powerUp.powerUpType.price.toString)


