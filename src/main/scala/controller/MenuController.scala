package controller

import model.GameModel
import model.infection.VirusConfiguration
import model.powerUp.{PowerUp, PowerUpType}

class MenuController(val gameModel: GameModel):

  def getVirusConfiguration(): VirusConfiguration = this.gameModel.virus.getActualConfiguration
  def getPowerUps: List[PowerUp] = this.gameModel.powerUpManager.getAllPowerUps()
  def getPowerUp(powerUpType: PowerUpType): Option[PowerUp] = this.gameModel.powerUpManager.getPowerUp(powerUpType)
  def getCollectedDNAPoints(): Int = this.gameModel.dnaPointsHandler.collectedPoints
  def purchasePowerUp(powerUpType: PowerUpType): Unit = this.gameModel.powerUpManager.purchasePowerUp(powerUpType)
  this.gameModel.dnaPointsHandler.collectedPoints = 10
   
