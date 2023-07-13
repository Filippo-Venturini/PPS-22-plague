package controller

import model.GameModel
import model.infection.VirusConfiguration
import model.powerUp.{PowerUp, PowerUpType}

/**
 * Class that represent the controller that bind the Game Model with the Menu View
 *
 * @param gameModel the model of the game
 */
class MenuController(val gameModel: GameModel):

  /**
   * @return the actual virus configuration
   */
  def getVirusConfiguration(): VirusConfiguration = this.gameModel.virus.getActualConfiguration

  /**
   * @return the complete list of all the power-ups
   */
  def getPowerUps: List[PowerUp] = this.gameModel.powerUpManager.getAllPowerUps()

  /**
   * @param powerUpType the type of the power up requested
   * @return the power up that match with the type passed by input
   */
  def getPowerUp(powerUpType: PowerUpType): Option[PowerUp] = this.gameModel.powerUpManager.getPowerUp(powerUpType)

  /**
   * @return the total amount of collected DNA Points
   */
  def getCollectedDNAPoints(): Int = this.gameModel.dnaPointsHandler.collectedPoints

  /**
   * @param powerUpType the type of the power up to purchase
   */
  def purchasePowerUp(powerUpType: PowerUpType): Unit = this.gameModel.powerUpManager.purchasePowerUp(powerUpType)
  this.gameModel.dnaPointsHandler.collectedPoints = 10
   
