package controller

import model.GameModel
import model.infection.VirusConfiguration
import model.powerUp.{PowerUp, PowerUpType}
import model.world.Filters.given
import model.world.Region


class LauncherController(val gameModel: GameModel):

  def getAllRegions: List[Region] = gameModel.world.getRegions

  println("avvio controller")

