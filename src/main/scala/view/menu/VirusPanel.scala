package view.menu

import model.infection.VirusConfiguration

import java.awt.{Color, GridLayout}
import javax.swing.border.EmptyBorder
import javax.swing.{JLabel, JPanel}

class VirusPanel(var virusConfiguration: VirusConfiguration) extends JPanel:
  this.setLayout(new GridLayout(0,3))
  this.setBorder(new EmptyBorder(20,300,20,10))
  this.setBackground(new Color(217,217,217))
  this.add(new JLabel("VIRUS: NAME"))
  this.add(new JLabel())
  this.add(new JLabel())
  this.add(new JLabel("Cold Regions Infectivity: " + virusConfiguration.coldRegionsInfectivity))
  this.add(new JLabel("Poor Regions Infectivity: " + virusConfiguration.poorRegionsInfectivity))
  this.add(new JLabel("Low Density Regions Infectivity: " + virusConfiguration.lowDensityRegionInfectivity))
  this.add(new JLabel("Hot Regions Infectivity: " + virusConfiguration.warmRegionsInfectivity))
  this.add(new JLabel("Rich Regions Infectivity: " + virusConfiguration.richRegionsInfectivity))
  this.add(new JLabel("High Density Regions Infectivity: " + + virusConfiguration.highDensityRegionsInfectivity))
  this.add(new JLabel("Vaccine Resistance: " + virusConfiguration.vaccineResistance))
  this.add(new JLabel("Port Enabled: " + virusConfiguration.portEnabled))
  this.add(new JLabel("Airport Enabled: " + virusConfiguration.airportEnabled))
