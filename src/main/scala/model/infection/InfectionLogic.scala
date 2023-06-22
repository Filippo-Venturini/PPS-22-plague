package model.infection
import model.world.Region

trait InfectionLogic:
  def region: Region
  def virus: VirusStructure
  def compute(): Unit

class InternalInfectionLogic(override val region: Region,
                     override val virus: VirusStructure) extends InfectionLogic:
  override def compute(): Unit = region.infectedAmount match
    case i if i > 0 => region.infectedAmount = region.infectedAmount + 1


class ExternalInfectionLogic(override val region: Region,
                             override val virus: VirusStructure) extends InfectionLogic:
  override def compute(): Unit = region.infectedAmount match
    case i if i > 0 => region.infectedAmount = region.infectedAmount + 1
