package view.game

import controller.GameEngine
import view.game.RegionsView.{DecimalProgressBar, RefreshablePanel}

import java.awt.{Color, Dimension, Font}
import javax.swing.border.EmptyBorder
import javax.swing.{BoxLayout, JComponent, JLabel, JPanel}

/**
 * Class that represent the panel that shows the information about the general infection progression
 *
 * @param gameEngine a reference to the game engine for receiving the information to show
 */
class GeneralInfectionPanel(val gameEngine: GameEngine) extends RefreshablePanel:
  private val worldInfectionProgressBar: DecimalProgressBar = new DecimalProgressBar(new Color(35, 187, 197), new Color(215, 19, 19))
  private val infectedAmountLabel: JLabel = new JLabel()
  private val totalPopulationLabel: JLabel = new JLabel("World Population: " + String.format("%,d", this.gameEngine.getWorldPopulation))
  private val worldTitleLabel: JLabel = new JLabel("World")
  private val vaccineTitleLabel: JLabel = new JLabel("Vaccine")
  private val vaccineProgressBar: DecimalProgressBar = new DecimalProgressBar(new Color(35, 187, 197), new Color(58, 144, 213))
  private val daysLabel: JLabel = new JLabel("Day: ")
  private val titleFont: Font = new Font("Arial", Font.PLAIN, 30)
  this.vaccineProgressBar.setMaximum(100)
  this.worldTitleLabel.setFont(this.titleFont)
  this.vaccineTitleLabel.setFont(this.titleFont)
  this.worldInfectionProgressBar.setMaximum((this.gameEngine.getWorldPopulation / 10).toInt)
  this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))
  this.setBorder(new EmptyBorder(20, 150, 20, 150))
  this.addComponents(worldTitleLabel, daysLabel, totalPopulationLabel, infectedAmountLabel,
    worldInfectionProgressBar, vaccineTitleLabel, vaccineProgressBar)

  private def addComponents(components: JComponent*): Unit = components.foreach(c => this.add(c))

  override def refresh(): Unit =
    this.infectedAmountLabel.setText("Infected Amount: " + String.format("%,d",this.gameEngine.getWorldInfectedAmount))
    this.worldInfectionProgressBar.setValue((this.gameEngine.getWorldInfectedAmount / 10).toInt)
    this.vaccineProgressBar.setValue(this.gameEngine.getVaccineProgression.toInt)
    this.daysLabel.setText("Day: " + this.gameEngine.days)