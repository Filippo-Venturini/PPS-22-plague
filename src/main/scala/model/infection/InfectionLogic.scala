package model.infection
import model.world.Region
import model.world.RegionTypes.ReachableMode

/**
 * Class that contains infection logic.
 * In particular the internal infection logic is for increase the population infected when there are already infected people inside in the region.
 * The external infection logic is for increase the population infected for a region when there is one reachable region with infected.
 */

trait InfectionLogic:
  def region: Region
  def virus: Virus
  def compute(region: Region, virus: Virus): Unit
  val infectionRatioIncreaseInside: Int = 1000000
  val infectionRatioIncreaseFromRoute: Double = 0.000001
  def infectedRatio(): Double
  /*
  val population: Population = regionConfiguration.population
  val richness: Richness = regionConfiguration.richness
  val climate: Climate = regionConfiguration.climate
  val bordersControl: BordersControl = regionConfiguration.bordersControl
  val globalization: Globalization = regionConfiguration.globalization
  val populationDensity: PopulationDensity = regionConfiguration.populationDensity
  */

class InternalInfectionLogic(override val region: Region,
                     override val virus: Virus) extends InfectionLogic:
  /**
   * Increase the infected amount for a specific factor
   */
  override def compute(region: Region, virus: Virus): Unit = region.infectedAmount match
    case i if i > 0 => region.infectedAmount = region.infectedAmount + (region.population / infectionRatioIncreaseInside)

  override def infectedRatio(): Double = ???


class ExternalInfectionLogic(override val region: Region,
                             override val virus: Virus) extends InfectionLogic:
  /**
   * Increase the infected amount for a specific factor
   */
  override def compute(region: Region, virus: Virus): Unit =
    if region.getReachableRegions.filter((region, reachableMode) => (region.infectedAmount.toFloat / region.population) > 0.5 &&
      (reachableMode == ReachableMode.Border) ||
      (reachableMode == ReachableMode.Airport && virus.airportEnabled) ||
      (reachableMode == ReachableMode.Port && virus.portEnabled)).size > 0
      then region.infectedAmount = region.infectedAmount + (region.population / (region.population * infectedRatio()).toInt)

  override def infectedRatio(): Double =
    infectionRatioIncreaseFromRoute / region.bordersControl