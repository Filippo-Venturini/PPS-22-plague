package model.world

import model.world.Filters.{RegionFilter, infectedRegions}

/**
 * Encapsulate all the available filters for the world
 */
object Filters:
  type RegionFilter = Region => Boolean

  /**
   * @return the filter for obtaining all the regions (default)
   */
  given RegionFilter = _ => true

  /**
   * Filter for obtaining only the infected regions
   */
  val infectedRegions: RegionFilter = region => region.infectedAmount > 0

  /**
   * Filter for obtaining only the regions not infected
   */
  val notInfectedRegions: RegionFilter = region => region.infectedAmount == 0

  /**
   * Filter for obtaining all the regions completely infected
   */
  val totallyInfectedRegions: RegionFilter = region => region.infectedAmount == region.population

/**
 * A class that represent the world
 *
 * @param regions all the regions of the world
 */
class World (private val regions: List[Region]):

  /**
   *
   * @param filter the filter to apply for obtaining the regions deired
   * @return the collection of the regions filtered
   */
  def getRegions(using filter: RegionFilter): List[Region] = regions.filter(filter)
