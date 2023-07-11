package view.menu

import java.awt.{Color, GridLayout}
import javax.swing.{JButton, JLabel, JPanel}
import model.powerUp.{PowerUpManager, PowerUpType}
import model.infection.{BasicVirus, ColdRegionsInfectivity, Virus, VirusConfiguration}
import model.dnapoints.DnaPoints.DnaPointsHandler

import java.awt.event.{ActionEvent, ActionListener}
import model.dnapoints.DnaPoints.Logic.EmptyLogic



class PowerUpsGridPanel(powerUpDetailsPanel: PowerUpDetailsPanel) extends JPanel:
  this.setBackground(new Color(0,0,255))

  var powerUps: List[PowerUpType] = List.empty[PowerUpType]

  PowerUpType.values.foreach(powerUpsType => powerUps = powerUps.appended(powerUpsType))


  val buttonPanel: JPanel = new JPanel()


  val numRows = 3
  val numCols = 5

  buttonPanel.setLayout(new GridLayout(numRows, numCols, 10, 10))
  var buttons: List[JButton] = List.empty[JButton]

  val btnColdResistanceI: JButton = new JButton("ColdResistanceI")
  btnColdResistanceI.addActionListener((e: ActionEvent) => println(btnColdResistanceI.getText))
  buttonPanel.add(btnColdResistanceI)

  val btnInfectedDrinkingWater: JButton = new JButton("InfectedDrinkingWater")
  btnInfectedDrinkingWater.addActionListener((e: ActionEvent) => println(btnInfectedDrinkingWater.getText))
  buttonPanel.add(btnInfectedDrinkingWater)

  val btnHotResistanceI: JButton = new JButton("HotResistanceI")
  btnHotResistanceI.addActionListener((e: ActionEvent) => println(btnHotResistanceI.getText))
  buttonPanel.add(btnHotResistanceI)

  val btnAlphaMutations: JButton = new JButton("AlphaMutations")
  btnAlphaMutations.addActionListener((e: ActionEvent) => println(btnAlphaMutations.getText))
  buttonPanel.add(btnAlphaMutations)

  val btnMedicinesResistance: JButton = new JButton("MedicinesResistance")
  btnMedicinesResistance.addActionListener((e: ActionEvent) => println(btnMedicinesResistance.getText))
  buttonPanel.add(btnMedicinesResistance)

  val btnColdResistanceII: JButton = new JButton("ColdResistanceII")
  btnColdResistanceII.addActionListener((e: ActionEvent) => println(btnColdResistanceII.getText))
  buttonPanel.add(btnColdResistanceII)

  val btnInfectionThroughAnimals: JButton = new JButton("InfectionThroughAnimals")
  btnInfectionThroughAnimals.addActionListener((e: ActionEvent) => println(btnInfectionThroughAnimals.getText))
  buttonPanel.add(btnInfectionThroughAnimals)

  val btnHotResistanceII: JButton = new JButton("HotResistanceII")
  btnHotResistanceII.addActionListener((e: ActionEvent) => println(btnHotResistanceII.getText))
  buttonPanel.add(btnHotResistanceII)

  val btnBetaMutations: JButton = new JButton("BetaMutations")
  btnBetaMutations.addActionListener((e: ActionEvent) => println(btnBetaMutations.getText))
  buttonPanel.add(btnBetaMutations)

  val btnInfectionThroughRespiratoryTract: JButton = new JButton("InfectionThroughRespiratoryTract")
  btnInfectionThroughRespiratoryTract.addActionListener((e: ActionEvent) => println(btnInfectionThroughRespiratoryTract.getText))
  buttonPanel.add(btnInfectionThroughRespiratoryTract)

  val btnAirportEnablement: JButton = new JButton("AirportEnablement")
  btnAirportEnablement.addActionListener((e: ActionEvent) => println(btnAirportEnablement.getText))
  buttonPanel.add(btnAirportEnablement)

  val btnBacterialResistance: JButton = new JButton("BacterialResistance")
  btnBacterialResistance.addActionListener((e: ActionEvent) => println(btnBacterialResistance.getText))
  buttonPanel.add(btnBacterialResistance)

  val btnOmegaMutations: JButton = new JButton("OmegaMutations")
  btnOmegaMutations.addActionListener((e: ActionEvent) => println(btnOmegaMutations.getText))
  buttonPanel.add(btnOmegaMutations)

  val btnGammaMutations: JButton = new JButton("GammaMutations")
  btnGammaMutations.addActionListener((e: ActionEvent) => println(btnGammaMutations.getText))
  buttonPanel.add(btnGammaMutations)

  val btnPortEnablement: JButton = new JButton("PortEnablement")
  btnPortEnablement.addActionListener((e: ActionEvent) => println(btnPortEnablement.getText))
  buttonPanel.add(btnPortEnablement)

  this.add(buttonPanel)
  buttonPanel.getComponents.foreach(btn => buttons = buttons.appended(btn.asInstanceOf[javax.swing.JButton]))
  println(buttons.length)

//TODO powerUpManager ottenerlo come riferimento
  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 0, 0, 0, 0, 0, 0, 0, false, false)
  val virus: Virus = new BasicVirus(testVirusConfiguration)
  var dnaPointsHandler = DnaPointsHandler(EmptyLogic())
  var powerUpManager: PowerUpManager = new PowerUpManager(this.virus, dnaPointsHandler)
  //powerUpManager.getAllPowerUps().foreach(p => println(p.powerUpType))





