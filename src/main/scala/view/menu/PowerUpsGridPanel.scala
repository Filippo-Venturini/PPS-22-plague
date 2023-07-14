package view.menu

import controller.MenuController

import java.awt.{BorderLayout, Color, Dimension, GridLayout, Image, Graphics}
import javax.swing.{ImageIcon, JButton, JLabel, JPanel}
import model.powerUp.{PowerUp, PowerUpManager, PowerUpType}
import model.infection.{BasicVirus, ColdRegionsInfectivity, Virus, VirusConfiguration}
import model.dnapoints.DnaPoints.DnaPointsHandler

import java.awt.event.{ActionEvent, ActionListener}
import model.dnapoints.DnaPoints.Logic.EmptyLogic

import java.awt.image.BufferedImage
import javax.imageio.ImageIO



class PowerUpsGridPanel(powerUpDetailsPanel: PowerUpDetailsPanel, menuController: MenuController) extends JPanel:
  powerUpDetailsPanel.refreshPowerUpInformation(menuController.getPowerUp(PowerUpType.ColdResistanceI).get, true)
  val buttonPanel: JPanel = new JPanel()

  val numRows = 3
  val numCols = 5
  buttonPanel.setLayout(new GridLayout(numRows, numCols, 50, 50))
  var buttons: List[JButton] = List.empty[JButton]

  val btnColdResistanceI: JButton = new JButton("ColdResistanceI")
  val powerUpColdResistanceI: PowerUp = menuController.getPowerUp(PowerUpType.ColdResistanceI).get
  btnColdResistanceI.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpColdResistanceI, this.menuController.powerUpsAvailableForPurchase.contains(powerUpColdResistanceI)))
  buttonPanel.add(btnColdResistanceI)

  val btnInfectedDrinkingWater: JButton = new JButton("InfectedDrinkingWater")
  val powerUpInfectedDrinkingWater: PowerUp = menuController.getPowerUp(PowerUpType.InfectedDrinkingWater).get
  btnInfectedDrinkingWater.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpInfectedDrinkingWater, this.menuController.powerUpsAvailableForPurchase.contains(powerUpInfectedDrinkingWater)))
  buttonPanel.add(btnInfectedDrinkingWater)

  val btnHotResistanceI: JButton = new JButton("HotResistanceI")
  val powerHotResistanceI: PowerUp = menuController.getPowerUp(PowerUpType.HotResistanceI).get
  btnHotResistanceI.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerHotResistanceI, true))
  btnHotResistanceI.setIcon(new ImageIcon(ImageIO.read(getClass.getResource("/hot.jpeg"))))
  btnHotResistanceI.setBackground(new Color(255, 255, 255))
  //btnHotResistanceI.setPreferredSize(new Dimension(100, 100))

  buttonPanel.add(btnHotResistanceI)

  val btnAlphaMutations: JButton = new JButton("AlphaMutations")
  val powerAlphaMutations: PowerUp = menuController.getPowerUp(PowerUpType.AlphaMutations).get
  btnAlphaMutations.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerAlphaMutations, this.menuController.powerUpsAvailableForPurchase.contains(powerAlphaMutations)))
  buttonPanel.add(btnAlphaMutations)

  val btnMedicinesResistance: JButton = new JButton("MedicinesResistance")
  val powerUpMedicinesResistance: PowerUp = menuController.getPowerUp(PowerUpType.MedicinesResistance).get
  btnMedicinesResistance.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpMedicinesResistance, this.menuController.powerUpsAvailableForPurchase.contains(powerUpMedicinesResistance)))
  buttonPanel.add(btnMedicinesResistance)

  val btnColdResistanceII: JButton = new JButton("ColdResistanceII")
  val powerUpColdResistanceII: PowerUp = menuController.getPowerUp(PowerUpType.ColdResistanceII).get
  btnColdResistanceII.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpColdResistanceII, this.menuController.powerUpsAvailableForPurchase.contains(powerUpColdResistanceII)))
  buttonPanel.add(btnColdResistanceII)

  val btnInfectionThroughAnimals: JButton = new JButton("InfectionThroughAnimals")
  val powerUpInfectionThroughAnimals: PowerUp = menuController.getPowerUp(PowerUpType.InfectionThroughAnimals).get
  btnInfectionThroughAnimals.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpInfectionThroughAnimals, this.menuController.powerUpsAvailableForPurchase.contains(powerUpInfectionThroughAnimals)))
  buttonPanel.add(btnInfectionThroughAnimals)

  val btnHotResistanceII: JButton = new JButton("HotResistanceII")
  val powerUpHotResistanceII: PowerUp = menuController.getPowerUp(PowerUpType.HotResistanceII).get
  btnHotResistanceII.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpHotResistanceII, this.menuController.powerUpsAvailableForPurchase.contains(powerUpHotResistanceII)))

  btnHotResistanceII.setIcon(new ImageIcon(ImageIO.read(getClass.getResource("/hotPlusG.jpeg"))))
  btnHotResistanceII.setBackground(new Color(155, 155, 155))
  //btnHotResistanceII.setPreferredSize(new Dimension(100, 100))

  buttonPanel.add(btnHotResistanceII)

  val btnBetaMutations: JButton = new JButton("BetaMutations")
  val powerUpBetaMutations: PowerUp = menuController.getPowerUp(PowerUpType.BetaMutations).get
  btnBetaMutations.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpBetaMutations, this.menuController.powerUpsAvailableForPurchase.contains(powerUpBetaMutations)))
  buttonPanel.add(btnBetaMutations)

  val btnInfectionThroughRespiratoryTract: JButton = new JButton("InfectionThroughRespiratoryTract")
  val powerUpInfectionThroughRespiratoryTract: PowerUp = menuController.getPowerUp(PowerUpType.InfectionThroughRespiratoryTract).get
  btnInfectionThroughRespiratoryTract.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpInfectionThroughRespiratoryTract, this.menuController.powerUpsAvailableForPurchase.contains(powerUpInfectionThroughRespiratoryTract)))
  buttonPanel.add(btnInfectionThroughRespiratoryTract)

  val btnAirportEnablement: JButton = new JButton("AirportEnablement")
  val powerUpAirportEnablement: PowerUp = menuController.getPowerUp(PowerUpType.AirportEnablement).get
  btnAirportEnablement.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpAirportEnablement, this.menuController.powerUpsAvailableForPurchase.contains(powerUpAirportEnablement)))
  buttonPanel.add(btnAirportEnablement)

  val btnBacterialResistance: JButton = new JButton("BacterialResistance")
  val powerUpBacterialResistance: PowerUp = menuController.getPowerUp(PowerUpType.BacterialResistance).get
  btnBacterialResistance.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpBacterialResistance, this.menuController.powerUpsAvailableForPurchase.contains(powerUpBacterialResistance)))
  buttonPanel.add(btnBacterialResistance)

  val btnOmegaMutations: JButton = new JButton("OmegaMutations")
  val powerUpOmegaMutations: PowerUp = menuController.getPowerUp(PowerUpType.OmegaMutations).get
  btnOmegaMutations.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpOmegaMutations, this.menuController.powerUpsAvailableForPurchase.contains(powerUpOmegaMutations)))
  buttonPanel.add(btnOmegaMutations)

  val btnGammaMutations: JButton = new JButton("GammaMutations")
  val powerUpGammaMutations: PowerUp = menuController.getPowerUp(PowerUpType.GammaMutations).get
  btnGammaMutations.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpGammaMutations, this.menuController.powerUpsAvailableForPurchase.contains(powerUpGammaMutations)))
  buttonPanel.add(btnGammaMutations)


  val btnPortEnablement: JButton = new JButton("ciao")
  val powerUpPortEnablement: PowerUp = menuController.getPowerUp(PowerUpType.PortEnablement).get
  btnPortEnablement.addActionListener((e: ActionEvent) => powerUpDetailsPanel.refreshPowerUpInformation(powerUpPortEnablement, this.menuController.powerUpsAvailableForPurchase.contains(powerUpPortEnablement)))
  btnPortEnablement.setBackground(new Color(255, 0, 0))
  btnPortEnablement.setOpaque(true)
  buttonPanel.add(btnPortEnablement)

  /*
  val mapImage: BufferedImage = ImageIO.read(getClass().getResource("/map.png"))
*/
  override def paintComponent(g: Graphics): Unit =
    super.paintComponent(g)
    g.drawLine(btnHotResistanceI.getX, btnHotResistanceI.getY + btnHotResistanceI.getHeight / 2,
      btnHotResistanceII.getX, btnHotResistanceII.getY + btnHotResistanceII.getHeight / 2)
    g.drawLine(btnAlphaMutations.getX, btnAlphaMutations.getY,
      btnBetaMutations.getX, btnBetaMutations.getY)
    g.drawLine(btnOmegaMutations.getX, btnOmegaMutations.getY,
      btnGammaMutations.getX, btnGammaMutations.getY)
    //g.drawImage(mapImage, 0, 0, this.getWidth, this.getHeight, null)


  buttonPanel.setOpaque(false)
  this.add(buttonPanel)
  buttonPanel.getComponents.foreach(btn => buttons = buttons.appended(btn.asInstanceOf[javax.swing.JButton]))


  //TODO powerUpsAvailableForPurchase NON funziona

  /*
  println(this.menuController.powerUpsAvailableForPurchase.contains(powerUpColdResistanceI))
  println(this.menuController.powerUpsAvailableForPurchase.contains(powerUpColdResistanceII))
  println(this.menuController.powerUpsAvailableForPurchase.length)
  */
  //TODO cambiare il background di un bottone sui dispositivi non windws è un gran casino
  def refreshPowerUpGridPanel(): Unit =
    buttons.foreach(btn => btn.setBackground(new Color(0,255,0)))

  //this.refreshPowerUpGridPanel()
