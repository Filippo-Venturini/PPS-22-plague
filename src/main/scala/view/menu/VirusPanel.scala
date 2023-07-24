package view.menu

import model.infection.VirusConfiguration

import java.awt.{Color, Font, GridLayout}
import javax.swing.border.EmptyBorder
import javax.swing.{JLabel, JPanel}

/**
 * Class that represent the panel that contains all the information about virus characteristics
 * 
 * @param virusConfiguration : the starting configuration of the virus
 */
class VirusPanel(var virusConfiguration: VirusConfiguration) extends JPanel:
  private val titleFont: Font = new Font("Courier", Font.BOLD, 16)
  private val firstEmptyLabel: JLabel = new JLabel()
  private val secondEmptyLabel: JLabel = new JLabel()
  private val virusNameLabel: JLabel = new JLabel("VIRUS: " + virusConfiguration.name)
  private val coldRegionsInfectivityLabel: JLabel = new JLabel("Cold Regions Infectivity: " + virusConfiguration.coldRegionsInfectivity)
  private val poorRegionsInfectivityLabel: JLabel = new JLabel("Poor Regions Infectivity: " + virusConfiguration.poorRegionsInfectivity)
  private val lowDensityRegionsInfectivityLabel: JLabel = new JLabel("Low Density Regions Infectivity: " + virusConfiguration.lowDensityRegionInfectivity)
  private val hotRegionsInfectivityLabel: JLabel = new JLabel("Hot Regions Infectivity: " + virusConfiguration.hotRegionsInfectivity)
  private val richRegionsInfectivityLabel: JLabel = new JLabel("Rich Regions Infectivity: " + virusConfiguration.richRegionsInfectivity)
  private val highDensityRegionsInfectivityLabel: JLabel = new JLabel("High Density Regions Infectivity: " + virusConfiguration.highDensityRegionsInfectivity)
  private val vaccineResistanceLabel: JLabel = new JLabel("Vaccine Resistance: " + virusConfiguration.vaccineResistance)
  private val portEnabledLabel: JLabel = new JLabel("Port Enabled: " + virusConfiguration.portEnabled)
  private val airPortEnabledLabel: JLabel = new JLabel("Airport Enabled: " + virusConfiguration.airportEnabled)

  this.setLayout(new GridLayout(0, 3))
  this.setBorder(new EmptyBorder(20, 300, 20, 200))
  this.setBackground(new Color(217, 217, 217))
  this.virusNameLabel.setFont(this.titleFont)

  addLabels(virusNameLabel, firstEmptyLabel, secondEmptyLabel, coldRegionsInfectivityLabel, poorRegionsInfectivityLabel,
    lowDensityRegionsInfectivityLabel, hotRegionsInfectivityLabel,richRegionsInfectivityLabel,
    highDensityRegionsInfectivityLabel,vaccineResistanceLabel,portEnabledLabel,airPortEnabledLabel)

  private def addLabels(labels: JLabel*): Unit = labels.foreach(l => this.add(l))

  /**
   * It refresh the information about the virus' characteristics
   * 
   * @param virusConfiguration : the actual virus configuration
   */
  def refreshVirusCharacteristics(virusConfiguration: VirusConfiguration): Unit =
    this.coldRegionsInfectivityLabel.setText("Cold Regions Infectivity: " + virusConfiguration.coldRegionsInfectivity.toString)
    this.poorRegionsInfectivityLabel.setText("Poor Regions Infectivity: " + virusConfiguration.poorRegionsInfectivity.toString)
    this.lowDensityRegionsInfectivityLabel.setText("Low Density Regions Infectivity: " + virusConfiguration.lowDensityRegionInfectivity.toString)
    this.hotRegionsInfectivityLabel.setText("Hot Regions Infectivity: " + virusConfiguration.hotRegionsInfectivity.toString)
    this.richRegionsInfectivityLabel.setText("Rich Regions Infectivity: " + virusConfiguration.richRegionsInfectivity.toString)
    this.highDensityRegionsInfectivityLabel.setText("High Density Regions Infectivity: " + virusConfiguration.highDensityRegionsInfectivity.toString)
    this.vaccineResistanceLabel.setText("Vaccine Resistance: " + virusConfiguration.vaccineResistance.toString)
    this.portEnabledLabel.setText("Port Enabled: " + virusConfiguration.portEnabled.toString)
    this.airPortEnabledLabel.setText("Airport Enabled: " + virusConfiguration.airportEnabled.toString)