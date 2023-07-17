package view.menu

import controller.MenuController

import java.awt.{BorderLayout, Color, Dimension, Graphics, GridLayout, Image}
import javax.swing.{ImageIcon, JButton, JLabel, JPanel}
import model.powerUp.{PowerUp, PowerUpManager, PowerUpType}
import model.infection.{BasicVirus, ColdRegionsInfectivity, Virus, VirusConfiguration}
import model.dnapoints.DnaPoints.DnaPointsHandler

import java.awt.event.{ActionEvent, ActionListener}
import model.dnapoints.DnaPoints.Logic.EmptyLogic

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.swing.border.EmptyBorder


case class ButtonPowerUp(button: JButton, powerUp: PowerUp)


class PowerUpsGridPanel(powerUpDetailsPanel: PowerUpDetailsPanel, menuController: MenuController) extends JPanel:
  this.setBorder(new EmptyBorder(30,30,30,30))
  powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.ColdResistanceI).get, true)
  val buttonPanel: JPanel = new JPanel()

  val numRows = 4
  val numCols = 4
  this.setLayout(new GridLayout(numRows, numCols, 50, 50))
  var buttons: List[ButtonPowerUp] = List.empty[ButtonPowerUp]


  val btnColdResistanceI: ButtonPowerUp = ButtonPowerUp(new JButton("ColdResistanceI"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpColdResistanceI: PowerUp = menuController.getPowerUp(PowerUpType.ColdResistanceI).get
  btnColdResistanceI.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpColdResistanceI, this.menuController.powerUpsAvailableForPurchase.contains(powerUpColdResistanceI)))
  btnColdResistanceI.button.setPreferredSize(new Dimension(50, 50))
  btnColdResistanceI.button.setMinimumSize(new Dimension(50, 50))
  this.add(btnColdResistanceI.button)
  buttons = buttons.appended(btnColdResistanceI)

  val btnInfectedDrinkingWater: ButtonPowerUp = ButtonPowerUp(new JButton("InfectedDrinkingWater"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpInfectedDrinkingWater: PowerUp = menuController.getPowerUp(PowerUpType.InfectedDrinkingWater).get
  btnInfectedDrinkingWater.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpInfectedDrinkingWater, this.menuController.powerUpsAvailableForPurchase.contains(powerUpInfectedDrinkingWater)))
  this.add(btnInfectedDrinkingWater.button)
  buttons = buttons.appended(btnInfectedDrinkingWater)

  val btnHotResistanceI: ButtonPowerUp = ButtonPowerUp(new JButton("HotResistanceI"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerHotResistanceI: PowerUp = menuController.getPowerUp(PowerUpType.HotResistanceI).get
  btnHotResistanceI.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerHotResistanceI, true))
  this.add(btnHotResistanceI.button)
  buttons = buttons.appended(btnHotResistanceI)

  val btnAlphaMutations: ButtonPowerUp = ButtonPowerUp(new JButton("AlphaMutations"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerAlphaMutations: PowerUp = menuController.getPowerUp(PowerUpType.AlphaMutations).get
  btnAlphaMutations.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerAlphaMutations, this.menuController.powerUpsAvailableForPurchase.contains(powerAlphaMutations)))
  this.add(btnAlphaMutations.button)
  buttons = buttons.appended(btnAlphaMutations)


  val btnColdResistanceII: ButtonPowerUp = ButtonPowerUp(new JButton("ColdResistanceII"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpColdResistanceII: PowerUp = menuController.getPowerUp(PowerUpType.ColdResistanceII).get
  btnColdResistanceII.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpColdResistanceII, this.menuController.powerUpsAvailableForPurchase.contains(powerUpColdResistanceII)))
  this.add(btnColdResistanceII.button)
  buttons = buttons.appended(btnColdResistanceII)

  val btnInfectionThroughAnimals: ButtonPowerUp = ButtonPowerUp(new JButton("InfectionThroughAnimals"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpInfectionThroughAnimals: PowerUp = menuController.getPowerUp(PowerUpType.InfectionThroughAnimals).get
  btnInfectionThroughAnimals.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpInfectionThroughAnimals, this.menuController.powerUpsAvailableForPurchase.contains(powerUpInfectionThroughAnimals)))
  this.add(btnInfectionThroughAnimals.button)
  buttons = buttons.appended(btnInfectionThroughAnimals)

  val btnHotResistanceII: ButtonPowerUp = ButtonPowerUp(new JButton("HotResistanceII"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpHotResistanceII: PowerUp = menuController.getPowerUp(PowerUpType.HotResistanceII).get
  btnHotResistanceII.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpHotResistanceII, this.menuController.powerUpsAvailableForPurchase.contains(powerUpHotResistanceII)))
  this.add(btnHotResistanceII.button)
  buttons = buttons.appended(btnHotResistanceII)

  val btnBetaMutations: ButtonPowerUp = ButtonPowerUp(new JButton("BetaMutations"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpBetaMutations: PowerUp = menuController.getPowerUp(PowerUpType.BetaMutations).get
  btnBetaMutations.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpBetaMutations, this.menuController.powerUpsAvailableForPurchase.contains(powerUpBetaMutations)))
  this.add(btnBetaMutations.button)
  buttons = buttons.appended(btnBetaMutations)


  val btnAirportEnablement: ButtonPowerUp = ButtonPowerUp(new JButton("AirportEnablement"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpAirportEnablement: PowerUp = menuController.getPowerUp(PowerUpType.AirportEnablement).get
  btnAirportEnablement.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpAirportEnablement, this.menuController.powerUpsAvailableForPurchase.contains(powerUpAirportEnablement)))
  this.add(btnAirportEnablement.button)
  buttons = buttons.appended(btnAirportEnablement)

  val btnBacterialResistance: ButtonPowerUp = ButtonPowerUp(new JButton("BacterialResistance"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpBacterialResistance: PowerUp = menuController.getPowerUp(PowerUpType.BacterialResistance).get
  btnBacterialResistance.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpBacterialResistance, this.menuController.powerUpsAvailableForPurchase.contains(powerUpBacterialResistance)))
  this.add(btnBacterialResistance.button)
  buttons = buttons.appended(btnBacterialResistance)

  val btnOmegaMutations: ButtonPowerUp = ButtonPowerUp(new JButton("OmegaMutations"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpOmegaMutations: PowerUp = menuController.getPowerUp(PowerUpType.OmegaMutations).get
  btnOmegaMutations.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpOmegaMutations, this.menuController.powerUpsAvailableForPurchase.contains(powerUpOmegaMutations)))
  this.add(btnOmegaMutations.button)
  buttons = buttons.appended(btnOmegaMutations)

  val btnGammaMutations: ButtonPowerUp = ButtonPowerUp(new JButton("GammaMutations"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpGammaMutations: PowerUp = menuController.getPowerUp(PowerUpType.GammaMutations).get
  btnGammaMutations.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpGammaMutations, this.menuController.powerUpsAvailableForPurchase.contains(powerUpGammaMutations)))
  this.add(btnGammaMutations.button)
  buttons = buttons.appended(btnGammaMutations)


  val btnMedicinesResistance: ButtonPowerUp = ButtonPowerUp(new JButton("MedicinesResistance"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpMedicinesResistance: PowerUp = menuController.getPowerUp(PowerUpType.MedicinesResistance).get
  btnMedicinesResistance.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpMedicinesResistance, this.menuController.powerUpsAvailableForPurchase.contains(powerUpMedicinesResistance)))
  this.add(btnMedicinesResistance.button)
  buttons = buttons.appended(btnMedicinesResistance)

  val btnInfectionThroughRespiratoryTract: ButtonPowerUp = ButtonPowerUp(new JButton("InfectionThroughRespiratoryTract"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpInfectionThroughRespiratoryTract: PowerUp = menuController.getPowerUp(PowerUpType.InfectionThroughRespiratoryTract).get
  btnInfectionThroughRespiratoryTract.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpInfectionThroughRespiratoryTract, this.menuController.powerUpsAvailableForPurchase.contains(powerUpInfectionThroughRespiratoryTract)))
  this.add(btnInfectionThroughRespiratoryTract.button)
  buttons = buttons.appended(btnInfectionThroughRespiratoryTract)

  val btnPortEnablement: ButtonPowerUp = ButtonPowerUp(new JButton("PortEnablement"), menuController.getPowerUp(PowerUpType.ColdResistanceI).get)
  val powerUpPortEnablement: PowerUp = menuController.getPowerUp(PowerUpType.PortEnablement).get
  btnPortEnablement.button.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpPortEnablement, this.menuController.powerUpsAvailableForPurchase.contains(powerUpPortEnablement)))
  this.add(btnPortEnablement.button)
  buttons = buttons.appended(btnPortEnablement)

  this.setOpaque(false)
  //this.add(buttonPanel)
  //buttonPanel.getComponents.foreach(btn => buttons = buttons.appended(btn.asInstanceOf[javax.swing.JButton]))

  this.refreshPowerUpGridPanel()
  //this.repaint()
  /*
  val mapImage: BufferedImage = ImageIO.read(getClass().getResource("/map.png"))
*/
  override def paintComponent(g: Graphics): Unit =
    println("ciao")
    super.paintComponent(g)
    drawLineBetweenPowerUpsButton(btnColdResistanceI, btnColdResistanceII, g)
    drawLineBetweenPowerUpsButton(btnHotResistanceI, btnHotResistanceII, g)
    drawLineBetweenPowerUpsButton(btnInfectedDrinkingWater, btnInfectionThroughAnimals, g)
    drawLineBetweenPowerUpsButton(btnAlphaMutations, btnBetaMutations, g)
    drawLineBetweenPowerUpsButton(btnColdResistanceII, btnBacterialResistance, g)
    drawLineBetweenPowerUpsButton(btnHotResistanceII, btnBacterialResistance, g)
    drawLineBetweenPowerUpsButton(btnBetaMutations, btnGammaMutations, g)
    drawLineBetweenPowerUpsButton(btnOmegaMutations, btnGammaMutations, g)
    drawLineBetweenPowerUpsButton(btnMedicinesResistance, btnInfectionThroughRespiratoryTract, g)


  //g.drawImage(mapImage, 0, 0, this.getWidth, this.getHeight, null)

  def drawLineBetweenPowerUpsButton(firstPowerUpButton: ButtonPowerUp, secondPowerUpButton: ButtonPowerUp, g: Graphics): Unit =
    g.drawLine(firstPowerUpButton.button.getLocation().x + firstPowerUpButton.button.getWidth / 2, firstPowerUpButton.button.getLocation().y + firstPowerUpButton.button.getHeight / 2,
      secondPowerUpButton.button.getLocation().x + secondPowerUpButton.button.getWidth / 2, secondPowerUpButton.button.getLocation().y + secondPowerUpButton.button.getHeight / 2)

  def refreshPowerUpGridPanel(): Unit =
    buttons.foreach(btn => {
      btn.button.setBackground(new Color(196, 29, 29))
      btn.button.setOpaque(true)
      btn.button.setBorderPainted(false)
    })


