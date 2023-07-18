package view.menu

import model.infection.VirusConfiguration

import java.awt.{Color, Font, GridLayout}
import javax.swing.border.EmptyBorder
import javax.swing.{JLabel, JPanel}

class VirusPanel(var virusConfiguration: VirusConfiguration) extends JPanel:
  val titleFont: Font = new Font("Courier", Font.BOLD, 16)
  this.setLayout(new GridLayout(0,3))
  this.setBorder(new EmptyBorder(20,300,20,10))
  this.setBackground(new Color(217,217,217))
  val firstEmptyLabel: JLabel = new JLabel()
  val secondEmptyLabel: JLabel = new JLabel()
  val virusNameLabel: JLabel = new JLabel("VIRUS: NAME")
  val coldRegionsInfectivityLabel: JLabel = new JLabel("Cold Regions Infectivity: " + virusConfiguration.coldRegionsInfectivity)
  val poorRegionsInfectivityLabel: JLabel = new JLabel("Poor Regions Infectivity: " + virusConfiguration.poorRegionsInfectivity)
  val lowDensityRegionsInfectivityLabel: JLabel = new JLabel("Low Density Regions Infectivity: " + virusConfiguration.lowDensityRegionInfectivity)
  val hotRegionsInfectivityLabel: JLabel = new JLabel("Hot Regions Infectivity: " + virusConfiguration.warmRegionsInfectivity)
  val richRegionsInfectivityLabel: JLabel = new JLabel("Rich Regions Infectivity: " + virusConfiguration.richRegionsInfectivity)
  val highDensityRegionsInfectivityLabel: JLabel = new JLabel("High Density Regions Infectivity: " + virusConfiguration.highDensityRegionsInfectivity)
  val vaccineResistanceLabel: JLabel = new JLabel("Vaccine Resistance: " + virusConfiguration.vaccineResistance)
  val portEnabledLabel: JLabel = new JLabel("Port Enabled: " + virusConfiguration.portEnabled)
  val airPortEnabledLabel: JLabel = new JLabel("Airport Enabled: " + virusConfiguration.airportEnabled)

  this.virusNameLabel.setFont(this.titleFont)

  addLabels(virusNameLabel, firstEmptyLabel, secondEmptyLabel, coldRegionsInfectivityLabel, poorRegionsInfectivityLabel,
    lowDensityRegionsInfectivityLabel, hotRegionsInfectivityLabel,richRegionsInfectivityLabel,
    highDensityRegionsInfectivityLabel,vaccineResistanceLabel,portEnabledLabel,airPortEnabledLabel)

  def addLabels(labels: JLabel*): Unit = labels.foreach(l => this.add(l))

  def refreshVirusCharacteristics(virusConfiguration: VirusConfiguration): Unit =
    this.coldRegionsInfectivityLabel.setText("Cold Regions Infectivity: " + virusConfiguration.coldRegionsInfectivity.toString)
    this.poorRegionsInfectivityLabel.setText("Poor Regions Infectivity: " + virusConfiguration.poorRegionsInfectivity.toString)
    this.lowDensityRegionsInfectivityLabel.setText("Low Density Regions Infectivity: " + virusConfiguration.lowDensityRegionInfectivity.toString)
    this.hotRegionsInfectivityLabel.setText("Hot Regions Infectivity: " + virusConfiguration.warmRegionsInfectivity.toString)
    this.richRegionsInfectivityLabel.setText("Rich Regions Infectivity: " + virusConfiguration.richRegionsInfectivity.toString)
    this.highDensityRegionsInfectivityLabel.setText("High Density Regions Infectivity: " + virusConfiguration.highDensityRegionsInfectivity.toString)
    this.vaccineResistanceLabel.setText("Vaccine Resistance: " + virusConfiguration.vaccineResistance.toString)
    this.portEnabledLabel.setText("Port Enabled: " + virusConfiguration.portEnabled.toString)
    this.airPortEnabledLabel.setText("Airport Enabled: " + virusConfiguration.airportEnabled.toString)