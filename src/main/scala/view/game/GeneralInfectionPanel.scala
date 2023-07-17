package view.game

import controller.GameEngine
import view.game.RegionsView.{DecimalProgressBar, RefreshablePanel}

import java.awt.{Color, Font}
import javax.swing.{BoxLayout, JLabel, JPanel}

class GeneralInfectionPanel(val gameEngine: GameEngine) extends RefreshablePanel:
  this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))
  val worldInfectionProgressBar: DecimalProgressBar = new DecimalProgressBar(new Color(35, 187, 197), new Color(215, 19, 19))
  this.worldInfectionProgressBar.setMaximum((this.gameEngine.getWorldPopulation / 10).toInt)
  val infectedAmountLabel: JLabel = new JLabel()
  val totalPopulationLabel: JLabel = new JLabel("World Population: " + String.format("%,d", this.gameEngine.getWorldPopulation))
  val worldTitleLabel: JLabel = new JLabel("World")
  worldTitleLabel.setFont(new Font("Arial", Font.PLAIN, 30))
  this.add(worldTitleLabel)
  this.add(totalPopulationLabel)
  this.add(infectedAmountLabel)
  this.add(worldInfectionProgressBar)

  override def refresh(): Unit =
    this.infectedAmountLabel.setText("Infected Amount: " + String.format("%,d",this.gameEngine.getWorldInfectedAmount))
    this.worldInfectionProgressBar.setValue((this.gameEngine.getWorldInfectedAmount / 10).toInt)

