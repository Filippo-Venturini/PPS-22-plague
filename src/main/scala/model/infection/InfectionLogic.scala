package model.infection
import model.world.Region

trait InfectionLogic:
  def region: Region
  def virus: Virus
  def compute(): Unit

class InternalInfectionLogic(override val region: Region,
                     override val virus: Virus) extends InfectionLogic:
  override def compute(): Unit = region.infectedAmount match
    case i if i > 0 => region.infectedAmount = region.infectedAmount + 1


class ExternalInfectionLogic(override val region: Region,
                             override val virus: Virus) extends InfectionLogic:
  override def compute(): Unit = ???
    /*val ratioOfInfectedPopulation: Double =
      region.getReachableRegions.filter((region, reachableMode) => region.infectedAmount > 0).size
    */

