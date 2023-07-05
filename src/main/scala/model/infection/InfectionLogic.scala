package model.infection
import model.world.Region
import model.world.RegionTypes.ReachableMode

/**
 * Class that contains infection logic.
 * In particular the internal infection logic is for increase the population infected when there are already infected people inside in the region.
 * The external infection logic is for increase the population infected for a region when there is one reachable region with infected.
 */

trait InfectionLogic:
  def compute(region: Region, virus: Virus): Unit
  val infectionRatioIncreaseInside: Double = 1000000.0
  val infectionRatioIncreaseFromRoute: Double = 0.000001

/*
val population: Population = regionConfiguration.population
val richness: Richness = regionConfiguration.richness
val climate: Climate = regionConfiguration.climate
val bordersControl: BordersControl = regionConfiguration.bordersControl
val globalization: Globalization = regionConfiguration.globalization
val populationDensity: PopulationDensity = regionConfiguration.populationDensity
*/

class InternalInfectionLogic extends InfectionLogic:
  /**
   * Increase the infected amount for a specific factor
   */
  override def compute(region: Region, virus: Virus): Unit = region.infectedAmount.toFloat / region.population match
    case i if i > 0 && i < 0.1 => region.infectedAmount = region.infectedAmount + (region.infectedAmount / infectionRatioIncreaseInside).toInt
    case i if i < 0.3 => region.infectedAmount = region.infectedAmount + (region.infectedAmount / infectionRatioIncreaseInside * 9).toInt
    case i if i < 0.5 => region.infectedAmount = region.infectedAmount + (region.infectedAmount / infectionRatioIncreaseInside * 19).toInt
    case i if i < 1 => region.infectedAmount = region.infectedAmount + (region.infectedAmount / infectionRatioIncreaseInside * 29).toInt
    case _ =>





class ExternalInfectionLogic extends InfectionLogic:
  /**
   * Increase the infected amount for a specific factor
   */
  override def compute(region: Region, virus: Virus): Unit =
    if region.getReachableRegions.filter((region, reachableMode) => (region.infectedAmount.toFloat / region.population) > 0.5 &&
      (reachableMode == ReachableMode.Border) ||
      (reachableMode == ReachableMode.Airport && virus.airportEnabled) ||
      (reachableMode == ReachableMode.Port && virus.portEnabled)).size > 0
    then
      region.infectedAmount = region.infectedAmount + ((region.population * (this.infectedRatio(region) / region.population )).toInt)

  def infectedRatio(region: Region): Double =
    (2.0 / region.bordersControl) + (region.populationDensity / 2.0 ) + (region.globalization / 2.0 ) + (2.0 / region.richness)
