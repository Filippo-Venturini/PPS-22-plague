package model.world

import model.world.Filters.RegionFilter

object Filters:
  type RegionFilter = Region => Boolean

  given RegionFilter = _ => true
  val infectedRegions: RegionFilter = region => region.infectedAmount > 0
  val totallyInfectedRegions: RegionFilter = region => region.infectedAmount == region.population

class World (private val regions: List[Region]):

  def getRegions(using filter: RegionFilter): List[Region] =
    regions.filter(filter)
