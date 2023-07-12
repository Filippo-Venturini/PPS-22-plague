package controller

import model.GameModel
import model.infection.VirusConfiguration

class MenuController(val gameModel: GameModel):

  def getVirusConfiguration(): VirusConfiguration = this.gameModel.virus.getActualConfiguration
   
