package view.menu

import controller.MenuController
import model.powerUp.PowerUp

import java.awt.{BorderLayout, Color, Component, Font, GridBagConstraints, GridBagLayout, GridLayout, Image, Insets}
import javax.imageio.ImageIO
import javax.swing.border.EmptyBorder
import javax.swing.{BoxLayout, ImageIcon, JButton, JComponent, JLabel, JPanel}

/**
 * Class that represent the panel that shows the details about power ups
 *
 * @param virusPanel : the reference to the virus panel for refreshing it's statistics
 * @param menuController : the reference to the menu controller
 */
class PowerUpDetailsPanel(val virusPanel: VirusPanel, val menuController: MenuController) extends JPanel:
  private val titleFont: Font = new Font("Courier", Font.BOLD, 16)
  private val constraint: GridBagConstraints = new GridBagConstraints()
  private val powerUpNameLabel: JLabel = new JLabel()
  private val powerUpPriceLabel: JLabel = new JLabel()
  private val DNAPointsLabel: JLabel = new JLabel("DNA Points collected: " + this.menuController.getCollectedDNAPoints)
  private val powerUpEffectLabel: JLabel = new JLabel()
  private val powerUpDescriptionLabel: JLabel = new JLabel()
  private val buyImage: Image = ImageIO.read(getClass.getResource("/images/buyPowerUp.png"))
  private val buyButton: JButton = new JButton()
  private var powerUpShowed: PowerUp = _
  private var powerUpsGridPanel: PowerUpsGridPanel = _
  this.setBackground(new Color(217, 217, 217))
  this.setBorder(new EmptyBorder(20, 300, 20, 300))
  this.setLayout(new GridBagLayout())
  this.constraint.fill = GridBagConstraints.HORIZONTAL
  this.powerUpNameLabel.setFont(this.titleFont)
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
  this.constraint.gridx = 1
  this.constraint.gridy = 4
  this.constraint.fill = GridBagConstraints.LINE_END
  this.constraint.anchor = GridBagConstraints.LINE_END
  this.add(buyButton, this.constraint)

  /**
   * @param powerUpsGridPanel : the reference of the panel that contains the power up grid
   */
  def setPowerUpsGridPanel(powerUpsGridPanel: PowerUpsGridPanel): Unit = this.powerUpsGridPanel = powerUpsGridPanel

  this.buyButton.addActionListener(_ => {
    this.menuController.purchasePowerUp(this.powerUpShowed.powerUpType)
    this.DNAPointsLabel.setText("DNA Points collected: " + this.menuController.getCollectedDNAPoints)
    this.buyButton.setEnabled(false)
    this.virusPanel.refreshVirusCharacteristics(this.menuController.getVirusConfiguration)
    this.powerUpsGridPanel.refreshPowerUpGridPanel()
  })

  /**
   * It refresh the panel updating all the power up's informations
   *
   * @param powerUp : the power up to be shown
   * @param arePrerequisiteSatisfied : true if the prerequisite for the purchasing are satisfied
   */
  def refreshPowerUpInformation(powerUp: PowerUp, arePrerequisiteSatisfied: Boolean): Unit =
    this.powerUpShowed = powerUp
    this.powerUpNameLabel.setText(powerUp.powerUpType.toString)
    this.powerUpPriceLabel.setText("Price: " + powerUp.powerUpType.price.toString)
    this.powerUpEffectLabel.setText(powerUp.powerUpType.information.effect)
    this.powerUpDescriptionLabel.setText(powerUp.powerUpType.information.description)
    this.buyButton.setEnabled(this.menuController.getCollectedDNAPoints >= powerUp.powerUpType.price && !powerUp.hasBeenBought && arePrerequisiteSatisfied)