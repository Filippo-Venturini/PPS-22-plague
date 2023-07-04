package controller

import model.world.RegionTypes.RegionConfiguration
import model.world.{BasicRegion, Region, World}
import model.world.Filters.given
import model.configuration.Loader.ConfigurationsLoader.given
import model.configuration.Loader.{ConfigurationsLoader, RegionFile, virusFile}
import model.configuration.Loader
import model.infection.Virus
val europeConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
val unitedStatesConfiguration: RegionConfiguration = RegionConfiguration("United States", 746000000, 9, 5, 8, 9, 8)

class GameEngine():
  private val refreshTime: Int = 200
  private val world: World = new World(ConfigurationsLoader.load(Loader.regionFile))
  private val virus: Virus = ConfigurationsLoader.load(virusFile)(0)
  
  def start(): Void =
    gameLoop()

  private def gameLoop(): Void =
    val startTime: Long = System.currentTimeMillis()
    println("loop")
    //Compute Internal Infection
    //Compute External Infection
    //Compute Vaccine
    Thread.sleep(refreshTime - (System.currentTimeMillis() - startTime))
    gameLoop()

  def getRegions(): List[Region] = this.world.getRegions
