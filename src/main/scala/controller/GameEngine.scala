package controller

import model.world.RegionTypes.RegionConfiguration
import model.world.{BasicRegion, Region, World}
import model.world.Filters.given
val europeConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
val unitedStatesConfiguration: RegionConfiguration = RegionConfiguration("United States", 746000000, 9, 5, 8, 9, 8)

class GameEngine():
  val refreshTime: Int = 200
  val world: World = new World(List(new BasicRegion(europeConfiguration), new BasicRegion(unitedStatesConfiguration)))

  def start(): Void =
    //Load configurations
    gameLoop()

  def gameLoop(): Void =
    val startTime: Long = System.currentTimeMillis()
    println("loop")
    //Compute Internal Infection
    //Compute External Infection
    //Compute Vaccine
    Thread.sleep(refreshTime - (System.currentTimeMillis() - startTime))
    gameLoop()

  def getRegions(): List[Region] = this.world.getRegions
