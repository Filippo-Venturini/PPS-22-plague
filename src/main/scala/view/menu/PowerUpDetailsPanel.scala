package view.menu

import controller.MenuController
import model.powerUp.PowerUp

import java.awt.{BorderLayout, Color, Component, GridBagConstraints, GridBagLayout, GridLayout, Image}
import javax.imageio.ImageIO
import javax.swing.border.EmptyBorder
import javax.swing.{BoxLayout, ImageIcon, JButton, JComponent, JLabel, JPanel}

class PowerUpDetailsPanel(val menuController: MenuController) extends JPanel:
  this.setBackground(new Color(217,217,217))
  this.setBorder(new EmptyBorder(20,300,20,300))
  this.setLayout(new GridBagLayout())
  val constraint: GridBagConstraints = new GridBagConstraints()
  this.constraint.fill = GridBagConstraints.HORIZONTAL
  var powerUpShowed: PowerUp = null
  val powerUpNameLabel: JLabel = new JLabel("POWER UP NAME")
  val powerUpPriceLabel: JLabel = new JLabel("Price: 5")
  val DNAPointsLabel: JLabel = new JLabel("DNA Points: " + menuController.getCollectedDNAPoints())
  val powerUpEffectLabel: JLabel = new JLabel("+10 cold regions infectivity")
  val powerUpDescriptionLabel: JLabel = new JLabel("Increase the resistance of the virus in regions with cold climate.")
  val buyImage: Image = ImageIO.read(getClass.getResource("/buyPowerUp.png"))
  val buyButton: JButton = new JButton()
  buyButton.setIcon(new ImageIcon(buyImage))
  buyButton.setSize(50,50)
  buyButton.setBackground(new Color(255,255,255))
  buyButton.setFocusPainted(false)

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

  this.buyButton.addActionListener(_ => {
    this.menuController.purchasePowerUp(this.powerUpShowed.powerUpType)
    this.DNAPointsLabel.setText("DNA Points: " + this.menuController.getCollectedDNAPoints())
    this.buyButton.setEnabled(false)
  })

  def refreshPowerUpInformation(powerUp: PowerUp, arePrerequisiteSatisfied: Boolean): Unit =
    this.powerUpShowed = powerUp
    this.powerUpNameLabel.setText(powerUp.powerUpType.toString)
    this.powerUpPriceLabel.setText("Price: " + powerUp.powerUpType.price.toString)
    this.powerUpEffectLabel.setText(powerUp.powerUpType.information.effect)
    this.powerUpDescriptionLabel.setText(powerUp.powerUpType.information.description)
    this.buyButton.setEnabled(this.menuController.getCollectedDNAPoints() >= powerUp.powerUpType.price && !powerUp.hasBeenBought && arePrerequisiteSatisfied)


