package view.menu

import controller.MenuController

import java.awt.{Color, GridLayout}
import javax.swing.{JButton, JLabel, JPanel}
import model.powerUp.{PowerUp, PowerUpManager, PowerUpType}
import model.infection.{BasicVirus, ColdRegionsInfectivity, Virus, VirusConfiguration}
import model.dnapoints.DnaPoints.DnaPointsHandler

import java.awt.event.{ActionEvent, ActionListener}
import model.dnapoints.DnaPoints.Logic.EmptyLogic



class PowerUpsGridPanel(powerUpDetailsPanel: PowerUpDetailsPanel, menuController: MenuController) extends JPanel:
  this.setBackground(new Color(0,0,255))
  powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.ColdResistanceI).get, true)

  var powerUps: List[PowerUp] = menuController.getPowerUps


  val buttonPanel: JPanel = new JPanel()


  val numRows = 3
  val numCols = 5

  buttonPanel.setLayout(new GridLayout(numRows, numCols, 10, 10))
  var buttons: List[JButton] = List.empty[JButton]

  val btnColdResistanceI: JButton = new JButton("ColdResistanceI")
  btnColdResistanceI.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.ColdResistanceI).get, true))
  buttonPanel.add(btnColdResistanceI)

  val btnInfectedDrinkingWater: JButton = new JButton("InfectedDrinkingWater")
  btnInfectedDrinkingWater.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.InfectedDrinkingWater).get, true))
  buttonPanel.add(btnInfectedDrinkingWater)

  val btnHotResistanceI: JButton = new JButton("HotResistanceI")
  btnHotResistanceI.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.HotResistanceI).get, true))
  buttonPanel.add(btnHotResistanceI)

  val btnAlphaMutations: JButton = new JButton("AlphaMutations")
  btnAlphaMutations.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.AlphaMutations).get, true))
  buttonPanel.add(btnAlphaMutations)

  val btnMedicinesResistance: JButton = new JButton("MedicinesResistance")
  btnMedicinesResistance.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.MedicinesResistance).get, true))
  buttonPanel.add(btnMedicinesResistance)

  val btnColdResistanceII: JButton = new JButton("ColdResistanceII")
  btnColdResistanceII.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.ColdResistanceII).get, true))
  buttonPanel.add(btnColdResistanceII)

  val btnInfectionThroughAnimals: JButton = new JButton("InfectionThroughAnimals")
  btnInfectionThroughAnimals.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.InfectionThroughAnimals).get, true))
  buttonPanel.add(btnInfectionThroughAnimals)

  val btnHotResistanceII: JButton = new JButton("HotResistanceII")
  btnHotResistanceII.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.HotResistanceII).get, true))
  buttonPanel.add(btnHotResistanceII)

  val btnBetaMutations: JButton = new JButton("BetaMutations")
  btnBetaMutations.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.BetaMutations).get, true))
  buttonPanel.add(btnBetaMutations)

  val btnInfectionThroughRespiratoryTract: JButton = new JButton("InfectionThroughRespiratoryTract")
  btnInfectionThroughRespiratoryTract.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.InfectionThroughRespiratoryTract).get, true))
  buttonPanel.add(btnInfectionThroughRespiratoryTract)

  val btnAirportEnablement: JButton = new JButton("AirportEnablement")
  btnAirportEnablement.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.AirportEnablement).get, true))
  buttonPanel.add(btnAirportEnablement)

  val btnBacterialResistance: JButton = new JButton("BacterialResistance")
  btnBacterialResistance.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.BacterialResistance).get, true))
  buttonPanel.add(btnBacterialResistance)

  val btnOmegaMutations: JButton = new JButton("OmegaMutations")
  btnOmegaMutations.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.OmegaMutations).get, true))
  buttonPanel.add(btnOmegaMutations)

  val btnGammaMutations: JButton = new JButton("GammaMutations")
  btnGammaMutations.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.GammaMutations).get, true))
  buttonPanel.add(btnGammaMutations)

  val btnPortEnablement: JButton = new JButton("PortEnablement")
  btnPortEnablement.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.PortEnablement).get, true))
  buttonPanel.add(btnPortEnablement)

  this.add(buttonPanel)
  buttonPanel.getComponents.foreach(btn => buttons = buttons.appended(btn.asInstanceOf[javax.swing.JButton]))
  println(buttons.length)


