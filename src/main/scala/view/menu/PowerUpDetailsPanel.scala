package view.menu

import controller.MenuController
import model.powerUp.PowerUp

import java.awt.{BorderLayout, Color, Component, GridBagConstraints, GridBagLayout, GridLayout, Image, Insets}
import javax.imageio.ImageIO
import javax.swing.border.EmptyBorder
import javax.swing.{BoxLayout, ImageIcon, JButton, JComponent, JLabel, JPanel}

class PowerUpDetailsPanel(val virusPanel: VirusPanel, val menuController: MenuController) extends JPanel:
  this.setBackground(new Color(217,217,217))
  this.setBorder(new EmptyBorder(20,300,20,300))
  this.setLayout(new GridBagLayout())
  val constraint: GridBagConstraints = new GridBagConstraints()
  this.constraint.fill = GridBagConstraints.HORIZONTAL
  var powerUpShowed: PowerUp = null
  val powerUpNameLabel: JLabel = new JLabel()
  val powerUpPriceLabel: JLabel = new JLabel()
  val DNAPointsLabel: JLabel = new JLabel("DNA Points collected: " + this.menuController.getCollectedDNAPoints())
  val powerUpEffectLabel: JLabel = new JLabel()
  val powerUpDescriptionLabel: JLabel = new JLabel()
  val buyImage: Image = ImageIO.read(getClass.getResource("/buyPowerUp.png"))
  val buyButton: JButton = new JButton()
  buyButton.setIcon(new ImageIcon(buyImage))
  buyButton.setBackground(new Color(255,255,255))
  buyButton.setFocusPainted(false)

  this.constraint.gridx = 0
  this.constraint.gridy = 0
  this.constraint.gridwidth = 2
  this.add(powerUpNameLabel, this.constraint)
  this.constraint.gridx = 0
  this.constraint.gridy = 1
  this.constraint.gridwidth = 1
  this.constraint.insets = new Insets(10,0,0,0)
  this.add(powerUpPriceLabel, this.constraint)
  this.constraint.gridx = 1
  this.constraint.gridy = 1
  this.constraint.insets = new Insets(10,200,0,0)
  this.add(DNAPointsLabel, this.constraint)
  this.constraint.gridx = 0
  this.constraint.gridy = 2
  this.constraint.insets = new Insets(10,0,0,0)
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
    this.DNAPointsLabel.setText("DNA Points collected: " + this.menuController.getCollectedDNAPoints())
    this.buyButton.setEnabled(false)
    this.virusPanel.refreshVirusCharacteristics(this.menuController.getVirusConfiguration())
  })

  def refreshPowerUpInformation(powerUp: PowerUp, arePrerequisiteSatisfied: Boolean): Unit =
    this.powerUpShowed = powerUp
    this.powerUpNameLabel.setText(powerUp.powerUpType.toString)
    this.powerUpPriceLabel.setText("Price: " + powerUp.powerUpType.price.toString)
    this.powerUpEffectLabel.setText(powerUp.powerUpType.information.effect)
    this.powerUpDescriptionLabel.setText(powerUp.powerUpType.information.description)
    this.buyButton.setEnabled(this.menuController.getCollectedDNAPoints() >= powerUp.powerUpType.price && !powerUp.hasBeenBought && arePrerequisiteSatisfied)


