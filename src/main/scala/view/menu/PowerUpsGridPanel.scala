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
  val powerUpColdResistanceI = menuController.getPowerUp(PowerUpType.ColdResistanceI).get
  btnColdResistanceI.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpColdResistanceI, this.menuController.powerUpsAvailableForPurchase.contains(powerUpColdResistanceI)))
  buttonPanel.add(btnColdResistanceI)

  val btnInfectedDrinkingWater: JButton = new JButton("InfectedDrinkingWater")
  val powerUpInfectedDrinkingWater = menuController.getPowerUp(PowerUpType.InfectedDrinkingWater).get
  btnInfectedDrinkingWater.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpInfectedDrinkingWater, this.menuController.powerUpsAvailableForPurchase.contains(powerUpInfectedDrinkingWater)))
  buttonPanel.add(btnInfectedDrinkingWater)

  val btnHotResistanceI: JButton = new JButton("HotResistanceI")
  val powerHotResistanceI = menuController.getPowerUp(PowerUpType.HotResistanceI).get
  btnHotResistanceI.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerHotResistanceI, this.menuController.powerUpsAvailableForPurchase.contains(powerHotResistanceI)))
  buttonPanel.add(btnHotResistanceI)

  val btnAlphaMutations: JButton = new JButton("AlphaMutations")
  val powerAlphaMutations = menuController.getPowerUp(PowerUpType.AlphaMutations).get
  btnAlphaMutations.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerAlphaMutations, this.menuController.powerUpsAvailableForPurchase.contains(powerAlphaMutations)))
  buttonPanel.add(btnAlphaMutations)

  val btnMedicinesResistance: JButton = new JButton("MedicinesResistance")
  val powerUpMedicinesResistance = menuController.getPowerUp(PowerUpType.MedicinesResistance).get
  btnMedicinesResistance.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpMedicinesResistance, this.menuController.powerUpsAvailableForPurchase.contains(powerUpMedicinesResistance)))
  buttonPanel.add(btnMedicinesResistance)

  val btnColdResistanceII: JButton = new JButton("ColdResistanceII")
  val powerUpColdResistanceII = menuController.getPowerUp(PowerUpType.ColdResistanceII).get
  btnColdResistanceII.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpColdResistanceII, this.menuController.powerUpsAvailableForPurchase.contains(powerUpColdResistanceII)))
  buttonPanel.add(btnColdResistanceII)

  val btnInfectionThroughAnimals: JButton = new JButton("InfectionThroughAnimals")
  val powerUpInfectionThroughAnimals = menuController.getPowerUp(PowerUpType.InfectionThroughAnimals).get
  btnInfectionThroughAnimals.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpInfectionThroughAnimals, this.menuController.powerUpsAvailableForPurchase.contains(powerUpInfectionThroughAnimals)))
  buttonPanel.add(btnInfectionThroughAnimals)

  val btnHotResistanceII: JButton = new JButton("HotResistanceII")
  val powerUpHotResistanceII = menuController.getPowerUp(PowerUpType.HotResistanceII).get
  btnHotResistanceII.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpHotResistanceII, this.menuController.powerUpsAvailableForPurchase.contains(powerUpHotResistanceII)))
  buttonPanel.add(btnHotResistanceII)

  val btnBetaMutations: JButton = new JButton("BetaMutations")
  val powerUpBetaMutations = menuController.getPowerUp(PowerUpType.BetaMutations).get
  btnBetaMutations.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpBetaMutations, this.menuController.powerUpsAvailableForPurchase.contains(powerUpBetaMutations)))
  buttonPanel.add(btnBetaMutations)

  val btnInfectionThroughRespiratoryTract: JButton = new JButton("InfectionThroughRespiratoryTract")
  val powerUpInfectionThroughRespiratoryTract = menuController.getPowerUp(PowerUpType.InfectionThroughRespiratoryTract).get
  btnInfectionThroughRespiratoryTract.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpInfectionThroughRespiratoryTract, this.menuController.powerUpsAvailableForPurchase.contains(powerUpInfectionThroughRespiratoryTract)))
  buttonPanel.add(btnInfectionThroughRespiratoryTract)

  val btnAirportEnablement: JButton = new JButton("AirportEnablement")
  val powerUpAirportEnablement = menuController.getPowerUp(PowerUpType.AirportEnablement).get
  btnAirportEnablement.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpAirportEnablement, this.menuController.powerUpsAvailableForPurchase.contains(powerUpAirportEnablement)))
  buttonPanel.add(btnAirportEnablement)

  val btnBacterialResistance: JButton = new JButton("BacterialResistance")
  val powerUpBacterialResistance = menuController.getPowerUp(PowerUpType.BacterialResistance).get
  btnBacterialResistance.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpBacterialResistance, this.menuController.powerUpsAvailableForPurchase.contains(powerUpBacterialResistance)))
  buttonPanel.add(btnBacterialResistance)

  val btnOmegaMutations: JButton = new JButton("OmegaMutations")
  val powerUpOmegaMutations = menuController.getPowerUp(PowerUpType.OmegaMutations).get
  btnOmegaMutations.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpOmegaMutations, this.menuController.powerUpsAvailableForPurchase.contains(powerUpOmegaMutations)))
  buttonPanel.add(btnOmegaMutations)

  val btnGammaMutations: JButton = new JButton("GammaMutations")
  val powerUpGammaMutations = menuController.getPowerUp(PowerUpType.GammaMutations).get
  btnGammaMutations.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpGammaMutations, this.menuController.powerUpsAvailableForPurchase.contains(powerUpGammaMutations)))
  buttonPanel.add(btnGammaMutations)

  val btnPortEnablement: JButton = new JButton("PortEnablement")
  val powerUpPortEnablement = menuController.getPowerUp(PowerUpType.PortEnablement).get
  btnPortEnablement.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpPortEnablement, this.menuController.powerUpsAvailableForPurchase.contains(powerUpPortEnablement)))
  buttonPanel.add(btnPortEnablement)

  this.add(buttonPanel)
  buttonPanel.getComponents.foreach(btn => buttons = buttons.appended(btn.asInstanceOf[javax.swing.JButton]))
  println(buttons.length)


