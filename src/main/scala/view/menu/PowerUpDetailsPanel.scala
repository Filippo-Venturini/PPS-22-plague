package view.menu

import model.powerUp.PowerUp

import java.awt.{BorderLayout, Color, Component, GridLayout, Image}
import javax.imageio.ImageIO
import javax.swing.border.EmptyBorder
import javax.swing.{BoxLayout, ImageIcon, JButton, JComponent, JLabel, JPanel}

class PowerUpDetailsPanel extends JPanel:
  this.setBackground(new Color(217,217,217))
  this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))
  val powerUpNameLabel: JLabel = new JLabel("POWER UP NAME")
  val powerUpPriceLabel: JLabel = new JLabel("DNA Points Required: 5")
  val powerUpEffectLabel: JLabel = new JLabel("+10 cold regions infectivity")
  val powerUpDescriptionLabel: JLabel = new JLabel("Increase the resistance of the virus in regions with cold climate.")
  val buyImage: Image = ImageIO.read(getClass.getResource("/dna.jpg"))
  val buyButton: JButton = new JButton()
  buyButton.setIcon(new ImageIcon(buyImage))
  buyButton.setSize(50,50)
  buyButton.setBackground(new Color(196,29,29))
  buyButton.setFocusPainted(false)
  this.addComponents(powerUpNameLabel, powerUpPriceLabel, powerUpEffectLabel, powerUpDescriptionLabel, buyButton)

  def addComponents(components: JComponent*): Unit =
    components.foreach(c => {c.setAlignmentX(Component.CENTER_ALIGNMENT); this.add(c)})

  def refreshPowerUpInformations(powerUp: PowerUp): Unit =
    this.powerUpNameLabel.setText(powerUp.powerUpType.toString)
    this.powerUpPriceLabel.setText(powerUp.powerUpType.price.toString)


