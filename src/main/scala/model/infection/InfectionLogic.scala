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

class InternalInfectionLogic extends InfectionLogic:
  private def normalize(value: Double, min: Double, max: Double): Double = (value - min) / (max - min)

  private def getRichRegionInfectivityIndex(regionRichness: Int, richRegionsInfectivity: Int): Double =
    normalize(regionRichness, 1, 5) * normalize(richRegionsInfectivity,0 ,100)

  private def getPoorRegionInfectivityIndex(regionRichness: Int, poorRegionsInfectivity: Int): Double =
    normalize(5 - regionRichness + 1, 1, 5) * normalize(poorRegionsInfectivity, 0, 100)

  private def getColdRegionInfectivityIndex(climate: Int, coldRegionsInfectivity: Int): Double =
    normalize(3 - climate + 1, 1, 3) * normalize(coldRegionsInfectivity, 0, 100)

  private def getHotRegionInfectivityIndex(climate: Int, warmRegionsInfectivity: Int): Double =
    normalize(climate, 1, 3) * normalize(warmRegionsInfectivity, 0, 100)

  private def getLowDensityRegionInfectivityIndex(populationDensity: Int, lowDensityRegionInfectivity: Int): Double =
    normalize(5 - populationDensity + 1, 1, 5) * normalize(lowDensityRegionInfectivity, 0, 100)

  private def getHighDensityRegionInfectivityIndex(populationDensity: Int, highDensityRegionInfectivity: Int): Double =
    normalize(populationDensity, 1, 5) * normalize(highDensityRegionInfectivity, 0, 100)

  private def getVirusInfectionRate(region: Region, virus: Virus): Double = (getRichRegionInfectivityIndex(region.richness, virus.richRegionsInfectivity) + getPoorRegionInfectivityIndex(region.richness, virus.poorRegionsInfectivity) +
    getHighDensityRegionInfectivityIndex(region.populationDensity, virus.highDensityRegionsInfectivity) + getLowDensityRegionInfectivityIndex(region.populationDensity, virus.lowDensityRegionInfectivity) +
    getColdRegionInfectivityIndex(region.climate, virus.coldRegionsInfectivity) + getHotRegionInfectivityIndex(region.climate, virus.hotRegionsInfectivity)) / 6

  private def getInfectionFactor(infectedPercentage: Int) : Double = infectedPercentage match
    case infectedPercentage if infectedPercentage <= 1 => 2.4
    case infectedPercentage if infectedPercentage <= 10 => 0.3
    case _ => 0.1



  /**
   * Increase the infected amount for a specific factor
   */
  override def compute(region: Region, virus: Virus): Unit =
    region.infectedAmount = (region.infectedAmount + getVirusInfectionRate(region, virus) * region.infectedAmount * getInfectionFactor(region.infectedAmount / region.population)).toInt




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

  //TODO climate deve influenzare con l'avanzamento dell'infezione?