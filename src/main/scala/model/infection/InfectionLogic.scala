package model.infection
import model.world.Region
import model.world.RegionParameters.ReachableMode
import scala.math._

/**
 * Class that contains infection logic.
 * In particular the internal infection logic is for increase the population infected when there are already infected people inside in the region.
 * The external infection logic is for increase the population infected for a region when there is one reachable region with infected.
 */

trait InfectionLogic:
  /**
   * @param region is the region used for compute the infection logic
   * @param virus is the game virus instance, it is used for obtain virus property
   */
  def compute(region: Region, virus: Virus): Unit

  /**
   * @param value is the value to normalize
   * @param min is the minimum range for the normalize
   * @param max is the maximum range for the normalize
   * @return a normalize factor inside in another range
   */
  def normalize(value: Double, min: Double, max: Double): Double = (value - min) / (max - min)

/**
 * Class that represent the internal infection logic
 */
class InternalInfectionLogic extends InfectionLogic:

  /**
   * @param regionRichness is the region value that represent its richness
   * @param richRegionsInfectivity is the virus value that represent the level of infectivity inside the rich regions
   * @return the infectivity index that represent a rich region
   */
  private def getRichRegionInfectivityIndex(regionRichness: Int, richRegionsInfectivity: Int): Double =
    normalize(regionRichness, 1, 5) * normalize(richRegionsInfectivity,0 ,100)

  /**
   * @param regionRichness is the region value that represent its richness
   * @param poorRegionsInfectivity is the virus value that represent the level of infectivity inside the poor regions
   * @return the infectivity index that represent a poor region
   */
  private def getPoorRegionInfectivityIndex(regionRichness: Int, poorRegionsInfectivity: Int): Double =
    normalize(5 - regionRichness + 1, 1, 5) * normalize(poorRegionsInfectivity, 0, 100)

  /**
   * @param climate is the region value that represent its climate
   * @param coldRegionsInfectivity is the virus value that represent the level of infectivity inside the cold regions
   * @return the infectivity index that represent a cold region
   */
  private def getColdRegionInfectivityIndex(climate: Int, coldRegionsInfectivity: Int): Double =
    normalize(3 - climate + 1, 1, 3) * normalize(coldRegionsInfectivity, 0, 100)

  /**
   *
   * @param climate is the region value that represent its climate
   * @param hotRegionsInfectivity is the virus value that represent the level of infectivity inside the hot regions
   * @return the infectivity index that represent a hot region
   */
  private def getHotRegionInfectivityIndex(climate: Int, hotRegionsInfectivity: Int): Double =
    normalize(climate, 1, 3) * normalize(hotRegionsInfectivity, 0, 100)

  /**
   *
   * @param populationDensity is the region value that represent its population density
   * @param lowDensityRegionInfectivity is the virus value that represent the level of infectivity inside the low population density regions
   * @return the infectivity index that represent a region with low density population
   */
  private def getLowDensityRegionInfectivityIndex(populationDensity: Int, lowDensityRegionInfectivity: Int): Double =
    normalize(5 - populationDensity + 1, 1, 5) * normalize(lowDensityRegionInfectivity, 0, 100)

  /**
   *
   * @param populationDensity is the region value that represent its population density
   * @param highDensityRegionInfectivity is the virus value that represent the level of infectivity inside the high population density regions
   * @return the infectivity index that represent a rich region high density population
   */
  private def getHighDensityRegionInfectivityIndex(populationDensity: Int, highDensityRegionInfectivity: Int): Double =
    normalize(populationDensity, 1, 5) * normalize(highDensityRegionInfectivity, 0, 100)

  /**
   *
   * @param region to calculate the infection rate
   * @param virus is the instance of virus game
   * @return a rate that represent the virus infection
   */
  private def getVirusInfectionRate(region: Region, virus: Virus): Double = (getRichRegionInfectivityIndex(region.richness, virus.richRegionsInfectivity) + getPoorRegionInfectivityIndex(region.richness, virus.poorRegionsInfectivity) +
    getHighDensityRegionInfectivityIndex(region.populationDensity, virus.highDensityRegionsInfectivity) + getLowDensityRegionInfectivityIndex(region.populationDensity, virus.lowDensityRegionInfectivity) +
    getColdRegionInfectivityIndex(region.climate, virus.coldRegionsInfectivity) + getHotRegionInfectivityIndex(region.climate, virus.hotRegionsInfectivity)) / 6

  /**
   *
   * @param infectedPercentage is the value that represent the percentage of infected inside a region
   * @return an infection factor in base of different infected percentage
   */
  private def getInfectionFactor(infectedPercentage: Double) : Double = infectedPercentage match
    case infectedPercentage if infectedPercentage <= 0.1 => 5.5
    case infectedPercentage if infectedPercentage <= 1 => 0.6
    case infectedPercentage if infectedPercentage <= 10 => 0.3
    case infectedPercentage if infectedPercentage <= 20 => 0.08
    case infectedPercentage if infectedPercentage <= 30 => 0.06
    case infectedPercentage if infectedPercentage <= 40 => 0.048
    case infectedPercentage if infectedPercentage <= 50 => 0.039
    case _ => 0.035

  /**
   * Increment the infected amount inside in a region
   * @param region is the region used for compute the infection logic
   * @param virus is the game virus instance, it is used for obtain virus property
   */
  override def compute(region: Region, virus: Virus): Unit =
    region.infectedAmount = min(region.infectedAmount + (getVirusInfectionRate(region, virus) * region.infectedAmount *
      getInfectionFactor(100*region.infectedAmount.toFloat / region.population)), region.population)

/**
 * Class that represent the external infection logic
 */
class ExternalInfectionLogic extends InfectionLogic:

  /**
   *
   * @param infectedRegion represent the infected region
   * @param regionToInfect represent the healthy region to infected
   * @return index of external infection. It is used to choose whether to infect a healthy region
   */
  def getExternalInfectionIndex(infectedRegion: Region, regionToInfect: Region): Double =
    val normalizedGlobalization: Double = normalize(infectedRegion.globalization, -4, 7)
    val normalizedBorderControl = normalize(5 - regionToInfect.bordersControl + 1, -4, 7)
    val infectedPercentage: Double = normalize(infectedRegion.infectedAmount, 0, infectedRegion.population)
    min(1, infectedPercentage + (normalizedGlobalization * normalizedBorderControl + 0.2))


  /**
   * Check if the infection can spread across borders, ports or airports and calculate the external infection index
   * @param infectedRegion is the infected region used to infect another region
   * @param virus is the game virus instance, it is used for obtain virus property
   */
  override def compute(infectedRegion: Region, virus: Virus): Unit =
    infectedRegion.getReachableRegions.filter((_, mode) => mode match
      case ReachableMode.Border => true
      case ReachableMode.Airport if virus.airportEnabled => true
      case ReachableMode.Port if virus.portEnabled => true
      case _ => false
    ).filter((region, _) => region.infectedAmount == 0)
      .foreach((regionToInfect, _) => if getExternalInfectionIndex(infectedRegion, regionToInfect) == 1 then regionToInfect.infectedAmount = 1)
