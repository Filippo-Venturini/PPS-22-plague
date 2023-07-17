package model.infection
import model.world.Region
import model.world.RegionTypes.ReachableMode
import scala.math._

/**
 * Class that contains infection logic.
 * In particular the internal infection logic is for increase the population infected when there are already infected people inside in the region.
 * The external infection logic is for increase the population infected for a region when there is one reachable region with infected.
 */

trait InfectionLogic:
  def compute(region: Region, virus: Virus): Unit
  def normalize(value: Double, min: Double, max: Double): Double = (value - min) / (max - min)

class InternalInfectionLogic extends InfectionLogic:


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
   * Increase the infected amount for a specific factor
   */
  override def compute(region: Region, virus: Virus): Unit =
    region.infectedAmount = min(region.infectedAmount + (getVirusInfectionRate(region, virus) * region.infectedAmount *
      getInfectionFactor(100*region.infectedAmount.toFloat / region.population)), region.population)





class ExternalInfectionLogic extends InfectionLogic:

  def getExternalInfectionIndex(infectedRegion: Region, regionToInfect: Region): Double =
    val normalizedGlobalization: Double = normalize(infectedRegion.globalization, -4, 7)
    val normalizedBorderControl = normalize(5 - regionToInfect.bordersControl + 1, -4, 7)
    val infectedPercentage: Double = normalize(infectedRegion.infectedAmount, 0, infectedRegion.population)
    min(1, infectedPercentage + (normalizedGlobalization * normalizedBorderControl + 0.2))


  /**
   * Increase the infected amount for a specific factor
   */
  override def compute(infectedRegion: Region, virus: Virus): Unit =
    infectedRegion.getReachableRegions.filter((_, mode) => mode match
      case ReachableMode.Border => true
      case ReachableMode.Airport if virus.airportEnabled => true
      case ReachableMode.Port if virus.portEnabled => true
      case _ => false
    ).filter((region, _) => region.infectedAmount == 0)
      .foreach((regionToInfect, _) => if getExternalInfectionIndex(infectedRegion, regionToInfect) == 1 then regionToInfect.infectedAmount = 1)



